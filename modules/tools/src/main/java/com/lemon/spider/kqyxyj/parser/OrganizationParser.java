package com.lemon.spider.kqyxyj.parser;

import com.lemon.ds.entity.Organization;
import com.lemon.ds.service.OrganizationService;
import com.lemon.ds.service.PaperService;
import us.codecraft.xsoup.Xsoup;

/**
 * Created by jishu12 on 2017/3/7.
 */
public class OrganizationParser extends ParserAbs {
    OrganizationService service = context.getBean(OrganizationService.class);
    public OrganizationParser(Integer id){
        super(id);
    }
    @Override
    public void doc2Entity() {
        Organization o = new Organization();
        String Unit_full = Xsoup.compile(String.format("/html/body/table[2]/tbody/tr/td/table[2]/tbody/tr[5]/td/span/text()")).evaluate(doc).get();
        String[] array = Unit_full.split(",");

        o.setUnit_full(Unit_full);
        o.setOrganization("");
        o.setLaboratory("");
        o.setCity("");
        o.setInstitute("");
        String Unit_full2 = Xsoup.compile(String.format("/html/body/table[2]/tbody/tr/td/table[2]/tbody/tr[9]/td/span/text()")).evaluate(doc).get();
        o.setUnit_full2(Unit_full2);
        o.setOrganization2("");
        o.setLaboratory2("");
        o.setCity2("");
        o.setInstitute2("");
        service.saveEntity(o);
    }

    @Override
    public ParserAbs getEntity() {
        return null;
    }
}
