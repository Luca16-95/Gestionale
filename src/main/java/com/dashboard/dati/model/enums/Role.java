package com.dashboard.dati.model.enums;

public enum Role {
    ADMIN,
    USER,
    EMPLOYEE,
    MANAGER,
    AUDITOR;

    public String getAuthority() {
        return this.name();
    }
}
