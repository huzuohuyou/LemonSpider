package com.lemon.ds.base.service;

import org.springframework.stereotype.Service;

import com.lemon.ds.base.dao.LemonRepo;
@Service
public abstract class LemonServiceDefault<T, DAO extends LemonRepo<T, Integer>> extends LemonService<T, Integer, DAO> {
	
}
