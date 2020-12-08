package com.terekhin.development.Controllers;

import com.terekhin.development.domain.Account;
import com.terekhin.development.domain.Customer;
import com.terekhin.development.helpers.ActionParser;
import com.terekhin.development.helpers.NotificationService;
import com.terekhin.development.helpers.NotificationType;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
                        tmpl.process("/pages/accounts/add", webContext, response.getWriter());
                    }
                    break;
                default:
                    webContext.setVariable("accounts", _dbCtx.Accounts().getAllByCustomerId(customer_id));
                    tmpl.process("/pages/accounts/list", webContext, response.getWriter());
            }
        }
    }
}
