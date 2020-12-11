package com.terekhin.development.Controllers;

import com.terekhin.development.database.DBContext;
import com.terekhin.development.database.IRepository;
import com.terekhin.development.helpers.NotificationService;
import com.terekhin.development.helpers.NotificationType;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.context.WebEngineContext;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public abstract class Controller {
    protected DBContext _dbCtx;
    protected String tmplDir ="/";
    private String queryParam ="";
    protected boolean showBtn =true;

    public Controller() {
        _dbCtx = new DBContext();
    }

    public void initGlobal(HttpServletRequest request, HttpServletResponse response,WebContext webContext)
    {
        webContext.setVariable("template_path", tmplDir);
        webContext.setVariable("show_btn", showBtn);
        if(request.getParameter("cid")!=null) {
            webContext.setVariable("cid", request.getParameter("cid"));
            queryParam ="?cid="+request.getParameter("cid");
        }
    }
    public abstract String index(WebContext webContext) throws NotificationService;
    public abstract String create(WebContext webContext) throws NotificationService;
    public abstract String update(WebContext webContext) throws NotificationService;
    public abstract String destroy(WebContext webContext) throws NotificationService;

    protected String viewRedirect(WebContext webContext) throws NotificationService {
        try {
            webContext.getResponse().sendRedirect(this.tmplDir +this.queryParam);
            return null;
        } catch (IOException e) {
            throw new NotificationService(e.getMessage());
        }
    }
    protected String viewRedirect(WebContext webContext, String respMessage) throws NotificationService {
        try {
            if (respMessage == null || respMessage.isEmpty())
                this.viewRedirect(webContext);
            HttpSession session = webContext.getSession();
            session.setAttribute("message", new NotificationService(respMessage));
            webContext.getResponse().sendRedirect(this.tmplDir + this.queryParam);
            return null;
        }catch(IOException e)
        {
            throw new NotificationService(e.getMessage());
        }
    }
    protected String view(String path, WebContext webContext) throws NotificationService {
        HttpSession session = webContext.getRequest().getSession();
        NotificationService message = (NotificationService) session.getAttribute("message");
        if(message!=null)
        {
            message.show(webContext, NotificationType.SUCCESS);
            session.removeAttribute("message");
        }
       return path;
    }

    protected long validateId(WebContext webContext, IRepository<?,Long> value,String viewVariableName) throws NotificationService {
        String cid = webContext.getRequest().getParameter("cid");
        if(cid==null)  {throw new NotificationService("Cant find Item with ID="+cid);}
        long id = Long.parseLong(cid);
        webContext.setVariable(viewVariableName, value.getById(id));
        return id;
    }

}
