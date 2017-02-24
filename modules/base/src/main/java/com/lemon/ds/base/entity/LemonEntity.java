package com.lemon.ds.base.entity;

import javax.persistence.MappedSuperclass;

import org.apache.commons.lang3.builder.ToStringBuilder;

import com.lemon.commons.Lemon;

//JPA 基类的标识
@MappedSuperclass
public abstract class LemonEntity extends Lemon {
	
	public String toString() {
		return "Entity: " + ToStringBuilder.reflectionToString(this);
	}
}
