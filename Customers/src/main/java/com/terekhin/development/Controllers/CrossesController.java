package com.terekhin.development.Controllers;

import com.terekhin.development.domain.CrossCourse;
import com.terekhin.development.helpers.ActionParser;
import com.terekhin.development.helpers.NotificationService;
import com.terekhin.development.helpers.NotificationType;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CrossesController extends Controller{
    public CrossesController() {
        this.tmplDir = "/cross-courses";
    }

    @Override
    public String index(WebContext webContext) throws NotificationService {
        long currency_id = this.validateId(webContext,_dbCtx.Currency(),"currency");

        webContext.setVariable("crosses", _dbCtx.Crosses().getAllByCurrencyId(currency_id));
        return this.view("/pages/cross-courses/list", webContext);
    }

    @Override
    public String create(WebContext webContext) throws NotificationService {

        webContext.setVariable("currencies", _dbCtx.Currency().getAll());

        long currency_id = this.validateId(webContext,_dbCtx.Currency(),"currency");

        CrossCourse cross = new CrossCourse();

        if (webContext.getRequest().getMethod().equals("POST")) {
            try{
            cross.setFromCurrencyId(currency_id);
            cross.setToCurrencyId(Long.parseLong(webContext.getRequest().getParameter("CurrencyFrom")));
            cross.setAmount(Double.parseDouble(webContext.getRequest().getParameter("Amount")));
                _dbCtx.Crosses().create(cross);
                return this.viewRedirect(webContext, "Cross-Course added");
            }catch(NotificationService notify)
            {
                notify.show(webContext,NotificationType.ERROR);
            }
        }

            webContext.setVariable("cross", cross);
            return this.view("/pages/cross-courses/add", webContext);
    }

    @Override
    public String update(WebContext webContext) throws NotificationService {
        return null;
    }

    @Override
    public String destroy(WebContext webContext) throws NotificationService {
        _dbCtx.Crosses().delete(Long.valueOf(webContext.getRequest().getParameter("id")));
        return this.viewRedirect(webContext);
    }
}
