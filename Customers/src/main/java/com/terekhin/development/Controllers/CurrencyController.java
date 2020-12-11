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
        this.tmplDir ="/currencies";
    }

    @Override
    public String index(WebContext webContext) throws NotificationService {
        webContext.setVariable("currencies",_dbCtx.Currency().getAll());
        return this.view("/pages/currencies/list", webContext);
    }

    @Override
    public String create(WebContext webContext) throws NotificationService {
        Currency currency = new Currency();
        if(webContext.getRequest().getMethod().equals("POST"))
        {
            try {
                currency.setName(webContext.getRequest().getParameter("Name"));
                _dbCtx.Currency().create(currency);
                return this.viewRedirect(webContext, "Currency Added successfully");
            }catch(NotificationService notify)
            {
                notify.show(webContext,NotificationType.ERROR);
            }
        }

            webContext.setVariable("currency", currency);
            return this.view("/pages/currencies/add", webContext);
    }

    @Override
    public String update(WebContext webContext) throws NotificationService {
        String id = webContext.getRequest().getParameter("id");
        Currency currency = _dbCtx.Currency().getById(Long.valueOf(id));
        if(webContext.getRequest().getMethod().equals("POST"))
        {
            try {
                currency.setName(webContext.getRequest().getParameter("Name"));
                _dbCtx.Currency().update(currency);
                return this.viewRedirect(webContext, "Currency Updated Successfully");
            }catch(NotificationService notify)
            {
                notify.show(webContext,NotificationType.ERROR);
            }
        }
            webContext.setVariable("currency", currency);
            return this.view("/pages/customers/edit", webContext);
    }

    @Override
    public String destroy(WebContext webContext) throws NotificationService {
        _dbCtx.Currency().delete(Long.valueOf(webContext.getRequest().getParameter("id")));
        return this.viewRedirect(webContext);
    }
}
