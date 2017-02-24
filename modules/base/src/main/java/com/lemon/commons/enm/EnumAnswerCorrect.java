package com.lemon.commons.enm;

/**
 * Created by ywp on 15/12/4.
 */
public enum  EnumAnswerCorrect implements IEnum {
     Wrong     (0,"错误"),
     Fix       (1,"漏选"),
     Correct   (2,"正确")
    ;

    private EnumAnswerCorrect(Integer code,String value){
        this.code = code;
        this.value = value;
    }

    private  Integer code;
    private String value;
    @Override
    public int getCode() {
        return code;
    }

    @Override
    public String getValue() {
        return value;
    }
}
