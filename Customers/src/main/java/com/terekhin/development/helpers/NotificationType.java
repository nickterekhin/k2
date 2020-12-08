package com.terekhin.development.helpers;

public enum NotificationType {
    ERROR("alert-danger"),
    INFO("alert-info"),
    WARNING("alert-warning"),
    SUCCESS("alert-success");

    private String type;
    NotificationType(String type) {
        this.type = type;
    }

    static public NotificationType getNotificationType(String type)
    {
        for(NotificationType nType: NotificationType.values())
        {
            if(nType.getType().equals(type))
            {
                return nType;
            }
        }
        return WARNING;
    }

    public String getType() {
        return type;
    }
}
