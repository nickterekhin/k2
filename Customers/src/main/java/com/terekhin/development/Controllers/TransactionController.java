package com.terekhin.development.Controllers;

import org.joda.time.DateTime;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class TransactionController extends Controller{

    public TransactionController() {
        this.tmpl_dir = "/trans";
        this.show_btn = false;
    }

    @Override
    public void getModel(HttpServletRequest request, HttpServletResponse response, WebContext webContext, TemplateEngine tmpl) throws Exception {
        long cid = Long.parseLong(request.getParameter("cid"));
        long account_id = Long.parseLong(request.getParameter("id"));

        webContext.setVariable("transactions", _dbCtx.Transactions().getAllByAccountId(account_id));
        tmpl.process("/pages/trans/list", webContext, response.getWriter());
    }
}
