package com.terekhin.development.Config;

import com.terekhin.development.Controllers.*;
import com.terekhin.development.domain.CrossCourse;
import nz.net.ultraq.thymeleaf.JodaDialect;
import nz.net.ultraq.thymeleaf.LayoutDialect;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.templateresolver.ServletContextTemplateResolver;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

public class Application {
    private Map<String,RouteConfig> routes;
    private TemplateEngine templateEngine;

    public Application(final ServletContext servletContext) {
        super();

            initTemplateEngine(servletContext);
            initRoutes();

    }

    public RouteConfig getRoute(HttpServletRequest request)
    {
        return routes.get(getRequestPath(request));
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
        this.routes = new HashMap<>();
        this.routes.put("/", new RouteConfig<>(HomeController.class));
        this.routes.put("/customers", new RouteConfig<>(CustomersController.class, "index"));
        this.routes.put("/customers/add", new RouteConfig<>(CustomersController.class, "create"));
        this.routes.put("/customers/delete", new RouteConfig<>(CustomersController.class, "destroy"));
        this.routes.put("/customers/edit", new RouteConfig<>(CustomersController.class, "update"));
        this.routes.put("/accounts",new RouteConfig<>(AccountsController.class));
        this.routes.put("/accounts/add",new RouteConfig<>(AccountsController.class,"create"));
        this.routes.put("/accounts/delete",new RouteConfig<>(AccountsController.class,"destroy"));
        this.routes.put("/accounts/charge",new RouteConfig<>(AccountsController.class,"charge"));
        this.routes.put("/accounts/transfer",new RouteConfig<>(AccountsController.class,"transfer"));
        this.routes.put("/currencies",new RouteConfig<>(CurrencyController.class));
        this.routes.put("/currencies/add",new RouteConfig<>(CurrencyController.class,"create"));
        this.routes.put("/currencies/delete",new RouteConfig<>(CurrencyController.class,"destroy"));
        this.routes.put("/currencies/edit",new RouteConfig<>(CurrencyController.class,"edit"));
        this.routes.put("/cross-courses",new RouteConfig<>(CrossesController.class));
        this.routes.put("/cross-courses/add",new RouteConfig<>(CrossesController.class,"create"));
        this.routes.put("/cross-courses/delete",new RouteConfig<>(CrossesController.class,"destroy"));
        this.routes.put("/transactions",new RouteConfig<>(TransactionController.class));
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
