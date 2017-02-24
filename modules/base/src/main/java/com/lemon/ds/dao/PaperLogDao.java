/*******************************************************************************
 * Copyright (c) 2005, 2014 springside.github.io
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 *******************************************************************************/
package com.lemon.ds.dao;

import com.lemon.ds.base.dao.LemonRepo;
import com.lemon.ds.entity.PaperLog;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Date;

public interface PaperLogDao extends LemonRepo<PaperLog, Integer> {

	@Query(" select max(pmcId) from PaperLog where status='y' ")
	Integer findMaxId();

	@Query(" select max(pmcId) from PaperLog where status='y' and pmcId%:mode=:modleft and (pmcId between :start and :stop) ")
	Integer findMaxId(@Param("mode") Integer mode, @Param("modleft") Integer left, @Param("start") Integer start, @Param("stop") Integer stop);

	PaperLog findOneByPmcId(Integer pmcId);

	@Modifying
	@Query("update PaperLog set status=:status, complete=:complete, errMsg=:errMsg, ts=:ts where pmcId=:pmcId")
	void updateByPmcId(@Param("pmcId") Integer pmcId, @Param("status") Character status, @Param("complete") Integer complete, @Param("errMsg") String errMsg, @Param("ts") Date ts);
}