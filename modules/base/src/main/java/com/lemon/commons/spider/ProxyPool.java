package com.lemon.commons.spider;

import java.io.File;
import javax.net.ssl.SSLContext;
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
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.TrustSelfSignedStrategy;
import org.apache.http.impl.auth.BasicScheme;
import org.apache.http.impl.client.BasicAuthCache;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicHeader;
import org.apache.http.ssl.SSLContexts;
import org.apache.http.util.EntityUtils;

/**
 * Created by bob on 2017/1/17.
 */
public class ProxyPool {

    private final static String CERT_HOME = "/Library/Java/JavaVirtualMachines/jdk1.8.0_102.jdk/Contents/Home/jre/lib/security/cacerts";
//    private final static String CERT_HOME = "/Users/bob/git/LemonSpider/cacerts";

    public static void main(String[] args) throws Exception {

        // Trust own CA and all self-signed certs
        SSLContext sslcontext = SSLContexts.custom()
                .loadTrustMaterial(new File(CERT_HOME),
                        "changeit".toCharArray(),
                        new TrustSelfSignedStrategy())
                .build();

        // Allow TLSv1 protocol only
        SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(
                sslcontext, new String[] {"TLSv1"},
                null,
                SSLConnectionSocketFactory.getDefaultHostnameVerifier());

        CredentialsProvider credsProvider = new BasicCredentialsProvider();
        credsProvider.setCredentials(
                new AuthScope("spider.crawlera.com", 8010),
                new UsernamePasswordCredentials("1bd66098acef4410b8eec210c15a1d68", "")); //

        try (CloseableHttpClient httpclient = HttpClients.custom()
                .setDefaultCredentialsProvider(credsProvider)
                .setSSLSocketFactory(sslsf)
                .build())
        {
            HttpHost target = new HttpHost("pubmedcentralcanada.ca", 80, "http");
            HttpHost proxy = new HttpHost("spider.crawlera.com", 8010);

            AuthCache authCache = new BasicAuthCache();

            BasicScheme basicAuth = new BasicScheme();
            basicAuth.processChallenge(
                    new BasicHeader(HttpHeaders.PROXY_AUTHENTICATE,
                            "Basic realm=\"Crawlera\""));
            authCache.put(proxy, basicAuth);

            HttpClientContext ctx = HttpClientContext.create();
            ctx.setAuthCache(authCache);

            RequestConfig config = RequestConfig.custom()
                    .setProxy(proxy)
                    .build();

            HttpGet httpget = new HttpGet("/pmcc/articles/PMC3000318/");
            httpget.setConfig(config);

            System.out.println("Executing request " + httpget.getRequestLine() + " to " + target + " via " + proxy);

            try (CloseableHttpResponse response = httpclient.execute(target, httpget, ctx)) {
                System.out.println("----------------------------------------");
                System.out.println(response.getStatusLine());
                System.out.println("----------------------------------------");
                System.out.println(EntityUtils.toString(response.getEntity()));
                EntityUtils.consume(response.getEntity());
            }
        }
    }

}
