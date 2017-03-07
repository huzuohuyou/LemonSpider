/*******************************************************************************
 * Copyright (c) 2005, 2014 springside.github.io
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 *******************************************************************************/
package com.lemon.ds.dao;

import com.lemon.ds.base.dao.LemonRepo;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface AuthorDao extends LemonRepo<PaperAuthor, Integer> {

    @Query("from PaperAuthor where author_name=:name")
    List<PaperAuthor> findByAuthorName(@Param("name") String name);
}