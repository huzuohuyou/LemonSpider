package com.lemon.commons.enm;

/**
 * Created by ywp on 15/12/23.
 */
public enum  EnumOrgType implements IEnum {
    TeacherStudio (1,"名师工作室"),
    TrainOrg      (2,"培训机构") ,
    Org           (3,"组织机构"),
    School        (4,"学校")
    ;

    private EnumOrgType(Integer code,String value){
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
