package com.terekhin.development.Filters;

import com.terekhin.development.Config.Application;
import com.terekhin.development.Controllers.Controller;
import org.thymeleaf.TemplateEngine;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

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

        if(!execute(req,resp))
        {
            filterChain.doFilter(servletRequest,servletResponse);
        }
    }


    @Override
    public void destroy() {

    }
    private boolean execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException
    {
        try {
            Controller controller = _application.getAction(req);
            if (controller == null)
                return false;


            TemplateEngine templateEngine = _application.getTemplateEngine();

            resp.setContentType("text/html;charset=utf-8");
            resp.setHeader("Param", "no-cache");
            resp.setHeader("Cache-Control", "no-cache");
            resp.setDateHeader("Expires", 0);

            controller.initModelView(req, resp, this._servletCtx, templateEngine);

            return true;
        }
        catch(Exception e)
        {
            throw new ServletException(e);
        }
    }
}
