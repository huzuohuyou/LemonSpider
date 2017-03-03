package com.lemon.ds.entity;

import com.lemon.ds.base.entity.IdSerialEntity;
import org.springside.modules.utils.Clock;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity
@Table(name = "paper")
public class Paper extends IdSerialEntity {
//	@NotNull
//	private Integer Id;
	@NotNull
	private String title;
	@NotNull
	private Integer sizeKB = 0;
	private Integer fulltext;
	@Column(length = 204800)
	private String abstr;
	@Column(length = 10240)
	private String keyword;
	private String abstr_cn;
	private String keyword_cn;
	private String doi;
	private Integer nihms_id;
	private String source;
	private String url_abstract;
	private String url_fulltext;
	private Integer source_id;
	private String source_id_str;
	private Integer pm_id;
	@NotNull
	private String journal;
	@NotNull
	private Integer journal_year;
	private String journal_volume;
	private String journal_no;
	@NotNull
	private Integer page_begin = 0;
	@NotNull
	private Integer page_end = 0;
	@Column(length = 10240)
	private String authors; //json
	@Column(length = 20480)
	private String author_orgs;
	@NotNull
	private Date ts = Clock.DEFAULT.getCurrentDate();

	//@Override
//	public Integer getId() {
//		return Id;
//	}

//	@Override
//	public void setId(Integer id) {
//		Id = id;
//	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Integer getSizeKB() {
		return sizeKB;
	}

	public void setSizeKB(Integer sizeKB) {
		this.sizeKB = sizeKB;
	}

	public Integer getFulltext() {
		return fulltext;
	}

	public void setFulltext(Integer fulltext) {
		this.fulltext = fulltext;
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

	public String getAbstr_cn() {
		return abstr_cn;
	}

	public void setAbstr_cn(String abstr_cn) {
		this.abstr_cn = abstr_cn;
	}

	public String getKeyword_cn() {
		return keyword_cn;
	}

	public void setKeyword_cn(String keyword_cn) {
		this.keyword_cn = keyword_cn;
	}

	public String getDoi() {
		return doi;
	}

	public void setDoi(String doi) {
		this.doi = doi;
	}

	public Integer getNihms_id() {
		return nihms_id;
	}

	public void setNihms_id(Integer nihms_id) {
		this.nihms_id = nihms_id;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public String getUrl_abstract() {
		return url_abstract;
	}

	public void setUrl_abstract(String url_abstract) {
		this.url_abstract = url_abstract;
	}

	public String getUrl_fulltext() {
		return url_fulltext;
	}

	public void setUrl_fulltext(String url_fulltext) {
		this.url_fulltext = url_fulltext;
	}

	public Integer getSource_id() {
		return source_id;
	}

	public void setSource_id(Integer source_id) {
		this.source_id = source_id;
	}

	public String getSource_id_str() {
		return source_id_str;
	}

	public void setSource_id_str(String source_id_str) {
		this.source_id_str = source_id_str;
	}

	public Integer getPm_id() {
		return pm_id;
	}

	public void setPm_id(Integer pm_id) {
		this.pm_id = pm_id;
	}

	public String getJournal() {
		return journal;
	}

	public void setJournal(String journal) {
		this.journal = journal;
	}

	public Integer getJournal_year() {
		return journal_year;
	}

	public void setJournal_year(Integer journal_year) {
		this.journal_year = journal_year;
	}

	public String getJournal_volume() {
		return journal_volume;
	}

	public void setJournal_volume(String journal_volume) {
		this.journal_volume = journal_volume;
	}

	public String getJournal_no() {
		return journal_no;
	}

	public void setJournal_no(String journal_no) {
		this.journal_no = journal_no;
	}

	public Integer getPage_begin() {
		return page_begin;
	}

	public void setPage_begin(Integer page_begin) {
		this.page_begin = page_begin;
	}

	public Integer getPage_end() {
		return page_end;
	}

	public void setPage_end(Integer page_end) {
		this.page_end = page_end;
	}

	public String getAuthors() {
		return authors;
	}

	public void setAuthors(String authors) {
		this.authors = authors;
	}

	public String getAuthor_orgs() {
		return author_orgs;
	}

	public void setAuthor_orgs(String author_orgs) {
		this.author_orgs = author_orgs;
	}

	public Date getTs() {
		return ts;
	}

	public void setTs(Date ts) {
		this.ts = ts;
	}








	public Paper(int id) {
		this.id = id;
	}

	public Paper() {

	}


}