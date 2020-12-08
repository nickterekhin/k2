package com.terekhin.development.Controllers;

import com.terekhin.development.domain.Account;
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
        this.tmpl_dir = "/cross-courses";
    }

    @Override
    public void getModel(HttpServletRequest request, HttpServletResponse response, WebContext webContext, TemplateEngine tmpl) throws Exception {
        String cid = request.getParameter("cid");
        if(cid==null){
            response.sendRedirect("/customers");
        }else {
            long currency_id = Long.parseLong(cid);
            webContext.setVariable("currency", _dbCtx.Currency().getById(currency_id));
            ActionParser actionParser = ActionParser.getResponseAction(request.getParameter("action"));

            switch (actionParser) {
                case DELETE:
                    _dbCtx.Crosses().delete(Long.valueOf(request.getParameter("id")));
                    this.viewRedirect(response);
                    break;
                case ADD:
                    webContext.setVariable("currencies", _dbCtx.Currency().getAll());

                    CrossCourse cross = new CrossCourse();

                    if (request.getMethod().equals("POST")) {
                        cross.setFromCurrencyId(currency_id);
                        cross.setToCurrencyId(Long.parseLong(request.getParameter("CurrencyFrom")));
                        cross.setAmount(Double.parseDouble(request.getParameter("Amount")));
                        try {
                            _dbCtx.Crosses().create(cross);
                            this.viewRedirect(request, response, "Cross-Course added");
                        } catch (NotificationService notify) {
                            notify.show(request, NotificationType.ERROR);
                        }
                    }
                    if (!response.isCommitted()) {
                        webContext.setVariable("cross", cross);
                        tmpl.process("/pages/cross-courses/add", webContext, response.getWriter());
                    }
                    break;
                default:
                    webContext.setVariable("crosses", _dbCtx.Crosses().getAllByCurrencyId(currency_id));
                    tmpl.process("/pages/cross-courses/list", webContext, response.getWriter());
            }
        }

    }
}
