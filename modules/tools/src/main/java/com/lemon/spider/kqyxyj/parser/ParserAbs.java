package com.lemon.spider.kqyxyj.parser;

import com.lemon.spider.kqyxyj.SpiderKQYXYJ;
import com.lemon.spider.kqyxyj.XDownloaderKQYXYJ;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

/**
 * Created by Bimt on 2017/3/7.
 */
public abstract class  ParserAbs <T>{
    protected static ApplicationContext context = new FileSystemXmlApplicationContext("classpath*:applicationContext.xml");
    T entity=null;
    Document doc=null;
    String url="";
    public ParserAbs(Integer id){
        url = String.format(SpiderKQYXYJ.UrlPrefix4Spider, id);
        String rawData = XDownloaderKQYXYJ.sharedInstance().getHtml(url);
        doc = Jsoup.parse(rawData);
    }

    public abstract void doc2Entity();

    public abstract T getEntity();
}
