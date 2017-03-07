package com.lemon.ds.entity;

import com.lemon.ds.base.entity.IdSerialEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

/**
 * Created by Bimt on 2017/3/7.
 */
@Entity
@Table(name = "paper_author")
public class PaperAuthor extends IdSerialEntity {
    //构造器方法
    public PaperAuthor() {

    }

    //成员变量
    @NotNull
    private Integer paper_id;
    @NotNull
    private Integer author_sno;
    @NotNull
    @Column(length = 255)
    private String author_name;
    @Column(length = 255)
    private String author_name2;
    @NotNull
    private Integer author_corres;
    @Column(length = 255)
    private String email;


    //Get Method
    public Integer getPaper_id() {
        return paper_id;
    }

    public Integer getAuthor_sno() {
        return author_sno;
    }

    public String getAuthor_name() {
        return author_name;
    }

    public String getAuthor_name2() {
        return author_name2;
    }

    public Integer getAuthor_corres() {
        return author_corres;
    }

    public String getEmail() {
        return email;
    }

    //Set Method
    public void setPaper_id(Integer paper_id) {
        this.paper_id = paper_id;
    }

    public void setAuthor_sno(Integer author_sno) {
        this.author_sno = author_sno;
    }

    public void setAuthor_name(String author_name) {
        this.author_name = author_name;
    }

    public void setAuthor_name2(String author_name2) {
        this.author_name2 = author_name2;
    }

    public void setAuthor_corres(Integer author_corres) {
        this.author_corres = author_corres;
    }

    public void setEmail(String email) {
        this.email = email;
    }

}
