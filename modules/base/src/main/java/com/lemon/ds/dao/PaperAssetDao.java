/*******************************************************************************
 * Copyright (c) 2005, 2014 springside.github.io
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 *******************************************************************************/
package com.lemon.ds.dao;

import com.lemon.ds.base.dao.LemonRepo;
import com.lemon.ds.entity.PaperAsset;

public interface PaperAssetDao extends LemonRepo<PaperAsset, Integer> {

//    @Query(" from PaperAsset where pmcId=:pmcId and imgPath=:imgPath ")
//    PaperAsset findOneByPmcIdAndImgPath(@Param("pmcId") Integer pmcId, @Param("imgPath") String imgPath);

    PaperAsset findOneByPmcIdAndImgPath(Integer pmcId, String imgPath);
}