package com.lemon.ds.service;

import com.lemon.ds.base.service.LemonServiceDefault;
import com.lemon.ds.dao.PaperDao;
import com.lemon.ds.entity.Paper;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@Transactional
public class PaperService extends LemonServiceDefault<Paper, PaperDao> {

}
