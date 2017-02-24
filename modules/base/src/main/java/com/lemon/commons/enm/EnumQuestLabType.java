package com.lemon.commons.enm;

/**
 * Created by ywp on 15/12/1.
 */
public enum EnumQuestLabType implements IEnum {
    MultipleChoiceSingle    (1,"单选题"),
    MultipleChoiceMultiple  (2,"多选题"),
    YesOrNo                 (3,"是非题"),
    BlankFilling            (4,"填空题"),
    AskAndAnswer            (5,"解答题");

    private Integer code;
    private String value;
    private EnumQuestLabType(Integer code,String value){
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


    static {
        for(EnumQuestLabType e : EnumQuestLabType.values()) {
            EnumCenter.registe(EnumQuestLabType.class, e);
        }
    }

    public static String explainAsString(int code) {
        IEnum ie = EnumCenter.explain(EnumQuestLabType.class, code);
        if(ie == null) {
            return null;
        } else {
            return ie.getValue();
        }
    }
}
