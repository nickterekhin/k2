package com.terekhin.development.domain;

import com.terekhin.development.helpers.ActionParser;

public enum TransactionTypesList {
    CHARGE(2),
    WITHDRAW(3),
    TRANSFER(4);
    private final int type;
    TransactionTypesList(int type) {
        this.type = type;
    }
    static public TransactionTypesList getResponseAction(int pType) {
        for (TransactionTypesList type: TransactionTypesList.values()) {
            if (type.getAction() == pType) {
                return type;
            }
        }
        return CHARGE;
    }

    public int getAction() {
        return type;
    }
}
