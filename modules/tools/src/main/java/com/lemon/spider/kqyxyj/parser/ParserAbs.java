package com.lemon.spider.kqyxyj.parser;

import com.lemon.spider.kqyxyj.SpiderKQYXYJ;
import com.lemon.spider.kqyxyj.XDownloaderKQYXYJ;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

/**
 * Created by Bimt on 2017/3/7.
 */
public abstract class  ParserAbs <T>{
    Document doc=null;
    String url="";
    ParserAbs entity;
    public ParserAbs(Integer id){
        url = String.format(SpiderKQYXYJ.UrlPrefix4Spider, id);
        String rawData = XDownloaderKQYXYJ.sharedInstance().getHtml(url);
        doc = Jsoup.parse(rawData);
    }

    public abstract void doc2Entity();

    public abstract T getEntity();
}
