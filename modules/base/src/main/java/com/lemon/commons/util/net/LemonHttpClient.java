package com.lemon.commons.util.net;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Date;
import java.util.Map;
import java.util.TreeMap;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.HttpMethod;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.StringRequestEntity;
import org.springframework.web.bind.annotation.RequestMethod;

import com.lemon.commons.JsonHttp;
import com.lemon.commons.JsonObject;
import com.lemon.commons.log.Log;

public class LemonHttpClient {
    private final static Log log = Log.getLogger(LemonHttpClient.class);
    private static int timeOut = 1000 * 30;  //超时30s
    private String url;
    private RequestMethod requestMethod = RequestMethod.GET;
    private Map<String, String> requestParams = new TreeMap<String, String>();
    private Map<String, String> headerParams = new TreeMap<String, String>();
    private String postBody = null;

    public LemonHttpClient(String url) {
        this.url = url;
        // headerParams.put("http.protocol.cookie-policy",CookiePolicy.IGNORE_COOKIES);
    }

    public LemonHttpClient(String url, Map<String, String> paramMap) {
        this(url);
        this.requestMethod = RequestMethod.GET;
        for (String k : paramMap.keySet()) {
            requestParams.put(k, paramMap.get(k));
        }
    }

    public LemonHttpClient(String method, String url, Map<String, String> paramMap) {
        this(url);
        this.requestMethod = RequestMethod.GET;
        if (method != null) {
            if (method.equalsIgnoreCase("post")) {
                this.requestMethod = RequestMethod.POST;
            }
        }
        for (String k : paramMap.keySet()) {
            requestParams.put(k, paramMap.get(k));
        }
    }

    public LemonHttpClient(RequestMethod method, String url, Map<String, String> paramMap) {
        this(url);
        this.requestMethod = method;
        for (String k : paramMap.keySet()) {
            requestParams.put(k, paramMap.get(k));
        }
    }

    //POST方法
    public LemonHttpClient(String url, Map<String, String> paramMap, String postBody) {
        this(url);
        this.requestMethod = RequestMethod.POST;
        ;
        for (String k : paramMap.keySet()) {
            requestParams.put(k, paramMap.get(k));
        }
        this.postBody = postBody;
    }

    public void setRequestMethod(RequestMethod method) {
        this.requestMethod = method;
    }

    // public void enableGB2312Capibility() {
    // // ---begin解决中文乱码问题
    // headerParams.put("Content-Type", "application/x-www-form-urlencoded");
    // headerParams.put("Accept-Language", "zh-cn");
    // headerParams.put("Accept-Encoding", "gzip, deflate");
    // // ---end
    // }

    /**
     * 如果request中不包含key对应的参数，则为新增； 如果request中已包括key对应的参数，则更新当前参数对应的value
     *
     * @param key
     * @param value
     */
    public void addOrUpdateRequestParams(String key, String value) {
        requestParams.put(key, value);
    }

    public void addHeaderParams(String key, String value) {
        headerParams.put(key, value);
    }

    public JsonHttp executeSync() {
        if (url == null&&(!url.startsWith("http:")||!url.startsWith("https:")))
            return null;

        JsonHttp jh = null;
        try {
            HttpMethod httpMethod = null;
            if (RequestMethod.POST == requestMethod) {
                PostMethod httpMethod2 = new PostMethod(url);
                if (postBody != null) {
                    StringRequestEntity entity = new StringRequestEntity(postBody, "text/plain", "UTF-8");
                    httpMethod2.setRequestEntity(entity);
                }
                httpMethod = httpMethod2;

            } else {
                httpMethod = new GetMethod(url);
            }
            String queryStrParam = null;
            if (requestParams != null && requestParams.size() > 0) {
                StringBuilder sb = new StringBuilder();
                for (String k : requestParams.keySet()) {
                    String v = requestParams.get(k);
                    if(v!=null){
                        v = URLEncoder.encode(v, "utf-8");
                        sb.append("&").append(k).append("=").append(v);
                    }
                }
                queryStrParam = sb.toString().substring(1);
            }
            if (queryStrParam != null)
                httpMethod.setQueryString(queryStrParam);

            for (String k : headerParams.keySet()) {
                httpMethod.addRequestHeader(k, URLEncoder.encode(headerParams.get(k), "utf-8"));
            }
            log.info("spider url: " + url + "?" + queryStrParam);
            Date beginDate = new Date();

            HttpClient client = new HttpClient();
            client.getHttpConnectionManager().getParams().setConnectionTimeout(timeOut);
            client.executeMethod(httpMethod);
            jh = new JsonHttp();
            jh.setErrNum(httpMethod.getStatusCode())
                    .setErrMsg(httpMethod.getStatusText())
                    .setRetData(httpMethod.getResponseBodyAsString());
            Date endDate = new Date();
            Long useTime = endDate.getTime() - beginDate.getTime();
            if (useTime >= 2000) {
                log.warn("spide url :{}?{} use {} s", url, queryStrParam, useTime / 1000);
            }
            httpMethod.releaseConnection();
        } catch (UnsupportedEncodingException e) {
            log.error("request url {} [error] {}",url, e.getMessage());
        } catch (HttpException e) {
            log.error("request url {} [error] {}",url, e.getMessage());
        } catch (IOException e) {
            log.error("request url {} [error] {}",url, e.getMessage());
        }
        return jh;
    }


    public InputStream downloadFileSync() {
        if (url == null)
            return null;

        JsonObject jh = null;
        try {
            HttpMethod httpMethod = null;
            if (RequestMethod.POST == requestMethod) {
                httpMethod = new PostMethod(url);
            } else {
                httpMethod = new GetMethod(url);
            }
            String queryStrParam = null;
            if (requestParams != null && requestParams.size() > 0) {
                StringBuilder sb = new StringBuilder();
                for (String k : requestParams.keySet()) {
                    String v = requestParams.get(k);
                    v = URLEncoder.encode(v, "utf-8");
                    sb.append("&").append(k).append("=").append(v);
                }
                queryStrParam = sb.toString().substring(1);
            }


            httpMethod.setQueryString(queryStrParam);

            for (String k : headerParams.keySet()) {
                httpMethod.addRequestHeader(k, URLEncoder.encode(headerParams.get(k), "utf-8"));
            }
            HttpClient client = new HttpClient();
            client.executeMethod(httpMethod);

            return httpMethod.getResponseBodyAsStream();
        } catch (UnsupportedEncodingException e) {
            log.error("[error] ", e);
        } catch (HttpException e) {
            log.error("[error] ", e);
        } catch (IOException e) {
            log.error("[error] ", e);
        }
        return null;
    }
}