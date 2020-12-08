package com.terekhin.development.Config;

import com.terekhin.development.Controllers.*;
import nz.net.ultraq.thymeleaf.JodaDialect;
import nz.net.ultraq.thymeleaf.LayoutDialect;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.templateresolver.ServletContextTemplateResolver;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

public class Application {

    private  Map<String,Controller> actions;
    private TemplateEngine templateEngine;

    public Application(final ServletContext servletContext) {
        super();

            initTemplateEngine(servletContext);
            initRoutes();

    }

    public Controller getAction(HttpServletRequest request)
    {
        return actions.get(getRequestPath(request));
    }

    public TemplateEngine getTemplateEngine(){return this.templateEngine;}

    private void initTemplateEngine(final ServletContext servletContext)
    {
        ServletContextTemplateResolver _templateResolver = new ServletContextTemplateResolver(servletContext);

        _templateResolver.setTemplateMode("HTML");
        _templateResolver.setPrefix("/templates/");
        _templateResolver.setSuffix(".jsp");
        _templateResolver.setCacheTTLMs(Long.valueOf(3600000L));
        _templateResolver.setCacheable(false);
        templateEngine = new TemplateEngine();
        templateEngine.setTemplateResolver(_templateResolver);
        templateEngine.addDialect(new JodaDialect());
        templateEngine.addDialect(new LayoutDialect());
    }

    private void initRoutes()
    {
        this.actions = new HashMap<String,Controller>();
        this.actions.put("/", new HomeController());
        this.actions.put("/customers", new CustomersController());
        this.actions.put("/currencies",new CurrencyController());
        this.actions.put("/accounts",new AccountsController());
        this.actions.put("/cross-courses",new CrossesController());

    }
    private String getRequestPath(final HttpServletRequest request) {
        String requestURI = request.getRequestURI();
        final String contextPath = request.getContextPath();
        final int fragmentIndex = requestURI.indexOf(';');
        if (fragmentIndex != -1) {
            requestURI = requestURI.substring(0, fragmentIndex);
        }
        if (requestURI.startsWith(contextPath)) {
            return requestURI.substring(contextPath.length());
        }
        return requestURI;
    }
}
