package com.lemon.ds.entity;

import com.lemon.ds.base.entity.IdSerialEntity;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "author_papers", uniqueConstraints={@UniqueConstraint(columnNames={"authorId", "pmcId", "paperId"})})
public class AuthorPapers extends IdSerialEntity {
	@NotNull
	private Integer authorId;

	@NotNull
	private Integer pmcId = 0;

	@NotNull
	private Integer paperId;

	@NotNull
	private Integer rank = 1;


	public AuthorPapers() {
	}


	public Integer getAuthorId() {
		return authorId;
	}

	public void setAuthorId(Integer authorId) {
		this.authorId = authorId;
	}

	public Integer getPmcId() {
		return pmcId;
	}

	public void setPmcId(Integer pmcId) {
		this.pmcId = pmcId;
	}

	public Integer getPaperId() {
		return paperId;
	}

	public void setPaperId(Integer paperId) {
		this.paperId = paperId;
	}

	public Integer getRank() {
		return rank;
	}

	public void setRank(Integer rank) {
		this.rank = rank;
	}
}