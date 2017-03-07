package com.lemon.ds.entity;

import javax.persistence.Entity;
import javax.persistence.Table;
import com.lemon.ds.base.entity.IdSerialEntity;
import org.springside.modules.utils.Clock;
import javax.persistence.Column;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity
@Table(name = "paper")
public class Paper extends  IdSerialEntity {
	//构造器方法
	public Paper() {

	}
	//成员变量
	@NotNull
	@Column(length = 1024)
	private String article_title;

	@Column(length = 1024)
	private String article_title2;

	@NotNull
	private Integer sizekb;

	@NotNull
	@Column(length = 1224)
	private String mid;

	@NotNull
	private Integer midhash;

	@Column(length = 1024)
	private String fulltext_u_r_l;

	@Column(length = 204800)
	private String tabloid;

	@Column(length = 204800)
	private String tabloid2;

	@Column(length = 102400)
	private String keywords;

	@Column(length = 102400)
	private String keywords2;

	@Column(length = 1024)
	private String doi;

	private Integer nihms_id;

	private Integer pm_id;

	@NotNull
	private Integer data_mask;

	@NotNull
	private String language_category;

	@NotNull
	private Integer journal_id;

	@NotNull
	private Integer year;

	@NotNull
	@Column(length = 255)
	private String volume;

	@NotNull
	@Column(length = 255)
	private String period;

	@NotNull
	private Integer page_start;

	@NotNull
	private Integer page_end;

	private Date online_date = Clock.DEFAULT.getCurrentDate();

	@NotNull
	private Date ts = Clock.DEFAULT.getCurrentDate();

	//Get Method
	public String getArticle_title() {
		return article_title;
	}

	public String getArticle_title2() {
		return article_title2;
	}

	public Integer getSizekb() {
		return sizekb;
	}

	public String getMid() {
		return mid;
	}

	public Integer getMidhash() {
		return midhash;
	}

	public String getFulltext_u_r_l() {
		return fulltext_u_r_l;
	}

	public String getTabloid() {
		return tabloid;
	}

	public String getTabloid2() {
		return tabloid2;
	}

	public String getKeywords() {
		return keywords;
	}

	public String getKeywords2() {
		return keywords2;
	}

	public String getDoi() {
		return doi;
	}

	public Integer getNihms_id() {
		return nihms_id;
	}

	public Integer getPm_id() {
		return pm_id;
	}

	public Integer getData_mask() {
		return data_mask;
	}

	public String getLanguage_category() {
		return language_category;
	}

	public Integer getJournal_id() {
		return journal_id;
	}

	public Integer getYear() {
		return year;
	}

	public String getVolume() {
		return volume;
	}

	public String getPeriod() {
		return period;
	}

	public Integer getPage_start() {
		return page_start;
	}

	public Integer getPage_end() {
		return page_end;
	}

	public Date getOnline_date() {
		return online_date;
	}

	public Date getTs() {
		return ts;
	}

	//Set Method
	public void setArticle_title(String article_title) {
		this.article_title = article_title;
	}

	public void setArticle_title2(String article_title2) {
		this.article_title2 = article_title2;
	}

	public void setSizekb(Integer sizekb) {
		this.sizekb = sizekb;
	}

	public void setMid(String mid) {
		this.mid = mid;
	}

	public void setMidhash(Integer midhash) {
		this.midhash = midhash;
	}

	public void setFulltext_u_r_l(String fulltext_u_r_l) {
		this.fulltext_u_r_l = fulltext_u_r_l;
	}

	public void setTabloid(String tabloid) {
		this.tabloid = tabloid;
	}

	public void setTabloid2(String tabloid2) {
		this.tabloid2 = tabloid2;
	}

	public void setKeywords(String keywords) {
		this.keywords = keywords;
	}

	public void setKeywords2(String keywords2) {
		this.keywords2 = keywords2;
	}

	public void setDoi(String doi) {
		this.doi = doi;
	}

	public void setNihms_id(Integer nihms_id) {
		this.nihms_id = nihms_id;
	}

	public void setPm_id(Integer pm_id) {
		this.pm_id = pm_id;
	}

	public void setData_mask(Integer data_mask) {
		this.data_mask = data_mask;
	}

	public void setLanguage_category(String language_category) {
		this.language_category = language_category;
	}

	public void setJournal_id(Integer journal_id) {
		this.journal_id = journal_id;
	}

	public void setYear(Integer year) {
		this.year = year;
	}

	public void setVolume(String volume) {
		this.volume = volume;
	}

	public void setPeriod(String period) {
		this.period = period;
	}

	public void setPage_start(Integer page_start) {
		this.page_start = page_start;
	}

	public void setPage_end(Integer page_end) {
		this.page_end = page_end;
	}

	public void setOnline_date(Date online_date) {
		this.online_date = online_date;
	}

	public void setTs(Date ts) {
		this.ts = ts;
	}
}


/*package com.lemon.ds.entity;


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
*/