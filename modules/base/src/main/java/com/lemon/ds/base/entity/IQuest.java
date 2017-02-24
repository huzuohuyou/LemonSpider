package com.lemon.ds.base.entity;

import java.util.Date;

public interface IQuest {
	
	public Integer getId();
	
	public Integer getOldid();

	public void setOldid(Integer oldid);

	public String getXmlCategory();

	public void setXmlCategory(String xmlCategory);

	public Integer getChildNum();

	public void setChildNum(Integer childNum);

	public String getQuesFrom();

	public void setQuesFrom(String quesFrom);

	public String getGuid();

	public void setGuid(String guid);
	
	public String getGuidVideo();
	
	public void setGuidVideo(String guidVideo);

	public Integer getQid();

	public void setQid(Integer qid);

	public Integer getKnowledgeId();

	public void setKnowledgeId(Integer knowledgeId);

	public Integer getQuesDiff();

	public void setQuesDiff(Integer quesDiff);

	public String getQuesAbility();

	public void setQuesAbility(String quesAbility);

	public String getQuesAnswer();

	public void setQuesAnswer(String quesAnswer);

	public String getQuesBody();

	public void setQuesBody(String quesBody);

	public String getQuesParse();

	public void setQuesParse(String quesParse);

	public String getQuesType();

	public void setQuesType(String quesType);

	public String getTitle();

	public void setTitle(String title);

	public Integer getQuesAbilityId();

	public void setQuesAbilityId(Integer quesAbilityId);

	public Integer getQuesTypeId();

	public void setQuesTypeId(Integer quesTypeId);

	public Boolean getIsSelected();

	public void setIsSelected(Boolean isSelected);

	public Integer getSubject();

	public void setSubject(Integer subject);

	public Integer getKd();

	public void setKd(Integer kd);

	public Integer getOutSubject();

	public void setOutSubject(Integer outSubject);

	public Integer getOutPage();

	public void setOutPage(Integer outPage);

	public Character getStatus();

	public void setStatus(Character status);

	public Date getTs();

	public void setTs(Date ts);

	public String getUrl();

	public Integer getLockUserId();

	public void setLockUserId(Integer lockUserId) ;
}