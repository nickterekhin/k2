package com.terekhin.development.Filters;

import com.terekhin.development.Config.Application;
import com.terekhin.development.Config.RouteConfig;
import com.terekhin.development.Controllers.Controller;
import com.terekhin.development.helpers.NotificationService;
import com.terekhin.development.helpers.NotificationType;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class CoreEngineFilter implements Filter {

    private ServletContext _servletCtx;
    private Application _application;

    public CoreEngineFilter() {
        super();
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        this._servletCtx = filterConfig.getServletContext();
        this._application = new Application(_servletCtx);
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse resp = (HttpServletResponse) servletResponse;

        try {
            if(!execute(req,resp))
            {
                filterChain.doFilter(servletRequest,servletResponse);
            }
        } catch (NoSuchMethodException | InvocationTargetException | IllegalAccessException | InstantiationException e) {
            e.printStackTrace();
        }
    }


    @Override
    public void destroy() {

    }
    private boolean execute(HttpServletRequest req, HttpServletResponse resp) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException, InstantiationException, IOException {

        RouteConfig route = _application.getRoute(req);

        Method method = route.getMethod();

        TemplateEngine templateEngine = _application.getTemplateEngine();


        resp.setContentType("text/html;charset=utf-8");
        resp.setHeader("Param", "no-cache");
        resp.setHeader("Cache-Control", "no-cache");
        resp.setDateHeader("Expires", 0);

        WebContext webContext = new WebContext(req, resp, this._servletCtx, req.getLocale());
        Controller controller = route.getInstance();
        controller.initGlobal(req,resp,webContext);
        String path =null;
        try {
            path = (String) method.invoke(controller, webContext);
            int pos_redirect = path.indexOf(':');

            if(pos_redirect!=-1 && path.substring(0,pos_redirect).equals("redirect")) {
                String s = path.substring(0,pos_redirect);
                String redirect_path = path.substring(pos_redirect+1);
                resp.sendRedirect(redirect_path);
            }else{
                templateEngine.process(path, webContext, webContext.getResponse().getWriter());
            }

        }catch(InvocationTargetException exception)
        {
            NotificationService notify = (NotificationService) exception.getTargetException();
            notify.show(webContext,NotificationType.ERROR);
            templateEngine.process("/errors/500", webContext, webContext.getResponse().getWriter());
        }



        return true;
    }
}
