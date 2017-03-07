package com.lemon.ds.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.lemon.ds.base.entity.IdSerialEntity;
import com.lemon.ds.base.entity.LemonEntity;
import org.springside.modules.utils.Clock;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity
//@Table(name = "paper_log" , uniqueConstraints={@UniqueConstraint(columnNames={"pmcId"})})
@Table(name = "paper_log" )
public class PaperLog extends IdSerialEntity {
	//构造器方法
	public PaperLog() {
	}

	//成员变量
	private Integer paper_id;

	@Column(length = 255)
	private String source;

	@Column(length = 10248)
	private String url;

	@NotNull
	private Character status = 'y';

	@NotNull
	private Integer complete = 0;

	@Column(length = 255)
	private String status_msg = "OK";

	private Date ts = Clock.DEFAULT.getCurrentDate();

	// Custom Method
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

	//Get Method
	public Integer getPaper_id() {
		return paper_id;
	}

	public String getSource() {
		return source;
	}

	public String getUrl() {
		return url;
	}

	public Character getStatus() {
		return status;
	}

	public Integer getComplete() {
		return complete;
	}

	public String getStatus_msg() {
		return status_msg;
	}

	public Date getTs() {
		return ts;
	}

	//Set Method
	public void setPaper_id(Integer paper_id) {
		this.paper_id = paper_id;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public void setStatus(Character status) {
		this.status = status;
	}

	public void setComplete(Integer complete) {
		this.complete = complete;
	}

	public void setStatus_msg(String status_msg) {
		this.status_msg = status_msg;
	}

	public void setTs(Date ts) {
		this.ts = ts;
	}
}
/*
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
*/