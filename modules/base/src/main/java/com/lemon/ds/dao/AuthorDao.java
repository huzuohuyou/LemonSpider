/*******************************************************************************
 * Copyright (c) 2005, 2014 springside.github.io
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 *******************************************************************************/
package com.lemon.ds.dao;

import com.lemon.ds.base.dao.LemonRepo;
import com.lemon.ds.entity.Author;

import java.util.List;

public interface AuthorDao extends LemonRepo<Author, Integer> {

    List<Author> findByNameAndAddress(String name, String address);
}