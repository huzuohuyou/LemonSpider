package com.lemon.ds.entity;

import com.lemon.ds.base.entity.IdSerialEntity;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

/**
 * Created by Bimt on 2017/3/7.
 */
@Entity
@Table(name = "paper_author_org")

public class PaperAuthorOrg extends IdSerialEntity {
    //构造器方法
    public PaperAuthorOrg() {

    }

    //成员变量
    @NotNull
    private Integer paper_author_id;
    @NotNull
    private Integer org_id;
    @NotNull
    private Integer unit_no;


    //Get Method
    public Integer getPaper_author_id() {
        return paper_author_id;
    }

    public Integer getOrg_id() {
        return org_id;
    }

    public Integer getUnit_no() {
        return unit_no;
    }

    //Set Method

    public void setPaper_author_id(Integer paper_author_id) {
        this.paper_author_id = paper_author_id;
    }

    public void setOrg_id(Integer org_id) {
        this.org_id = org_id;
    }

    public void setUnit_no(Integer unit_no) {
        this.unit_no = unit_no;
    }

}
