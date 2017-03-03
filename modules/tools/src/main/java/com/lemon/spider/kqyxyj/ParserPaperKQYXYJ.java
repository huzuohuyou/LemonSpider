package com.lemon.spider.kqyxyj;

import com.lemon.commons.spider.XDownloader;
import com.lemon.ds.entity.PaperAuthor;
import com.lemon.ds.entity.Paper;
import com.lemon.ds.entity.PaperEmail;
import com.lemon.ds.entity.PaperLog;
import com.lemon.ds.service.*;

import com.lemon.spider.FileSession;
import com.lemon.spider.pmc.HtmlWraper;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;
import org.springframework.stereotype.Component;
import us.codecraft.xsoup.Xsoup;

import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * sample links
 * only pdf
 * http://pubmedcentralcanada.ca/pmcc/articles/PMC2125087/;jsessionid=0CC6B01018E52C5C995F585DBE299DAE.eider?lang=en-ca#__abstractid439074title
 * <p>
 * no publish online date
 * http://pubmedcentralcanada.ca/pmcc/articles/PMC3327067/;jsessionid=0CC6B01018E52C5C995F585DBE299DAE.eider?lang=en-ca#S9
 * <p>
 * full
 * http://pubmedcentralcanada.ca/pmcc/articles/PMC3325087/;jsessionid=0CC6B01018E52C5C995F585DBE299DAE.eider?lang=en-ca#__abstractid439074title
 * http://pubmedcentralcanada.ca/pmcc/articles/PMC3127067/;jsessionid=0CC6B01018E52C5C995F585DBE299DAE.eider?lang=en-ca#S9
 * http://pubmedcentralcanada.ca/pmcc/articles/PMC3000000/;jsessionid=0CC6B01018E52C5C995F585DBE299DAE.eider?lang=en-ca#S9
 * full v2
 * http://pubmedcentralcanada.ca/pmcc/articles/PMC3125087/;jsessionid=0CC6B01018E52C5C995F585DBE299DAE.eider?lang=en-ca#__abstractid439074title
 */

@Component
public final class ParserPaperKQYXYJ extends Thread {



    public final static int skipHead = 5000;
    private final static char YES = 'y',
            NOTFOUND = 'n',
            ERROR = 'e';
    private final static SimpleDateFormat PubDateFormate = new SimpleDateFormat("yyyy MMMMM dd", Locale.ENGLISH);
    private final static SimpleDateFormat PubDateFormate2 = new SimpleDateFormat("yyyy MMMMM", Locale.ENGLISH);

    @Autowired
    private PaperService service;
    @Autowired
    private PaperLogService plService;
    @Autowired
    private PaperEmailService peService;
    @Autowired
    private PaperAssetService prService;
    @Autowired
    private AuthorService aService;

    private List<PaperEmail> listEmail = new ArrayList<>();
    private FileSession fileSession;

    private int mode;
    private int left;
    private int count = 0;
    private static ApplicationContext context = new FileSystemXmlApplicationContext("classpath*:applicationContext.xml");

    public ParserPaperKQYXYJ(int mode, int left) {
        this.mode = mode;
        this.left = left;
        service = context.getBean(PaperService.class);
        plService = context.getBean(PaperLogService.class);
        peService = context.getBean(PaperEmailService.class);
        prService = context.getBean(PaperAssetService.class);
        aService = context.getBean(AuthorService.class);
//		apService = context.getBean(AuthorPapersService.class);
    }

