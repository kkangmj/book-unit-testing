package com.mango.bookunittesting.ch8_6_2.v2;

public enum UserType {
    Customer(1), Employee(2);

    private final int value;

    UserType(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
