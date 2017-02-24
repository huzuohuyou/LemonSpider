package com.lemon.commons.enm;

/**
 * Created by ywp on 15/11/4.
 */
public enum EnumCommentType implements IEnum {
    Comment(1,"评论"),
    Question(2,"提问"),
    Exam(3,"考试")
    ;
    private int code;
    private String value;
    private EnumCommentType(int code, String value) {
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
        for(EnumCommentType e : EnumCommentType.values()) {
            EnumCenter.registe(EnumCommentType.class, e);
        }
    }

    public static String explainAsString(int code) {
        return EnumCenter.explainAsString(EnumCommentType.class, code);
    }
}
