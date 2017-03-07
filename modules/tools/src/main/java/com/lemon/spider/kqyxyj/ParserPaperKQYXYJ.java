package com.lemon.spider.kqyxyj;

import com.lemon.commons.spider.XDownloader;
import com.lemon.ds.entity.Paper;
import com.lemon.ds.service.*;

import com.lemon.spider.kqyxyj.parser.*;
import com.lemon.spider.pmc.ParserPaper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.*;

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



    private int mode;
    private int left;
    private int count = 0;
    private static ApplicationContext context = new FileSystemXmlApplicationContext("classpath*:applicationContext.xml");

    public ParserPaperKQYXYJ(int mode, int left) {
        this.mode = mode;
        this.left = left;
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

        Integer pmcId  = SpiderKQYXYJ.PMC_Start;
        for (; pmcId % mode != left; pmcId++) {
        }
        System.out.println("started success. spider from " + pmcId);
        for (; pmcId < SpiderKQYXYJ.PMC_Stop; pmcId += mode) {

            long start = System.currentTimeMillis();

            try {
                Paper p = runOne(pmcId);
                long timeElips = (System.currentTimeMillis() - start) / 1000;
                if (p != null) {
                    ++XDownloader.Total_Papers;
                    //System.out.println("OK :  " + left + "\t" + (++count) + "\tPMC" + pmcId + "\tused(s): " + timeElips + "  \tsize(KB): " + p.getSizeKB());

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
                try {
                    //plService.saveEntity(pl);
                } catch (Exception ee) {
                }
                e.printStackTrace();
            }

            long timeElips = (System.currentTimeMillis() - start) / 1000;
            System.out.println("Err:  " + left + "\t" + (++count) + "\tPMC" + pmcId + "\tused(s): " + timeElips + "\tcause: " );

//			if(fileSession != null) {
//				fileSession.onFailureClean();
//			}
        }
        System.out.println("bye. all data is ok.");
    }

    private final static String SkipText = "<p>The web page address (URL) that you used may be incorrect. It specifies a non-existent article ID.</p>";

    public Paper runOne(int id) throws Exception {
//        PaperRefParser prp = new PaperRefParser(id);
//        prp.doc2Entity();
        ParserAbs pp = new PaperParser<Paper>(id);
        pp.doc2Entity();
        ParserAbs pa = new PaperAuthorParser(id);
        pa.doc2Entity();
        ParserAbs j = new JounalParser(id);
        j.doc2Entity();
        ParserAbs o = new OrganizationParser(id);
        o.doc2Entity();
        PaperAuthorOrgParser pao = new PaperAuthorOrgParser(1,1);
        pao.doc2Entity();

        return new Paper();
    }




}