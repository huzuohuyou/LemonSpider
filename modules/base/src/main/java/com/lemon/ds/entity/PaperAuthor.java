package com.lemon.ds.entity;

import com.lemon.ds.base.entity.IdSerialEntity;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;

import static com.lemon.commons.enm.EnumSearchOperator.NotNull;

@Entity
@Table(name = "paper_author")
public class PaperAuthor extends IdSerialEntity {
//	@NotNull
//	private Integer id;
	@NotNull
	private Integer paper_id;
	@NotNull
	private String author_name;
	private String author_email;
	@NotNull
	private char status;
	private String extra;

	public PaperAuthor() {
	}

//	public void setId(String id) {
//		this.id = id;
//	}

	public Integer getPaper_id() {
		return paper_id;
	}

	public void setPaper_id(Integer paper_id) {
		this.paper_id = paper_id;
	}

	public String getAuthor_name() {
		return author_name;
	}

	public void setAuthor_name(String author_name) {
		this.author_name = author_name;
	}

	public String getAuthor_email() {
		return author_email;
	}

	public void setAuthor_email(String author_email) {
		this.author_email = author_email;
	}

	public char getStatus() {
		return status;
	}

	public void setStatus(char status) {
		this.status = status;
	}

	public String getExtra() {
		return extra;
	}

	public void setExtra(String extra) {
		this.extra = extra;
	}




}