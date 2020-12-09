package com.terekhin.development.Controllers;

import com.terekhin.development.database.DBContext;
import com.terekhin.development.domain.Currency;
import com.terekhin.development.helpers.ActionParser;
import com.terekhin.development.helpers.NotificationService;
import com.terekhin.development.helpers.NotificationType;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

public abstract class Controller {
    protected DBContext _dbCtx;
    protected String tmpl_dir="/";
    private String query_param="";
    protected boolean show_btn=true;

    public Controller() {
        _dbCtx = new DBContext();
    }

    public void initModelView(HttpServletRequest request, HttpServletResponse response, ServletContext ctx, TemplateEngine tmpl) throws Exception {
        WebContext webContext = new WebContext(request, response, ctx, request.getLocale());
        webContext.setVariable("template_path",tmpl_dir);
        webContext.setVariable("show_btn",show_btn);
        if(request.getParameter("cid")!=null) {
            webContext.setVariable("cid", request.getParameter("cid"));
            query_param="?cid="+request.getParameter("cid");
        }
        this.getModel(request,response,webContext,tmpl);
    }

    public abstract void getModel(HttpServletRequest request, HttpServletResponse response, WebContext webContext, TemplateEngine tmpl) throws Exception ;

    protected void viewRedirect(HttpServletResponse resp) throws IOException {
        resp.sendRedirect(this.tmpl_dir+this.query_param);
    }
    protected void viewRedirect(HttpServletRequest req, HttpServletResponse resp, String respMessage) throws IOException {
        if(respMessage==null || respMessage.isEmpty())
            this.viewRedirect(resp);
        HttpSession session = req.getSession();
        session.setAttribute("message",new NotificationService(respMessage));
        resp.sendRedirect(this.tmpl_dir+this.query_param);
    }
    protected void view(String path, HttpServletRequest req, HttpServletResponse resp, WebContext webContext, TemplateEngine tmpl) throws IOException {
        HttpSession session = req.getSession();
        NotificationService message = (NotificationService) session.getAttribute("message");
        if(message!=null)
        {
            message.show(req, NotificationType.SUCCESS);
            session.removeAttribute("message");
        }
        tmpl.process(path, webContext, resp.getWriter());
    }

}
