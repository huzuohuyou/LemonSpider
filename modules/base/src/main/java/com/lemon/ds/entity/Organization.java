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
@Table(name = "organization")

public class Organization extends IdSerialEntity {
    //构造器方法
    public Organization() {

    }

    //成员变量
    @Column(length = 2048)
    @NotNull
    private String unit_full;
    @Column(length = 2048)
    private String unit_full2;
    @Column(length = 1024)
    private String organization;
    @Column(length = 1024)
    private String organization2;
    @Column(length = 1024)
    private String laboratory;
    @Column(length = 1024)
    private String laboratory2;
    @Column(length = 1024)
    private String city;
    @Column(length = 1024)
    private String city2;
    @Column(length = 1024)
    private String institute;
    @Column(length = 1024)
    private String institute2;

    //Get Method
    public String getUnit_full() {
        return unit_full;
    }

    public String getUnit_full2() {
        return unit_full2;
    }

    public String getOrganization() {
        return organization;
    }

    public String getOrganization2() {
        return organization2;
    }

    public String getLaboratory() {
        return laboratory;
    }

    public String getLaboratory2() {
        return laboratory2;
    }

    public String getCity() {
        return city;
    }

    public String getCity2() {
        return city2;
    }

    public String getInstitute() {
        return institute;
    }

    public String getInstitute2() {
        return institute2;
    }

    //Set Method
    public void setUnit_full(String unit_full) {
        this.unit_full = unit_full;
    }

    public void setUnit_full2(String unit_full2) {
        this.unit_full2 = unit_full2;
    }

    public void setOrganization(String organization) {
        this.organization = organization;
    }

    public void setOrganization2(String organization2) {
        this.organization2 = organization2;
    }

    public void setLaboratory(String laboratory) {
        this.laboratory = laboratory;
    }

    public void setLaboratory2(String laboratory2) {
        this.laboratory2 = laboratory2;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setCity2(String city2) {
        this.city2 = city2;
    }

    public void setInstitute(String institute) {
        this.institute = institute;
    }

    public void setInstitute2(String institute2) {
        this.institute2 = institute2;
    }
}