    @Override
    public void run() {
        /**
         *  3000000, 2125087, 3327067, 3127067, 3125087, 3325087, 3000002, 3000003, 3000066, 3000067, 3000075, 4000471
         *  4000334  引表不规范, 没有摘要
         *  4000479  没有作者
         */
//		int[] ids = {4000011};
//		for (int pmcId : ids) {

        Integer pmcId = plService.findNextStartId(mode, left, SpiderKQYXYJ.PMC_Start, SpiderKQYXYJ.PMC_Stop);
        if (pmcId == null) {
            pmcId = SpiderKQYXYJ.PMC_Start;
        } else if (pmcId < SpiderKQYXYJ.PMC_Start) {
            pmcId = SpiderKQYXYJ.PMC_Start;
        }
        for (; pmcId % mode != left; pmcId++) {
        }
        System.out.println("started success. spider from " + pmcId);
        for (; pmcId < SpiderKQYXYJ.PMC_Stop; pmcId += mode) {

            long start = System.currentTimeMillis();
            PaperLog pl = new PaperLog();
            pl.setPmcId(pmcId);
            pl.setStatus(ERROR);
            try {
                Paper p = runOne(pmcId, pl);
                long timeElips = (System.currentTimeMillis() - start) / 1000;
                if (p != null) {
                    ++XDownloader.Total_Papers;
                    System.out.println("OK :  " + left + "\t" + (++count) + "\tPMC" + pmcId + "\tused(s): " + timeElips + "  \tsize(KB): " + p.getSizeKB());

                    if (XDownloader.Total_Papers % 10 == 0) {
                        System.out.println("----------------------------------------------------------------------------------");
                        Date now = new Date();
                        long elaped = (now.getTime() - SpiderKQYXYJ.APP_Start) / (1000 * 60);
                        String time;
                        if (elaped > 60) {
                            time = "" + elaped / 60 + "h " + elaped % 60 + "m";
                        } else {
                            time = elaped + "m";
                        }
                        if (elaped < 1) {
                            elaped = 1;
                        }
                        long speed = (XDownloader.Total_Papers * 60) / elaped;
                        System.out.println("Time: " + now + "\tElaped: " + time + "\tPapers/Hour: " + speed + "\nTotal Request: " + XDownloader.Total_Requests + "\tTotal Paper: " + XDownloader.Total_Papers + "  \tavg(R/P): " + (1.0 * XDownloader.Total_Requests / XDownloader.Total_Papers));
                        System.out.println("----------------------------------------------------------------------------------");
                    }
                    continue;
                }
            } catch (Exception e) {
                pl.setErrMsg(e.getMessage());
                try {
                    plService.saveEntity(pl);
                } catch (Exception ee) {
                }
                e.printStackTrace();
            }

            long timeElips = (System.currentTimeMillis() - start) / 1000;
            System.out.println("Err:  " + left + "\t" + (++count) + "\tPMC" + pmcId + "\tused(s): " + timeElips + "\tcause: " + pl.getErrMsg());

//			if(fileSession != null) {
//				fileSession.onFailureClean();
//			}
        }
        System.out.println("bye. all data is ok.");
    }

    private final static String SkipText = "<p>The web page address (URL) that you used may be incorrect. It specifies a non-existent article ID.</p>";

