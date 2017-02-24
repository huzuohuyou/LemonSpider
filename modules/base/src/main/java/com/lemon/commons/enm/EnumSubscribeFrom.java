package com.lemon.commons.enm;

/**
 * Created by ywp on 16/2/5.
 */
public enum  EnumSubscribeFrom implements IEnum {
    Direct		(1,"直接订阅"),
    Clazz	    (2,"加入班级"),
    Other		        (9999,"其它");

    private int code;
    private String value;
    private EnumSubscribeFrom(int code, String value) {
        this.code = code;
        this.value = value;
    }

    @Override
    public int getCode() {
        return code;
    }

    @Override
    public String getValue() {
        return value;
    }

    //------------------static methods -------------------------
    static {
        for(EnumSubscribeFrom e : EnumSubscribeFrom.values()) {
            EnumCenter.registe(EnumSubscribeFrom.class, e);
        }
    }

    public static String explainAsString(int code) {
        return EnumCenter.explainAsString(EnumSubscribeFrom.class, code);
    }
}
