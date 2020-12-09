package com.terekhin.development.Controllers;

import com.terekhin.development.domain.Currency;
import com.terekhin.development.helpers.ActionParser;
import com.terekhin.development.helpers.NotificationService;
import com.terekhin.development.helpers.NotificationType;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CurrencyController extends Controller{

    public CurrencyController() {
        this.tmpl_dir="/currencies";
    }

    @Override
    public void getModel(HttpServletRequest request, HttpServletResponse response, WebContext webContext, TemplateEngine tmpl) throws Exception {

        ActionParser actionParser = ActionParser.getResponseAction(request.getParameter("action"));

        switch(actionParser)
        {
            case DELETE:
                _dbCtx.Currency().delete(Long.valueOf(request.getParameter("id")));
                this.viewRedirect(response);
                break;
            case ADD:
                Currency currency = new Currency();
                if(request.getMethod().equals("POST"))
                {
                    currency.setName(request.getParameter("Name"));
                    try{
                        _dbCtx.Currency().create(currency);
                        this.viewRedirect(request,response,"Currency Added successfully");
                    }catch(NotificationService notify)
                    {
                        notify.show(request, NotificationType.ERROR);
                    }
                }
                if(!response.isCommitted())
                {
                    request.setAttribute("currency", currency);
                    this.view("/pages/currencies/add",request,response, webContext,tmpl);
                }
                break;
            case EDIT:
                String id = request.getParameter("id");
                currency = _dbCtx.Currency().getById(Long.valueOf(id));
                if(request.getMethod().equals("POST"))
                {
                    currency.setName(request.getParameter("Name"));
                    try {

                        _dbCtx.Currency().update(currency);
                        this.viewRedirect(request,response,"Currency Updated Successfully");
                    }catch (NotificationService notify)
                    {
                        notify.show(request, NotificationType.ERROR);
                    }
                }
                if(!response.isCommitted()) {
                    request.setAttribute("currency", currency);
                    this.view("/pages/customers/edit",request,response, webContext,tmpl);
                }
                break;
            default:
                webContext.setVariable("currencies",_dbCtx.Currency().getAll());
                this.view("/pages/currencies/list",request,response, webContext,tmpl);

        }
    }
}
