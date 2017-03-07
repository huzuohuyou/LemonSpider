package com.lemon.ds.entity;

import com.lemon.ds.base.entity.IdSerialEntity;
import jdk.nashorn.internal.scripts.JO;
import org.springside.modules.utils.Clock;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.xml.crypto.Data;
import java.util.Date;


/**
 * Created by Bimt on 2017/3/7.
 */
@Entity
@Table(name = "journal")

public class Journal extends IdSerialEntity{
    //构造器方法
    public Journal() {

    }

    //成员变量
    @NotNull
    private String journal_name;
    private String journal_name2;
    @NotNull
    private String issn;
    private String cn;
    @NotNull
    private Integer begin_year;
    @NotNull
    private Integer begin_period;
    @NotNull
    private Integer latest_year;
    @NotNull
    private Integer latest_volume;
    @NotNull
    private Integer latest_period;
    @NotNull
    private Integer volume_per_year;
    @NotNull
    private Date ts = Clock.DEFAULT.getCurrentDate();

    //Get Method
    public String getJournal_name() {
        return journal_name;
    }

    public String getJournal_name2() {
        return journal_name2;
    }

    public String getIssn() {
        return issn;
    }

    public String getCn() {
        return cn;
    }

    public Integer getBegin_year() {
        return begin_year;
    }

    public Integer getBegin_period() {
        return begin_period;
    }

    public Integer getLatest_year() {
        return latest_year;
    }

    public Integer getLatest_volume() {
        return latest_volume;
    }

    public Integer getLatest_period() {
        return latest_period;
    }

    public Integer getVolume_per_year() {
        return volume_per_year;
    }

    public Date getTs() {
        return ts;
    }

    //Set Method
    public void setJournal_name(String journal_name) {
        this.journal_name = journal_name;
    }

    public void setJournal_name2(String journal_name2) {
        this.journal_name2 = journal_name2;
    }

    public void setIssn(String issn) {
        this.issn = issn;
    }

    public void setCn(String cn) {
        this.cn = cn;
    }

    public void setBegin_year(Integer begin_year) {
        this.begin_year = begin_year;
    }

    public void setBegin_period(Integer begin_period) {
        this.begin_period = begin_period;
    }

    public void setLatest_year(Integer latest_year) {
        this.latest_year = latest_year;
    }

    public void setLatest_volume(Integer latest_volume) {
        this.latest_volume = latest_volume;
    }

    public void setLatest_period(Integer latest_period) {
        this.latest_period = latest_period;
    }

    public void setVolume_per_year(Integer volume_per_year) {
        this.volume_per_year = volume_per_year;
    }

    public void setTs(Date ts) {
        this.ts = ts;
    }
}
