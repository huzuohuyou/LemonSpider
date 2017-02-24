package com.lemon.spider.pmc;

import com.lemon.commons.spider.XDownloader;

/**
 * Created by bob on 2017/2/20.
 */
public class XDownloaderPMC extends XDownloader {

    private static XDownloaderPMC instance;

    public final static XDownloaderPMC sharedInstance() {
        if(instance == null) {
            instance = new XDownloaderPMC();
        }
        return instance;
    }

    public XDownloaderPMC() {
        super("http://pubmedcentralcanada.ca");
    }

    @Override
    protected boolean checkBlockStatus(String text, String url) {
        if(text.indexOf("Bulk downloading of content", ParserPaper.skipHead) >= 0) {
            System.out.println("[Blocked]:  try other way. " + url);
            return true; //
        }
        if(text.indexOf("Page not available</h1>", ParserPaper.skipHead) >= 0) {
            System.out.println("[NA-Skip]:  Page not available. " + url);
            return false; //
        }
        return false;
    }
}
