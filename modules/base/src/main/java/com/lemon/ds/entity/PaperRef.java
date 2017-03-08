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
@Table(name = "paper_ref")

public class PaperRef extends IdSerialEntity {
    //构造器方法
    public PaperRef() {

    }

    //成员变量
    @NotNull
    private Integer paper_id;
    @NotNull
    private Integer ref_no;
    @NotNull
    @Column(length = 2048)
    private String ref_full;
    @Column(length = 255)
    private String ref_doi;
    @Column(length = 1024)
    private String ref_authors;
    @Column(length = 255)
    private String ref_title;
    @Column(length = 255)
    private String ref_journal;
    @Column(length = 255)
    private String ref_volume;
    @Column(length = 255)
    private String ref_issue;
    private Integer ref_startpage;
    private Integer ref_endpage;

    //Get Method

    public Integer getPaper_id() {
        return paper_id;
    }

    public Integer getRef_no() {
        return ref_no;
    }

    public String getRef_full() {
        return ref_full;
    }

    public String getRef_doi() {
        return ref_doi;
    }

    public String getRef_authors() {
        return ref_authors;
    }

    public String getRef_title() {
        return ref_title;
    }

    public String getRef_journal() {
        return ref_journal;
    }

    public String getRef_volume() {
        return ref_volume;
    }

    public String getRef_issue() {
        return ref_issue;
    }

    public Integer getRef_startpage() {
        return ref_startpage;
    }

    public Integer getRef_endpage() {
        return ref_endpage;
    }

    //Set Method

    public void setPaper_id(Integer paper_id) {
        this.paper_id = paper_id;
    }

    public void setRef_no(Integer ref_no) {
        this.ref_no = ref_no;
    }

    public void setRef_full(String ref_full) {
        this.ref_full = ref_full;
    }

    public void setRef_doi(String ref_doi) {
        this.ref_doi = ref_doi;
    }

    public void setRef_authors(String ref_authors) {
        this.ref_authors = ref_authors;
    }

    public void setRef_title(String ref_title) {
        this.ref_title = ref_title;
    }

    public void setRef_journal(String ref_journal) {
        this.ref_journal = ref_journal;
    }

    public void setRef_volume(String ref_volume) {
        this.ref_volume = ref_volume;
    }

    public void setRef_issue(String ref_issue) {
        this.ref_issue = ref_issue;
    }

    public void setRef_startpage(Integer ref_startpage) {
        this.ref_startpage = ref_startpage;
    }

    public void setRef_endpage(Integer ref_endpage) {
        this.ref_endpage = ref_endpage;
    }
}
