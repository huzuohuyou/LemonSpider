package com.lemon.commons.enm;

import com.lemon.commons.enm.IEnum;

/**
 * Created by ywp on 16/6/3.
 */
public enum  EnumModulePermission implements IEnum {
    /**
     * 群的权限
     */
    Group   (1,"group"),
    /**
     * 课程的权限
     */
    Course  (2,"course"),
    /**
     * 全站的权限
     */
    Web     (3,"web"),

    /**
     * 节点的权限
     */
    PathNode(4,"node"),

    /**
     * 工作室的权限
     */
    Studio (5,"studio")
    ;

    int code;
    String value;
    private EnumModulePermission(int code,String value){
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
}
