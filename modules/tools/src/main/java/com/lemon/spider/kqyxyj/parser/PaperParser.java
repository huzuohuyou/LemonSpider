package com.lemon.spider.kqyxyj.parser;

import com.lemon.ds.entity.Paper;
import com.lemon.ds.service.PaperService;
import us.codecraft.xsoup.Xsoup;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by jishu12 on 2017/3/7.
 */
public class PaperParser extends ParserAbs {
    private PaperService service = new PaperService();

    public PaperParser(Integer id){
        super(id);
    }
    @Override
    public void doc2Entity() {

        String title = Xsoup.compile(String.format("/html/body/table[2]/tbody/tr/td/table[2]/tbody/tr[3]/td/span/text()")).evaluate(doc).get();
        String title2 = Xsoup.compile(String.format("/html/body/table[2]/tbody/tr/td/table[2]/tbody/tr[3]/td/span/text()")).evaluate(doc).get();
        Integer sizekb =0;
        String mid = Xsoup.compile(String.format("/html/body/table[2]/tbody/tr/td/table[2]/tbody/tr[3]/td/span/text()")).evaluate(doc).get();
        Integer midhash=0;
        String fulltext_u_r_l =super.url;
        String tabloid = Xsoup.compile(String.format("//*[@id=\"abstract_tab_content\"]/table[1]/tbody/tr[2]/td[1]/span/text()")).evaluate(doc).get();
        String tabloid2 = Xsoup.compile(String.format("//*[@id=\"abstract_tab_content\"]/table[1]/tbody/tr[4]/td/span/text()")).evaluate(doc).get();
        String keywordsInfo = Xsoup.compile(String.format("//*[@id=\\\"abstract_tab_content\\\"]/table[1]/tbody/tr[3]/td")).evaluate(doc).get();
        Pattern rkeyword =  Pattern.compile(">([\\u4e00-\\u9fa5]*)<");
        Matcher mkeyword = rkeyword.matcher(keywordsInfo);
        String keywords = "";
        while(mkeyword.find())
            keywords +=mkeyword.group().replace("<","").replace(">","")+" ";
        keywords = keywords.trim();

        String keywords2Info = Xsoup.compile(String.format("//*[@id=\"abstract_tab_content\"]/table[1]/tbody/tr[5]/td")).evaluate(doc).get();
        Pattern rkeyword2 =  Pattern.compile(">([\\u4e00-\\u9fa5]*)<");
        Matcher mkeyword2 = rkeyword2.matcher(keywords2Info);
        String keywords2 = "";
        while(mkeyword2.find())
            keywords2 +=mkeyword2.group().replace("<","").replace(">","")+" ";
        keywords2 = keywords2.trim();

        String doIinfo=Xsoup.compile(String.format("/html/body/table[2]/tbody/tr/td/table[1]/tbody/tr/td/span/text()")).evaluate(doc).get();
        String[] dois =doIinfo==null?null:doIinfo.split(":");
        String doi=dois==null?"":dois[dois.length - 1].trim();
        doi = doi.length()>=28?doi:"";

        Integer nihms_id = 0;
        Integer pm_id =0;
        Integer data_mask = 1;
        String language_category = "chi";
        String journal_id = Xsoup.compile(String.format("/html/body/table[2]/tbody/tr/td/table[2]/tbody/tr[3]/td/span/text()")).evaluate(doc).get();
        Integer year = Integer.parseInt(Xsoup.compile(String.format("/html/body/table[2]/tbody/tr/td/table[1]/tbody/tr/td/a[2]/text()")).evaluate(doc).get());
        String volume = Xsoup.compile(String.format("/html/body/table[2]/tbody/tr/td/table[2]/tbody/tr[3]/td/span/text()")).evaluate(doc).get();
        String period = Xsoup.compile(String.format("/html/body/table[2]/tbody/tr/td/table[1]/tbody/tr/td/a[3]/text()")).evaluate(doc).get();
        String page_start = Xsoup.compile(String.format("/html/body/table[2]/tbody/tr/td/table[1]/tbody/tr/td/span/text()")).evaluate(doc).get();
        String page_end = Xsoup.compile(String.format("/html/body/table[2]/tbody/tr/td/table[1]/tbody/tr/td/span/text()")).evaluate(doc).get();
//        String online_date ="";
//        String ts ="";
        Paper p = new Paper();
        p.setArticle_title(title);
        p.setArticle_title2(title2);
        p.setSizekb(sizekb);
        p.setMid(mid);
        p.setMidhash(midhash);
        p.setFulltext_u_r_l(fulltext_u_r_l);
        p.setTabloid(tabloid);
        p.setTabloid2(tabloid2);
        p.setKeywords(keywords);
        p.setKeywords2(keywords2);
        p.setDoi(doi);
        p.setNihms_id(nihms_id);
        p.setPm_id(pm_id);
        p.setData_mask(data_mask);
        p.setLanguage_category(language_category);
        p.setJournal_id(Integer.parseInt(journal_id));
        p.setYear(year);
        p.setVolume(volume);
        p.setPeriod(period);
        p.setPage_start(Integer.parseInt(page_start.trim().split("    ")[0].split("-")[0]));
        p.setPage_end(Integer.parseInt(page_end.trim().split("    ")[0].split("-")[1]));
        service.saveEntity(p);
    }

    @Override
    public ParserAbs getEntity() {
        return null;
    }
}
