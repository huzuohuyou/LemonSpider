package com.lemon.ds.entity;

import com.lemon.ds.base.entity.IdSerialEntity;
import com.lemon.ds.base.entity.LemonEntity;
import org.springside.modules.utils.Clock;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity
@Table(name = "paper")
public class Paper extends IdSerialEntity {
	private Integer pmId;
	@NotNull
	private Integer pmcId;	  //PMCID: PMC3125087
	//maybe null
	private String doi;
	private Integer nihmsId;  //NIHMSID: NIHMS301993

	@NotNull
	private String journal;
	@NotNull
	private String journalVolume;

	@NotNull
	private Integer pageBegin = 0;
	@NotNull
	private Integer pageEnd = 0;

	//maybe null
	private Date onlineDate;

	@NotNull
	private String title;	//Viruses as anticancer drugs

	@Column(length = 20480)
	private String abstr;

	//maybe null
	@Column(length = 10240)
	private String keyword; //json
	//maybe null 有些公众文章
	@Column(length = 10240)
	private String authors; //json

	@Column(length = 20480)
	private String authorOrgs;

	@NotNull
	private Integer sizeKB = 0;

	@NotNull
	private Date ts = Clock.DEFAULT.getCurrentDate();

	public Paper(int pmcId) {
		this.pmcId = pmcId;
	}

	public Paper() {

	}


	public Integer getPmId() {
		return pmId;
	}

	public void setPmId(Integer pmId) {
		this.pmId = pmId;
	}

	public Integer getPmcId() {
		return pmcId;
	}

	public void setPmcId(Integer pmcId) {
		this.pmcId = pmcId;
	}

	public String getDoi() {
		return doi;
	}

	public void setDoi(String doi) {
		this.doi = doi;
	}

	public Integer getNihmsId() {
		return nihmsId;
	}

	public void setNihmsId(Integer nihmsId) {
		this.nihmsId = nihmsId;
	}

	public String getJournal() {
		return journal;
	}

	public void setJournal(String journal) {
		this.journal = journal;
	}

	public String getJournalVolume() {
		return journalVolume;
	}

	public void setJournalVolume(String journalVolume) {
		this.journalVolume = journalVolume;
	}

	public Integer getPageBegin() {
		return pageBegin;
	}

	public void setPageBegin(Integer pageBegin) {
		this.pageBegin = pageBegin;
	}

	public Integer getPageEnd() {
		return pageEnd;
	}

	public void setPageEnd(Integer pageEnd) {
		this.pageEnd = pageEnd;
	}

	public Date getOnlineDate() {
		return onlineDate;
	}

	public void setOnlineDate(Date onlineDate) {
		this.onlineDate = onlineDate;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getAbstr() {
		return abstr;
	}

	public void setAbstr(String abstr) {
		this.abstr = abstr;
	}

	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

	public String getAuthors() {
		return authors;
	}

	public void setAuthors(String authors) {
		this.authors = authors;
	}

	public String getAuthorOrgs() {
		return authorOrgs;
	}

	public void setAuthorOrgs(String authorOrgs) {
		this.authorOrgs = authorOrgs;
	}

	public Integer getSizeKB() {
		return sizeKB;
	}

	public void setSizeKB(Integer sizeKB) {
		this.sizeKB = sizeKB;
	}

	public Date getTs() {
		return ts;
	}
	
	public void setTs(Date ts) {
		this.ts = ts;
	}
}