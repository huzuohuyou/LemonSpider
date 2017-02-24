package com.lemon.commons.spider;

import com.lemon.commons.ThreadSafeSleep;
import org.apache.http.HttpHeaders;
import org.apache.http.HttpHost;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.AuthCache;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.impl.auth.BasicScheme;
import org.apache.http.impl.client.*;
import org.apache.http.message.BasicHeader;
import org.apache.http.util.EntityUtils;
import org.jsoup.Connection;
import org.jsoup.Jsoup;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;

/**
 * Created by bob on 2017/1/17.
 */
public abstract class XDownloader {

    private final static String CERT_HOME = "/Library/Java/JavaVirtualMachines/jdk1.8.0_102.jdk/Contents/Home/jre/lib/security/cacerts";
    private final static int TimeoutInMs = 20000;
    public static int Total_Requests = 0;
    public static int Total_Papers = 0;
    public static int Total_Failed = 0;
    public static int Total_Skiped = 0;


    private String rootUrl;
    private HttpHost target;
    private RequestConfig config;
    private CloseableHttpClient httpclient;
    private HttpClientContext ctx;
    private HttpHost proxy;
    private int clientUsedTime = 0;
    private final static int MaxContinuousFailedNum4HtmlDirectDownload = 3;
    private int continuousFailedNum4HtmlDirectDownload = 0;
    private final static int MaxCoolDowncounter = 200;
    private int coolDowncounter = MaxCoolDowncounter;  //被墙之后的冷却次数。


    protected XDownloader(String rootUrl) {
        this.rootUrl = rootUrl;
        init();
    }


    /**
     * leave subclass to override
     * @param text
     * @param url
     * @return
     */
    protected abstract boolean checkBlockStatus(String text, String url);

    public String getHtml(String url) {
        ++Total_Requests;

//        if(url.contains("/table/") || url.contains("/figure/")) {
            if (continuousFailedNum4HtmlDirectDownload >= MaxContinuousFailedNum4HtmlDirectDownload) {
                --coolDowncounter;
                if (coolDowncounter == 0) { //冷却时间到,重置
                    coolDowncounter = MaxCoolDowncounter;
                    continuousFailedNum4HtmlDirectDownload = 0;
                }
            } else {
                try {
                    //System.out.print(String.format("full url : %s", rootUrl+url));
                    String text = Jsoup.connect(rootUrl + url).timeout(TimeoutInMs).get().html();

                    if (!checkBlockStatus(text, url)) {
                        //成功一次就清0
                        continuousFailedNum4HtmlDirectDownload = 0;
                        System.out.println("200:  " + url);
                        return text;
                    }
                } catch (Exception e) {
                    System.out.println("[warn]  first loading html failed, try another way. " + url);
                    //nothing do, to jump to the following get methods.

                }
                //如果连续失败计数
                ++continuousFailedNum4HtmlDirectDownload;
            }
//        }

        int retry = 3;
        System.out.print("***************"+url);
        String rt = doProxyGetHtml(url);
        while(rt==null || (checkBlockStatus(rt, url) && retry>0)) { //如果失败了,休眠一会之后,再次尝试,这个时候可能会以另外的ip去访问。
            --retry;
            System.out.println("[Retry Later]:  Use Proxy still got blocked!!!");
            ThreadSafeSleep.sleep(2000);
            System.out.print("***************"+rootUrl + url);
            rt = doProxyGetHtml(url);
        }
        System.out.println("Pxy:  " + url);
        return rt;
    }




    public boolean download2File(String url, File dstFile) {
        ++Total_Requests;

        BufferedOutputStream bos = null;
        Connection.Response response;
        try {
            response = Jsoup.connect(rootUrl + url).ignoreContentType(true).timeout(TimeoutInMs).execute();
            bos = new BufferedOutputStream(new FileOutputStream(dstFile));
            bos.write(response.bodyAsBytes());
            bos.flush();

            System.out.println(response.statusCode() + ":  " + url);
            return true;
        } catch (Exception e) {
            System.out.println("[warn]  first loading file failed, try another way. " + url);
            //nothing do, to jump to the following get methods.

        } finally {
            if (bos != null) {
                try {
                    bos.close();
                } catch (Exception e) {
                }
            }
        }

        int retry = 2;
        boolean rt = doProxyDownload2File(url, dstFile);
        while(!rt && retry>0) { //如果失败了,休眠一会之后,再次尝试,这个时候可能会以另外的ip去访问。
            --retry;
            ThreadSafeSleep.sleep(2000);
            System.out.println("Retry spider download");
            rt = doProxyDownload2File(url, dstFile);
        }
        return rt;
    }


