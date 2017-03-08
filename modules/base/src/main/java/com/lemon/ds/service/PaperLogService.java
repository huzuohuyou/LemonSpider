package com.lemon.ds.service;

import com.lemon.ds.base.service.LemonServiceDefault;
import com.lemon.ds.dao.PaperLogDao;
import com.lemon.ds.entity.PaperLog;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@Transactional
public class PaperLogService extends LemonServiceDefault<PaperLog, PaperLogDao> {

	public Integer findNextStartId(Integer mode, Integer left, Integer start, Integer stop) {
		Integer maxId;
		if(mode==null || left==null) {
			maxId = serviceDao.findMaxId();
		} else {
		 	maxId = serviceDao.findMaxId(mode, left, start, stop);
		}
		if(maxId == null) {
			return null;
		} else {
			maxId += 1;
		}
		return maxId;
	}

	public void saveOrUpdate(PaperLog pl) {
		PaperLog dbpl = serviceDao.findOneByPmcId(pl.getId());
		if(dbpl == null) {
			serviceDao.save(pl);
		} else {
			serviceDao.updateByPmcId(pl.getId(), pl.getStatus(), pl.getComplete(), pl.getStatus_msg(), pl.getTs());
		}
	}

}
