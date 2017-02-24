package com.lemon.commons.enm;

/**
 * Created by ywp on 15/12/23.
 */
public enum  EnumClazzMode implements IEnum {
    Normal(1,"普通"),
    Stage(2,"闯关模式")
    ;

    private EnumClazzMode(Integer code,String value){
        this.code = code;
        this.value = value;
    }

    Integer code;
    String value;
    @Override
    public int getCode() {
        return code;
    }

    @Override
    public String getValue() {
        return value;
    }
}
