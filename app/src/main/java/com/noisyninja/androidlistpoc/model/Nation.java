package com.noisyninja.androidlistpoc.model;

public enum Nation {
    ALL(0),
    US(1),
    DK(2),
    FR(3),
    GB(4);

    private int value;

    private Nation(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public Nation next() {
        switch (value) {
            case 0:
                return US;
            case 1:
                return DK;
            case 2:
                return FR;
            case 3:
                return GB;
            default:
                return ALL;
        }
    }

}
