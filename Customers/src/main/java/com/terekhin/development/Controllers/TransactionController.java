package com.terekhin.development.Controllers;

import com.terekhin.development.helpers.NotificationService;
import org.thymeleaf.context.WebContext;


public class TransactionController extends Controller{

    public TransactionController() {
        this.tmplDir = "/trans";
        this.showBtn = false;
    }

    @Override
    public String index(WebContext webContext) throws NotificationService {
        long account_id = Long.parseLong((String) webContext.getRequest().getParameter("id"));

        webContext.setVariable("transactions", _dbCtx.Transactions().getAllByAccountId(account_id));
        return this.view("/pages/trans/list", webContext);
    }

    @Override
    public String create(WebContext webContext) throws NotificationService {
        return null;
    }

    @Override
    public String update(WebContext webContext) throws NotificationService {
        return null;
    }

    @Override
    public String destroy(WebContext webContext) throws NotificationService {
        return null;
    }
}
