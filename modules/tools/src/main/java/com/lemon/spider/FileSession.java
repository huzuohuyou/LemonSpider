package com.lemon.spider;

import com.lemon.spider.pmc.Spider;

import java.io.File;

/**
 * Created by bob on 2017/1/12.
 */
public class FileSession {


    private final static String ArticlesDir = Spider.WWWROOTDir ;

    private String sessionDir;
    public int pmcId;
    public int parent;
    public FileSession(int pmcId) {
        this.pmcId = pmcId;
        parent = pmcId / 100000;
        sessionDir = ArticlesDir + parent + "/" + pmcId;
        File f = new File(sessionDir);
        f.mkdirs();
    }

    public void onFailureClean() {
        File f = new File(sessionDir);
        f.delete();
    }

    public File getJspOsPath() {
        return new File(sessionDir + "/index.jsp");
    }

    public File getHtmlOsPath() {
        return new File(sessionDir + "/index.html");
    }

    public File getHtmlSideOsPath() {
        return new File(sessionDir + "/side.html");
    }

    public File getFigureHtmlOsPath(String fid) {
        File dir = new File(sessionDir + "/figure/");
        if(!dir.exists()) {
            dir.mkdirs();
        }
        return new File(sessionDir + "/figure/" + fid + ".html");
    }
    public File getFigureJspOsPath(String fid) {
        File dir = new File(sessionDir + "/figure/");
        if(!dir.exists()) {
            dir.mkdirs();
        }
        return new File(sessionDir + "/figure/" + fid + ".jsp");
    }

    public File getTableHtmlOsPath(String fid) {
        File dir = new File(sessionDir + "/table/");
        if(!dir.exists()) {
            dir.mkdirs();
        }
        return new File(sessionDir + "/table/" + fid + ".html");
    }
    public File getTableJspOsPath(String fid) {
        File dir = new File(sessionDir + "/table/");
        if(!dir.exists()) {
            dir.mkdirs();
        }
        return new File(sessionDir + "/table/" + fid + ".jsp");
    }

    public File getPdfOsPath() {
        return new File(sessionDir + "/" + pmcId + ".pdf");
    }

    public boolean isPdfExists() {
        File f = new File(sessionDir + "/" + pmcId + ".pdf");
        return f.exists();
    }

    public File getImgPath(String partPath) {
        String path = sessionDir + "/" + partPath;
        File parent = new File(path.substring(0, path.lastIndexOf("/")));
        if(!parent.exists()) {
            parent.mkdirs();
        }
        return new File(path);
    }

    public File getCommonResourcePath(String partPath) {
        String path = Spider.WWWROOTDir + partPath;
        File parent = new File(path.substring(0, path.lastIndexOf("/")));
        if(!parent.exists()) {
            parent.mkdirs();
        }
        return new File(path);
    }
}
