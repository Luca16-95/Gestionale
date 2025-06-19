package com.dashboard.dati.model.enums;

public enum InvoiceType {
    INCOME,
    EXPENSE;

    public String getAuthority() {
        return this.name();
    }
}