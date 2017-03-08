package com.lemon.spider.kqyxyj.parser;

import com.lemon.ds.entity.PaperAuthor;
import com.lemon.ds.entity.PaperAuthorOrg;
import com.lemon.ds.service.OrganizationService;
import com.lemon.ds.service.PaperAuthorOrgService;

/**
 * Created by jishu12 on 2017/3/7.
 */
public class PaperAuthorOrgParser extends ParserAbs {
    PaperAuthorOrgService service =context.getBean(PaperAuthorOrgService.class);
    Integer paper_author_id;
    Integer org_id;
    public PaperAuthorOrgParser(Integer paper_author_id,Integer org_id){
        super(0);
        this.paper_author_id = paper_author_id;
        this.org_id = org_id;

    }
    public void doc2Entity() {
        PaperAuthorOrg pao = new PaperAuthorOrg();
        pao.setPaper_author_id(paper_author_id);
        pao.setOrg_id(org_id);
        pao.setUnit_no(1);
        service.saveEntity(pao);
    }

    @Override
    public Object getEntity() {
        return null;
    }
}
