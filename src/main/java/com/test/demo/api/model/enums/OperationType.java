package com.test.demo.api.model.enums;

public enum OperationType {

    ADDITION("ADDITION"), SUBTRACTION("SUBTRACTION"), MULTIPLICATION("MULTIPLICATION"),
    DIVISION("DIVISION"), SQUARE_ROOT("SQUARE_ROOT"), RANDOM_STRING("RANDOM_STRING");

    public final String label;

    private OperationType(String label) {
        this.label = label;
    }
}
