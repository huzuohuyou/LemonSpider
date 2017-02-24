package com.lemon.ds.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.lemon.ds.base.entity.IdSerialEntity;
import com.lemon.ds.base.entity.LemonEntity;
import org.springside.modules.utils.Clock;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import java.util.Date;

@Entity
@Table(name = "paper_log" , uniqueConstraints={@UniqueConstraint(columnNames={"pmcId"})})
public class PaperLog extends IdSerialEntity {
	private Integer pmcId;
	private Character status = 'y';
	private int complete = 0;
	private String errMsg = "OK";
	private Date ts = Clock.DEFAULT.getCurrentDate();

	public PaperLog() {
	}

	public int onAbstractOK() {
		complete |= 1;
		return complete;
	}

	public int onPdfOK() {
		complete |= 2;
		return complete;
	}

	public int onHtmlOK() {
		complete |= 4;
		return complete;
	}

	public int onAllOK() {
		complete = 7;
		return complete;
	}

	public Integer getPmcId() {
		return pmcId;
	}

	public void setPmcId(Integer pmcId) {
		this.pmcId = pmcId;
	}

	public Character getStatus() {
		return status;
	}
	
	public void setStatus(Character status) {
		this.status = status;
	}

	public int getComplete() {
		return complete;
	}

	public void setComplete(int complete) {
		this.complete = complete;
	}

	public String getErrMsg() {
		return errMsg;
	}

	public void setErrMsg(String errMsg) {
		this.errMsg = errMsg;
	}

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+08:00")
	public Date getTs() {
		return ts;
	}

	public void setTs(Date ts) {
		this.ts = ts;
	}
}