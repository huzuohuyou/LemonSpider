package com.lemon.ds.entity;

import com.lemon.ds.base.entity.IdSerialEntity;
import org.springside.modules.utils.Clock;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;
import java.util.Date;

import static com.lemon.commons.enm.EnumSearchOperator.NotNull;

@Entity
@Table(name = "paper_asset", uniqueConstraints={@UniqueConstraint(columnNames={"pmcId", "imgPath"})})
public class PaperAsset extends IdSerialEntity {
	public final static int ST_OK = 1, ST_NO = 0;

	@NotNull
	private Integer pmcId;

	@NotNull
	private String imgPath;

	@NotNull
	private Integer status = ST_OK;

	@NotNull
	private Date ts = Clock.DEFAULT.getCurrentDate();


	public PaperAsset() {
	}

	public Integer getPmcId() {
		return pmcId;
	}

	public void setPmcId(Integer pmcId) {
		this.pmcId = pmcId;
	}


	public String getImgPath() {
		return imgPath;
	}

	public void setImgPath(String imgPath) {
		this.imgPath = imgPath;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Date getTs() {
		return ts;
	}

	public void setTs(Date ts) {
		this.ts = ts;
	}
}