package com.terekhin.development.helpers;

import org.thymeleaf.context.WebContext;

import javax.servlet.http.HttpServletRequest;

public class NotificationService extends Exception {
    public NotificationService(String message) {
        super(message);
    }

    public void show(WebContext webContext, NotificationType messageType)
    {
        Notification notification = new Notification(this.getMessage(),messageType.getType());
        webContext.setVariable("message",notification);
    }
}
