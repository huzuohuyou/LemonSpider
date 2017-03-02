package com.lemon.ds.entity;

import com.lemon.ds.base.entity.IdSerialEntity;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;

import static com.lemon.commons.enm.EnumSearchOperator.NotNull;

@Entity
@Table(name = "author", uniqueConstraints={@UniqueConstraint(columnNames={"name", "email", "address"})})
public class Author extends IdSerialEntity {
	@NotNull
	private String id;
	@NotNull
	private String paper_id;
	@NotNull
	private String author_name;
	private String author_email;

	private String status;

	private String extra;




	public Author() {
	}

	

	public void setId(String id) {
		this.id = id;
	}

	public String getPaper_id() {
		return paper_id;
	}

	public void setPaper_id(String paper_id) {
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

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getExtra() {
		return extra;
	}

	public void setExtra(String extra) {
		this.extra = extra;
	}




}