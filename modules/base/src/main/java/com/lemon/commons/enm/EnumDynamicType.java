package com.lemon.commons.enm;

/**
 * Created by Administrator on 2016/6/7.
 */
public enum EnumDynamicType implements IEnum{
    Publish         (1,"发布资源"),
    Student         (2,"班级学生"),
    Subscribe       (3,"订阅人数");

    private int ch;
    private String value;
    private EnumDynamicType(int ch,String value){
        this.ch = ch;
        this.value = value;
    }
    @Override
    public int getCode(){
        return ch;
    }
    @Override
    public String getValue(){
        return value;
    }

    //------------------static methods -------------------------
    static {
        for(EnumActiveOptions e : EnumActiveOptions.values()) {
            EnumCenter.registe(EnumActiveOptions.class, e);
        }
    }

    public static String explainAsString(int code) {
        return EnumCenter.explainAsString(EnumActiveOptions.class, code);
    }

    public static EnumActiveOptions explain(int code) {
        IEnum ie = EnumCenter.explain(EnumActiveOptions.class, code);
        if(ie == null) {
            return null;
        } else {
            return (EnumActiveOptions)ie;
        }
    }
}