    private void init() {
        CredentialsProvider credsProvider = new BasicCredentialsProvider();
        credsProvider.setCredentials(
                new AuthScope("spider.crawlera.com", 8010),
                new UsernamePasswordCredentials("1bd66098acef4410b8eec210c15a1d68", "")); //

        try {
            if(httpclient != null) {
                try {
                    httpclient.close();
                    httpclient = null;
                } catch (Exception ee) {

                }
            }

            httpclient = HttpClients.custom()
                    .setDefaultCredentialsProvider(credsProvider)
                    .build();
            target = new HttpHost("pubmedcentralcanada.ca", 80, "http");
            proxy = new HttpHost("spider.crawlera.com", 8010);
            //proxy = new HttpHost("45.78.4.173", 443);
            AuthCache authCache = new BasicAuthCache();

            BasicScheme basicAuth = new BasicScheme();
            basicAuth.processChallenge(
                    new BasicHeader(HttpHeaders.PROXY_AUTHENTICATE,
                            "Basic realm=\"Crawlera\""));
            authCache.put(proxy, basicAuth);

            ctx = HttpClientContext.create();
            ctx.setAuthCache(authCache);

            config = RequestConfig.custom()
                    .setProxy(proxy)
                    .setConnectTimeout(TimeoutInMs)
                    .build();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private CloseableHttpClient getClient() {
        ++clientUsedTime;
        if(clientUsedTime % 200 == 0) {
            System.out.println("\n\nspider had been used #" + clientUsedTime + "\n\n");
            init();
        }
        return httpclient;
    }

    private String doProxyGetHtml(String url) {
        HttpGet httpget = new HttpGet(url);
        httpget.setConfig(config);
        CloseableHttpResponse response = null;

        try {
            response = getClient().execute(target, httpget, ctx);
            String text = EntityUtils.toString(response.getEntity());
            EntityUtils.consume(response.getEntity());

            if(!checkBlockStatus(text, url)) {
                return text;
            } else {
                return null;
            }
        } catch (Exception e) {
            System.err.println("Err: " + httpget.getRequestLine());
            e.printStackTrace();
        } finally {
            if(response != null) {
                try {
                    response.close();
                } catch (Exception e) {}
            }
            if(httpget != null) {
                try {
                    httpget.releaseConnection();
                } catch (Exception e) {}
            }
        }
        return null;
    }

    private boolean doProxyDownload2File(String url, File dstFile) {
        HttpGet httpget = new HttpGet(url);
        httpget.setConfig(config);
        BufferedInputStream bis = null;
        BufferedOutputStream bos = null;
        CloseableHttpResponse response = null;
        try {
            //Open a URL Stream
            response = getClient().execute(target, httpget, ctx);
            if(response.getStatusLine().getStatusCode() != 200) {
                return false;
            }

            bis = new BufferedInputStream(response.getEntity().getContent());
            bos = new BufferedOutputStream(new FileOutputStream(dstFile));
            int got;
            byte[] cache = new byte[1024*1024];
            while((got=bis.read(cache)) != -1) {
                bos.write(cache, 0, got);
            }
            bos.flush();
            
            System.out.println(response.getStatusLine().getReasonPhrase() + " :  " + httpget.getRequestLine().getUri());

            return true;
        } catch (Exception e) {
            System.err.println("Err: " + httpget.getRequestLine());
            e.printStackTrace();
            return false;
        } finally {
            if(bos != null) {
                try {
                    bos.close();
                } catch (Exception e) {}
            }

            if(bis != null) {
                try {
                    bis.close();
                } catch (Exception e) {}
            }

            if(response != null) {
                try {
                    response.close();
                } catch (Exception e) {}
            }

            if(httpget != null) {
                try {
                    httpget.releaseConnection();
                } catch (Exception e) {}
            }
        }
    }

}