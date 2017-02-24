package com.lemon.commons.enm;

/**
 * Created by Administrator on 2016/6/21.
 */
public enum EnumShareType implements IEnum {


    Type_Group      (0,"group"),
    Type_Asset      (1,"asset");

    private int ch;
    private String value;
    private EnumShareType(int ch,String value){
        this.ch = ch;
        this.value = value;
    }
    @Override
    public String getValue(){
        return value;
    }
    @Override
    public int getCode(){
        return ch;
    }

    static {
        for(EnumShareType e : EnumShareType.values()){
            EnumCenter.registe(EnumShareType.class,e);
        }
    }
    public static String explainAsString(int code){
        return EnumCenter.explainAsString(EnumShareType.class,code);
    }
    public static EnumShareType explain(int code){
        IEnum ie = EnumCenter.explain(EnumShareType.class,code);
        if(ie == null){
            return null;
        }else {
            return (EnumShareType)ie;
        }
    }

}
