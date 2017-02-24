package com.lemon.ds.service;

import com.lemon.ds.base.service.LemonServiceDefault;
import com.lemon.ds.dao.PaperAssetDao;
import com.lemon.ds.entity.PaperAsset;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@Transactional
public class PaperAssetService extends LemonServiceDefault<PaperAsset, PaperAssetDao> {

    public boolean existsByPmcIdAndImgPath(Integer pmcId, String imgPath) {
        PaperAsset pi = serviceDao.findOneByPmcIdAndImgPath(pmcId, imgPath);
        if(pi==null) {
            return false;
        }
        if(pi.getStatus() == PaperAsset.ST_NO) {
            serviceDao.delete(pi.getId());
            return false;
        }
        return true;
    }
}
