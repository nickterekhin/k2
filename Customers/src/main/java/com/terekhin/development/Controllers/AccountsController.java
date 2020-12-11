package com.terekhin.development.Controllers;

import com.terekhin.development.domain.*;
import com.terekhin.development.helpers.ActionParser;
import com.terekhin.development.helpers.Notification;
import com.terekhin.development.helpers.NotificationService;
import com.terekhin.development.helpers.NotificationType;
import org.joda.time.DateTime;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class AccountsController extends Controller{

    public AccountsController() {
        this.tmplDir = "/accounts";
    }


    @Override
    public String index(WebContext webContext) throws NotificationService {

            long customer_id = this.validateId(webContext,_dbCtx.Customers(),"customer");

            webContext.setVariable("accounts", _dbCtx.Accounts().getAllByCustomerId(customer_id));
            return this.view("/pages/accounts/list", webContext);
    }

    @Override
    public String create(WebContext webContext) throws NotificationService {

        long customer_id = this.validateId(webContext,_dbCtx.Customers(),"customer");;

        webContext.setVariable("currencies", _dbCtx.Currency().getAll());

        Account account = new Account();

        if (webContext.getRequest().getMethod().equals("POST")) {
            try {
                account.setCustomerId(customer_id);
                account.setCurrencyId(Long.parseLong(webContext.getRequest().getParameter("Currency")));
                account.setBalance(Double.parseDouble(webContext.getRequest().getParameter("Balance")));
                _dbCtx.Accounts().create(account);
                return this.viewRedirect(webContext, "Account added");
            }catch(NotificationService notify)
            {
                notify.show(webContext,NotificationType.ERROR);
            }
        }

            webContext.setVariable("account", account);
            return this.view("/pages/accounts/add",webContext);
    }

    @Override
    public String update(WebContext webContext) throws NotificationService {
        return null;
    }

    @Override
    public String destroy(WebContext webContext) throws NotificationService {
        _dbCtx.Accounts().delete(Long.valueOf(webContext.getRequest().getParameter("id")));
        return this.viewRedirect(webContext);
    }

    public String charge(WebContext webContext) throws NotificationService{
        this.validateId(webContext,_dbCtx.Customers(),"customer");;
        String id = webContext.getRequest().getParameter("id");
        Account account = _dbCtx.Accounts().getById(Long.valueOf(id));
        if(webContext.getRequest().getMethod().equals("POST"))
        {
            try {
                boolean isCharge = Integer.parseInt(webContext.getRequest().getParameter("Charge")) == 1;
                double amount = Double.parseDouble(webContext.getRequest().getParameter("Balance"));
                account.setBalance(account.getBalance() + (amount) * (isCharge ? 1 : -1));

                if (account.getBalance() < amount)
                    throw new NotificationService("There are not enough money on you Account");

                Transaction transaction = new Transaction();
                transaction.setAccountId(account.getId());
                transaction.setAmount(amount);
                transaction.setCreatedAt(DateTime.now());
                transaction.setTypeId(isCharge ? TransactionTypesList.CHARGE.getAction() : TransactionTypesList.WITHDRAW.getAction());
                transaction.setDescription((isCharge ? "Account Charged with " : "Account Withdraw with ") + Double.toString(amount));
                _dbCtx.Accounts().charge(account, transaction);

                return this.viewRedirect(webContext, "Balance Changed Successfully!");
            }catch(NotificationService notify)
            {
                notify.show(webContext,NotificationType.ERROR);
            }

        }

            webContext.setVariable("account",account);
        return this.view("/pages/accounts/charge", webContext);

    }
    public String transfer(WebContext webContext) throws NotificationService{
        this.validateId(webContext,_dbCtx.Customers(),"customer");
        Account account = _dbCtx.Accounts().getById(Long.valueOf(webContext.getRequest().getParameter("id")));
        List<Account> accounts = _dbCtx.Accounts().getAccountsWithoutId(account.getId());
        if (webContext.getRequest().getMethod().equals("POST")) {
            Account to_account = _dbCtx.Accounts().getById(Long.parseLong(webContext.getRequest().getParameter("toAccount")));
            CrossCourse cc = account.getCurrency().getCrossCoursesFrom().stream().filter(e->e.getToCurrencyId()==to_account.getCurrencyId()).findFirst().orElse(null);
            try {
                if (cc == null) throw new NotificationService("There is no any cross-courses");

                double amount = Double.parseDouble(webContext.getRequest().getParameter("Amount") != null ? webContext.getRequest().getParameter("Amount") : "0");

                if (account.getBalance() < amount)
                    throw new NotificationService("There are no enough money to transfer");

                to_account.setBalance(to_account.getBalance() + (amount * cc.getAmount()));
                account.setBalance(account.getBalance() - amount);
                Transaction transactionFrom = new Transaction();
                transactionFrom.setAccountId(account.getId());
                transactionFrom.setAmount(amount);
                transactionFrom.setCreatedAt(DateTime.now());
                transactionFrom.setTypeId(TransactionTypesList.TRANSFER.getAction());
                transactionFrom.setDescription("Transfer to Account (" + to_account.getCurrency().getName() + ")" + to_account.getNumber() + " Amount: (" + account.getCurrency().getName() + ")" + Double.toString(amount) + " completed successfully!");
                Transaction transactionTo = new Transaction();
                transactionTo.setAccountId(to_account.getId());
                transactionTo.setAmount(amount * cc.getAmount());
                transactionTo.setCreatedAt(DateTime.now());
                transactionTo.setTypeId(TransactionTypesList.TRANSFER.getAction());
                transactionTo.setDescription("Transfer from Account (" + account.getCurrency().getName() + "):" + account.getNumber() + "  Amount of: (" + to_account.getCurrency().getName() + ")" + Double.toString(amount * cc.getAmount()) + " completed successfully!");
                _dbCtx.Accounts().transfer(account, to_account, transactionFrom, transactionTo);
                return this.viewRedirect(webContext, "Transfer completed");
            }catch(NotificationService notify)
            {
                notify.show(webContext,NotificationType.ERROR);
            }

        }

            webContext.setVariable("account",account);
            webContext.setVariable("accounts",accounts);
            return this.view("/pages/accounts/transfer", webContext);

    }
}
