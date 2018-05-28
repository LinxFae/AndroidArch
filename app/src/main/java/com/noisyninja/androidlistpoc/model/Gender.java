package com.noisyninja.androidlistpoc.model;

public enum Gender {
    ALL(0),
    MALE(1),
    FEMALE(2);

    private int value;

    private Gender(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public Gender next() {
        switch (value) {
            case 0:
                return MALE;
            case 1:
                return FEMALE;
            default:
                return ALL;
        }
    }
}
