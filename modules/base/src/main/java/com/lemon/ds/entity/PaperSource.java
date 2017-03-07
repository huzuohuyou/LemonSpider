package com.lemon.ds.entity;

import com.lemon.ds.base.entity.IdSerialEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Created by Bimt on 2017/3/7.
 */
@Entity
@Table(name = "paper_source")

public class PaperSource extends IdSerialEntity{
    //构造器方法
    public PaperSource() {

    }

    //成员变量
    private Integer paper_id;
    @Column(length = 255)
    private String source;
    @Column(length = 4096)
    private String url_abstract;
    @Column(length = 4096)
    private String url_fulltext;
    private Integer source_id;
    @Column(length = 255)
    private String source_id_str;
    @Column(length = 102400)
    private String authors;
    @Column(length = 204800)
    private String author_orgs;
    private String rawdata;
    private String rawdata2;


    //Get Method
    public Integer getPaper_id() {
        return paper_id;
    }

    public String getSource() {
        return source;
    }

    public String getUrl_abstract() {
        return url_abstract;
    }

    public String getUrl_fulltext() {
        return url_fulltext;
    }

    public Integer getSource_id() {
        return source_id;
    }

    public String getSource_id_str() {
        return source_id_str;
    }

    public String getAuthors() {
        return authors;
    }

    public String getAuthor_orgs() {
        return author_orgs;
    }

    public String getRawdata() {
        return rawdata;
    }

    public String getRawdata2() {
        return rawdata2;
    }

    //Set Method
    public void setPaper_id(Integer paper_id) {
        this.paper_id = paper_id;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public void setUrl_abstract(String url_abstract) {
        this.url_abstract = url_abstract;
    }

    public void setUrl_fulltext(String url_fulltext) {
        this.url_fulltext = url_fulltext;
    }

    public void setSource_id(Integer source_id) {
        this.source_id = source_id;
    }

    public void setSource_id_str(String source_id_str) {
        this.source_id_str = source_id_str;
    }

    public void setAuthors(String authors) {
        this.authors = authors;
    }

    public void setAuthor_orgs(String author_orgs) {
        this.author_orgs = author_orgs;
    }

    public void setRawdata(String rawdata) {
        this.rawdata = rawdata;
    }

    public void setRawdata2(String rawdata2) {
        this.rawdata2 = rawdata2;
    }

}
