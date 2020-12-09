package com.terekhin.development.Controllers;

import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Calendar;

public class HomeController extends Controller {
    @Override
    public void getModel(HttpServletRequest request, HttpServletResponse response, WebContext webContext, TemplateEngine tmpl) throws Exception {

        this.view("/pages/dashboard",request,response, webContext,tmpl);
    }
}
