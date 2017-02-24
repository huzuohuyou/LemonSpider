package com.lemon.commons.enm;

/**
 * Created by ywp on 15/11/19.
 */
public enum EnumUserChangeStatus implements IEnum {
    Status_School(1,"school"),
    Status_Segment(2,"segment")
    ;

    private int ch;
    private String value;
    private EnumUserChangeStatus(int ch, String value) {
        this.ch = ch;
        this.value = value;
    }

    @Override
    public String getValue() {
        return value;
    }

    @Override
    public int getCode() {
        return ch;
    }
}
