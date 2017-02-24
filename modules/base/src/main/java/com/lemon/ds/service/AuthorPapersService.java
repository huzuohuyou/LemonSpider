package com.lemon.ds.service;

import com.lemon.ds.base.service.LemonServiceDefault;
import com.lemon.ds.dao.AuthorPapersDao;
import com.lemon.ds.entity.AuthorPapers;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@Transactional
public class AuthorPapersService extends LemonServiceDefault<AuthorPapers, AuthorPapersDao> {

}
