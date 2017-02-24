/*******************************************************************************
 * Copyright (c) 2005, 2014 springside.github.io
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 *******************************************************************************/
package com.lemon.ds.dao;

import com.lemon.ds.base.dao.LemonRepo;
import com.lemon.ds.entity.PaperEmail;

public interface PaperEmailDao extends LemonRepo<PaperEmail, Integer> {

    PaperEmail findOneByPmcIdAndEmail(Integer pmcId, String email);
}