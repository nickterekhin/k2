package com.terekhin.development.Controllers;

import com.terekhin.development.domain.*;
import com.terekhin.development.helpers.ActionParser;
import com.terekhin.development.helpers.Notification;
import com.terekhin.development.helpers.NotificationService;
import com.terekhin.development.helpers.NotificationType;
import org.joda.time.DateTime;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.StringReader;
import java.util.List;

public class AccountsController extends Controller{

    public AccountsController() {
        this.tmpl_dir = "/accounts";
    }

    @Override
    public void getModel(HttpServletRequest request, HttpServletResponse response, WebContext webContext, TemplateEngine tmpl) throws Exception {
        String cid = request.getParameter("cid");
        if(cid==null){
            response.sendRedirect("/customers");
        }else {
            long customer_id = Long.parseLong(cid);
            webContext.setVariable("customer", _dbCtx.Customers().getById(customer_id));

            ActionParser actionParser = ActionParser.getResponseAction(request.getParameter("action"));

            switch (actionParser) {
                case DELETE:
                    _dbCtx.Accounts().delete(Long.valueOf(request.getParameter("id")));
                    this.viewRedirect(response);
                    break;
                case ADD:
                    webContext.setVariable("currencies", _dbCtx.Currency().getAll());

                    Account account = new Account();

                    if (request.getMethod().equals("POST")) {
                        account.setCustomerId(customer_id);
                        account.setCurrencyId(Long.valueOf(request.getParameter("Currency")));
                        account.setBalance(Double.valueOf(request.getParameter("Balance")));
                        try {
                            _dbCtx.Accounts().create(account);
                            this.viewRedirect(request, response, "Account added");
                        } catch (NotificationService notify) {
                            notify.show(request, NotificationType.ERROR);
                        }
                    }
                    if (!response.isCommitted()) {
                        webContext.setVariable("account", account);
                        this.view("/pages/accounts/add",request,response, webContext,tmpl);
                    }
                    break;
                case CHARGE:
                    String id = request.getParameter("id");
                    account = _dbCtx.Accounts().getById(Long.valueOf(id));
                    if(request.getMethod().equals("POST"))
                    {
                        try {
                            boolean isCharge = Integer.parseInt(request.getParameter("Charge")) == 1;
                            double amount = Double.parseDouble(request.getParameter("Balance"));
                            account.setBalance(account.getBalance() + (amount) * (isCharge ? 1 : -1));

                            if(!isCharge && account.getBalance()<amount) throw new NotificationService("There are not enough money on you Account");
                            _dbCtx.Accounts().update(account);
                            Transaction transaction = new Transaction();
                            transaction.setAccountId(account.getId());
                            transaction.setAmount(amount);
                            transaction.setCreatedAt(DateTime.now());
                            transaction.setTypeId(isCharge? TransactionTypesList.CHARGE.getAction():TransactionTypesList.WITHDRAW.getAction());
                            transaction.setDescription((isCharge?"Account Charged with ":"Account Withdraw with ")+ Double.toString(amount));
                            _dbCtx.Transactions().create(transaction);

                            this.viewRedirect(request,response,"Balance Changed Successfully!");
                        }catch(NotificationService notify)
                        {

                            notify.show(request,NotificationType.ERROR);
                        }
                    }
                    if(!response.isCommitted()){
                        webContext.setVariable("account",account);
                        this.view("/pages/accounts/charge",request,response, webContext,tmpl);
                    }
                    break;
                case TRANSFER:
                    account = _dbCtx.Accounts().getById(Long.valueOf(request.getParameter("id")));
                    List<Account> accounts = _dbCtx.Accounts().getAccountsWithoutId(account.getId());
                    if (request.getMethod().equals("POST")) {
                            Account to_account = _dbCtx.Accounts().getById(Long.parseLong(request.getParameter("toAccount")));
                            CrossCourse cc = account.getCurrency().getCrossCoursesFrom().stream().filter(e->e.getToCurrencyId()==to_account.getCurrencyId()).findFirst().orElse(null);
                            if(cc==null) throw new NotificationService("There is no any cross-courses");

                            double amount = Double.parseDouble(request.getParameter("Amount")!=null?request.getParameter("Amount"):"0");

                            if(account.getBalance()<amount) throw new NotificationService("There are no enough money to transfer");

                                to_account.setBalance(to_account.getBalance()+(amount*cc.getAmount()));
                                account.setBalance(account.getBalance()-amount);
                        Transaction transactionFrom = new Transaction();
                        transactionFrom.setAccountId(account.getId());
                        transactionFrom.setAmount(amount);
                        transactionFrom.setCreatedAt(DateTime.now());
                        transactionFrom.setTypeId(TransactionTypesList.TRANSFER.getAction());
                        transactionFrom.setDescription("Transfer to Account ("+to_account.getCurrency().getName()+")"+to_account.getNumber()+" Amount: ("+account.getCurrency().getName()+")"+ Double.toString(amount) +" completed successfully!");
                        Transaction transactionTo = new Transaction();
                        transactionTo.setAccountId(to_account.getId());
                        transactionTo.setAmount(amount*cc.getAmount());
                        transactionTo.setCreatedAt(DateTime.now());
                        transactionTo.setTypeId(TransactionTypesList.TRANSFER.getAction());
                        transactionTo.setDescription("Transfer from Account ("+account.getCurrency().getName()+"):"+account.getNumber()+"  Amount of: ("+to_account.getCurrency().getName()+")"+ Double.toString(amount*cc.getAmount()) +" completed successfully!");
                            _dbCtx.Accounts().transfer(account,to_account, transactionFrom,transactionTo);
                        this.viewRedirect(request,response,"Transfer completed");
                        }
                    if(!response.isCommitted()){
                        webContext.setVariable("account",account);
                        webContext.setVariable("accounts",accounts);
                        this.view("/pages/accounts/transfer",request,response, webContext,tmpl);
                    }
                    break;
                default:
                    webContext.setVariable("accounts", _dbCtx.Accounts().getAllByCustomerId(customer_id));
                    this.view("/pages/accounts/list",request,response, webContext,tmpl);
            }
        }
    }
}
