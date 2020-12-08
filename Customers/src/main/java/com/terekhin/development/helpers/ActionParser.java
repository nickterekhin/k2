package com.terekhin.development.helpers;

public enum ActionParser {
    ADD("add"),
    EDIT("edit"),
    DELETE("delete"),
    LIST("list");

    private final String action;
    ActionParser(String action) {
        this.action = action;
    }
    static public ActionParser getResponseAction(String pType) {
        for (ActionParser type: ActionParser.values()) {
            if (type.getAction().equals(pType)) {
                return type;
            }
        }
        return LIST;
    }

    public String getAction() {
        return action;
    }
}
