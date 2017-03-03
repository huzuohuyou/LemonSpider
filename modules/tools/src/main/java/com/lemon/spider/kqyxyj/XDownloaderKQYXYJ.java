package com.lemon.spider.kqyxyj;

import com.lemon.commons.spider.XDownloader;

/**
 * Created by bob on 2017/2/20.
 */
public class XDownloaderKQYXYJ extends XDownloader {

    private static XDownloaderKQYXYJ instance;

    public final static XDownloaderKQYXYJ sharedInstance() {
        if(instance == null) {
            instance = new XDownloaderKQYXYJ();
        }
        return instance;
    }

    public XDownloaderKQYXYJ() {
        super("http://manu45.magtech.com.cn");
    }

    @Override
    protected boolean checkBlockStatus(String text, String url) {
        if(text.indexOf("Bulk downloading of content", ParserPaperKQYXYJ.skipHead) >= 0) {
            System.out.println("[Blocked]:  try other way. " + url);
            return true; //
        }
        if(text.indexOf("Page not available</h1>", ParserPaperKQYXYJ.skipHead) >= 0) {
            System.out.println("[NA-Skip]:  Page not available. " + url);
            return false; //
        }
        return false;
    }
}
