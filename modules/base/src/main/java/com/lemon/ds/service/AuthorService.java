package com.lemon.ds.service;

import com.lemon.ds.base.service.LemonServiceDefault;
import com.lemon.ds.dao.AuthorDao;
import com.lemon.ds.entity.PaperAuthor;
import com.lemon.ds.entity.PaperAuthor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@Transactional
public class AuthorService extends LemonServiceDefault<PaperAuthor, AuthorDao> {

}