    public Paper runOne(int pmcId, PaperLog pl) throws Exception {
        //onBefore
        listEmail.clear();
        fileSession = null;
        Paper p = new Paper(pmcId);


        //1. 摘要
        //wuhailong
        String url = String.format(SpiderKQYXYJ.UrlPrefix4Spider, pmcId);
        String rawData = XDownloaderKQYXYJ.sharedInstance().getHtml(url);
        fileSession = new FileSession(pmcId);
        if (rawData == null) {
            HtmlWraper.writePaperHtml(SkipText, fileSession);
            pl.setStatus(NOTFOUND);
            pl.setErrMsg("Page not available");
            plService.saveOrUpdate(pl);
            return p;
        }
        pl.onAbstractOK();
        Document doc = Jsoup.parse(rawData);        //html/body/table[2]/tbody/tr/td/table[2]/tbody/tr[3]/td/span
        String title = Xsoup.compile(String.format("/html/body/table[2]/tbody/tr/td/table[2]/tbody/tr[3]/td/span/text()")).evaluate(doc).get();
        String doIinfo=Xsoup.compile(String.format("/html/body/table[2]/tbody/tr/td/table[1]/tbody/tr/td/span/text()")).evaluate(doc).get();
        String[] dois =doIinfo==null?null:doIinfo.split(":");
        String doi=dois==null?"":dois[dois.length - 1].trim();
        doi = doi.length()>=28?doi:"";
        System.out.println("DOI"+doi);
        //.split(":")[-1].trim()
        String journal="口腔医学研究";
        String journalVolume=Xsoup.compile(String.format("/html/body/table[2]/tbody/tr/td/table[1]/tbody/tr/td/b/a/text()")).evaluate(doc).get();
        String pageBegin=Xsoup.compile(String.format("/html/body/table[2]/tbody/tr/td/table[1]/tbody/tr/td/span/text()")).evaluate(doc).get();
        String pageEnd=Xsoup.compile(String.format("/html/body/table[2]/tbody/tr/td/table[1]/tbody/tr/td/span/text()")).evaluate(doc).get();
        String onlineDate=Xsoup.compile(String.format("/html/body/table[2]/tbody/tr/td/table[1]/tbody/tr/td/span/text()")).evaluate(doc).get();
        String abstr=Xsoup.compile(String.format("//*[@id=\"abstract_tab_content\"]/table[1]/tbody/tr[2]/td[1]/span/text()")).evaluate(doc).get();
        String keywordInfo =Xsoup.compile(String.format("//*[@id=\"abstract_tab_content\"]/table[1]/tbody/tr[3]/td")).evaluate(doc).get();

        Pattern rkeyword =  Pattern.compile(">([\\u4e00-\\u9fa5]*)<");
        Matcher mkeyword = rkeyword.matcher(keywordInfo);
        String keyword = "";
        while(mkeyword.find())
            keyword +=mkeyword.group().replace("<","").replace(">","")+" ";
        keyword = keyword.trim();
        String authors=Xsoup.compile(String.format("/html/body/table[2]/tbody/tr/td/table[2]/tbody/tr[4]/td/text()")).evaluate(doc).get();
        String authorOrgs=Xsoup.compile(String.format("/html/body/table[2]/tbody/tr/td/table[2]/tbody/tr[5]/td/span/text()")).evaluate(doc).get();
        boolean ok = XDownloaderKQYXYJ.sharedInstance().download2File(
                String.format("/Jwk_kqyxyj/CN/article/downloadArticleFile.do?attachType=PDF&id=$d", pmcId),
                fileSession.getPdfOsPath());

        p.setTitle(title);
        p.setDoi(doi);
        p.setJournal(journal);
        p.setJournal_volume(journalVolume);
       // System.out.println("----"+pageBegin.trim().split("    ")[0].split("-")[0]);
        p.setPage_begin(Integer.parseInt(pageBegin.trim().split("    ")[0].split("-")[0]));
        p.setPage_end(Integer.parseInt(pageBegin.trim().split("    ")[0].split("-")[1]));
        SimpleDateFormat sdf =   new SimpleDateFormat( " yyyy-MM-dd" );
        //p.setOnlineDate(sdf.parse("2017-02-01"));
        p.setAbstr_cn(abstr);
        p.setKeyword_cn(keyword);
        p.setAuthors(authors);
        p.setAuthor_orgs(authorOrgs);
        //authors
        String email=Xsoup.compile(String.format("//*[@id=\"abstract_tab_content\"]/table[1]/tbody/tr[9]/td/span/text()")).evaluate(doc).get();
        String dept=Xsoup.compile(String.format("/html/body/table[2]/tbody/tr/td/table[2]/tbody/tr[5]/td/span/text()")).evaluate(doc).get();
        String baseinfo=Xsoup.compile(String.format("//*[@id=\"abstract_tab_content\"]/table[1]/tbody/tr[10]/td/span/text()")).evaluate(doc).get();



        String[]  authoList = authors.trim().split(",");
        pl.setStatus(YES);
        String journalYear=Xsoup.compile(String.format("/html/body/table[2]/tbody/tr/td/table[1]/tbody/tr/td/a[2]/text()")).evaluate(doc).get();
        //System.out.print("###############"+journalYear);
        p.setJournal_year(Integer.parseInt(journalYear));

        String journalNo=Xsoup.compile(String.format("/html/body/table[2]/tbody/tr/td/table[1]/tbody/tr/td/a[3]/text()")).evaluate(doc).get();
        p.setJournal_no(journalNo);
        p=service.saveEntity(p);

        for (String x:authoList ) {
            String name = x.trim();
            System.out.println("*********************"+x);
            PaperAuthor a = new PaperAuthor();
            a.setAuthor_name(name);
            Pattern remail =  Pattern.compile("[\\w!#$%&'*+/=?^_`{|}~-]+(?:\\.[\\w!#$%&'*+/=?^_`{|}~-]+)*@(?:[\\w](?:[\\w-]*[\\w])?\\.)+[\\w](?:[\\w-]*[\\w])?");
            Matcher memail = remail.matcher(email);

            Pattern rphone =  Pattern.compile("\\d{3}-\\d{8}|\\d{4}-\\{7,8}");
            Matcher mphone = rphone.matcher(email);
            if(memail.find()|mphone.find())
            {

                if(email.contains(name))
                    a.setAuthor_email(memail.group(0)==null?mphone.group(0):memail.group(0));
            }else{

                a.setAuthor_email(null);
            }
            a.setPaper_id(p.getId());
            a.setStatus('a');
            aService.saveEntity(a);
        }


        //System.out.print("*********************\n"+email+"#"+dept+baseinfo);
        //2. PDF



        plService.saveOrUpdate(pl);
        return p;
    }




}