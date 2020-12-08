package com.terekhin.development.helpers;

import javax.servlet.http.HttpServletRequest;

public class NotificationService extends Exception {
    public NotificationService(String message) {
        super(message);
    }

    public void show(HttpServletRequest req, NotificationType messageType)
    {
        Notification notification = new Notification(this.getMessage(),messageType.getType());
        req.setAttribute("message",notification);
    }
}
