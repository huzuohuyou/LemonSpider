package com.lemon.ds.entity;

import com.lemon.ds.base.entity.IdSerialEntity;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "paper_email", uniqueConstraints={@UniqueConstraint(columnNames={"pmcId", "email"})})
public class PaperEmail extends IdSerialEntity {
	@NotNull
	private Integer pmcId;
	@NotNull
	private String email;

	public PaperEmail() {
	}

	public Integer getPmcId() {
		return pmcId;
	}

	public void setPmcId(Integer pmcId) {
		this.pmcId = pmcId;
	}

	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
}