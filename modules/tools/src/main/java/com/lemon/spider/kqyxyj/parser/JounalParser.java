package com.lemon.spider.kqyxyj.parser;

import com.lemon.ds.entity.Journal;
import com.lemon.ds.service.JournalService;

import java.util.Date;

/**
 * Created by jishu12 on 2017/3/7.
 */
public class JounalParser extends ParserAbs {
    JournalService service = new JournalService();
    public  JounalParser(Integer id){
        super(id);
    }
    @Override
    public void doc2Entity() {
        Journal j = new Journal();
        j.setJournal_name("口腔医学研究");
        //j.setJournal_name2();
        j.setIssn("1671-7651");
        j.setCn("42-1682/R");
        j.setBegin_year(1985);
        j.setBegin_period(6);
        j.setLatest_year(2017);
        j.setLatest_volume(3);
        j.setLatest_period(1);
        j.setVolume_per_year(12);
//        j.setTs();
        service.saveEntity(j);
    }

    @Override
    public ParserAbs getEntity() {
        return null;
    }
}
