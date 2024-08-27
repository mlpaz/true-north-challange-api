package com.test.demo.api.model.enums;

public enum Status {

    ACTIVE("ACTIVE"), INACTIVE("INACTIVE");

    public final String label;

    private Status(String label) {
        this.label = label;
    }
}
