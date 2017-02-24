package com.lemon.ds.service;

import com.lemon.ds.base.service.LemonServiceDefault;
import com.lemon.ds.dao.PaperEmailDao;
import com.lemon.ds.entity.PaperEmail;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@Transactional
public class PaperEmailService extends LemonServiceDefault<PaperEmail, PaperEmailDao> {

    public void saveOrUpdatePE(PaperEmail pe) {
        PaperEmail pe0 = serviceDao.findOneByPmcIdAndEmail(pe.getPmcId(), pe.getEmail());
        if(pe0 == null) {
            serviceDao.save(pe);
        }
    }
}
