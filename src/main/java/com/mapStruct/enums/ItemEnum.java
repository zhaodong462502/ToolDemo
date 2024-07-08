package com.mapStruct.enums;

public enum ItemEnum {
    ONE_ONE(1),
    TWO_TWO(2),
    THREE_THREE(3)
    ;
    private int value;

    ItemEnum(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }
}
