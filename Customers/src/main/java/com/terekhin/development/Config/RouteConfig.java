package com.terekhin.development.Config;

import com.terekhin.development.Controllers.Controller;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class RouteConfig<T extends Controller> {
    private final Class<T> controller;
    private final String methodName;
    public RouteConfig(Class<T> controllerType) {
        this.controller = controllerType;
        this.methodName = "index";
    }
    public RouteConfig(Class<T> controllerType, String methodName) {
        this.controller = controllerType;
        this.methodName = methodName;
    }

    public Class<T> getController() {
        return controller;
    }

    public Method getMethod() throws NoSuchMethodException {
        return this.controller.getMethod(this.methodName, WebContext.class);
    }

    public T getInstance() throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        Constructor<T> ctr = this.controller.getConstructor();
        return (T) ctr.newInstance();
    }
}
