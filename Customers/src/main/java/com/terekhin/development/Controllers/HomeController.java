package com.terekhin.development.Controllers;

import com.terekhin.development.helpers.NotificationService;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Calendar;

public class HomeController extends Controller {

    @Override
    public String index(WebContext webContext) throws NotificationService {
        return this.view("/pages/dashboard",webContext);
    }

    @Override
    public String create(WebContext webContext) throws NotificationService {
        return null;
    }

    @Override
    public String update(WebContext webContext) throws NotificationService {
        return null;
    }

    @Override
    public String destroy(WebContext webContext) throws NotificationService {
        return null;
    }
}
