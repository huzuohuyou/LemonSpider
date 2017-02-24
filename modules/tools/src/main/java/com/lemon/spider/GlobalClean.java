package com.lemon.spider;

import com.lemon.commons.spider.StringPlus;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by bob on 2017/1/13.
 */
public class GlobalClean {

    private final static String patRefImg = "=\"/pmcc/articles/PMC[\\d]+/bin/";  //"=\"/pmcc/articles/PMC[\\d]+/bin/[^\\.]+\\.[^\"]+\"";
    private final static String patRefTableIcon = "=\"/corehtml/pmc/pmcgifs/";
    private final static Pattern patRef = Pattern.compile("=\"/pmcc/articles/PMC[\\d]+/((figure/[^/]+/)|(table/[^/]+/))");

    private final static Pattern patCityBy = Pattern.compile("href=\"/pmcc/articles/PMC[\\d]+/citedby/\"");
    private final static Pattern patPMRef = Pattern.compile("<span class=\"nowrap ref ((pubmed)|(pmc))\">");
    private final static Pattern patAnchor4AuthorOrDoi = Pattern.compile("href=\"http://((www\\.ncbi\\.nlm\\.nih\\.gov/pubmed/\\?term=[^\"]+\")|(dx\\.doi\\.org/))");
    /**
     * <img src="/pmcc/articles/PMC3000086/bin/ijms-11-04348f2.gif" class="small-thumb" alt="Figure 2." title="Figure 2." src-large="/pmcc/articles/PMC3000086/bin/ijms-11-04348f2.jpg">
     *
     * <a class="figpopup" href="/pmcc/articles/PMC3000086/figure/f2-ijms-11-04348/" target="figure" rid-figpopup="f2-ijms-11-04348" rid-ob="ob-f2-ijms-11-04348">Figure 2.</a>
     * @param text
     * @return
     */
    public static String fixOutLinks4Papers(String text) {
        Matcher m = patPMRef.matcher(text);
        StringBuffer sb = new StringBuffer();
        int lastPos = 0;
        while (m.find()) {
            sb.append(text.substring(lastPos, m.start()));
            lastPos = text.indexOf("</span>", m.start() + 10) + 7;
        }
        sb.append(text.substring(lastPos));
        text = sb.toString();

        m = patAnchor4AuthorOrDoi.matcher(text);
        sb = new StringBuffer();
        lastPos = 0;
        while (m.find()) {
            int tagA = text.lastIndexOf("<a", m.start());
            int tagAEnd = text.indexOf(">", m.end());
            int tagEndA = text.indexOf("</a>", tagAEnd);
            sb.append(text.substring(lastPos, tagA))
                    .append(text.substring(tagAEnd+1, tagEndA));
            lastPos = tagEndA + 4;
        }
        sb.append(text.substring(lastPos));

        text = sb.toString();

        m = patCityBy.matcher(text);
        while (m.find()) {
            int tagCityBy = StringPlus.lastIndexOf(text, "<div", m.start(), 3);
            int tagCityByEnd = StringPlus.indexOf(text, "</div>", m.end(), 3);
            if(tagCityBy>=0 && tagCityByEnd>=0) {
                sb = new StringBuffer();
                sb.append(text.substring(0, tagCityBy))
                        .append(text.substring(tagCityByEnd+6));
                text = sb.toString();
            }
        }


        //stag 2
        text = text.replaceAll(patRefImg, "=\"bin/");
        text = text.replaceAll(patRefTableIcon, "=\"../../../include/");

        sb = new StringBuffer();
        lastPos = 0;
        m = patRef.matcher(text);
        while(m.find()) {
            String lite = text.substring(m.start(), m.end());
            int tagRef = StringPlus.lastIndexOf(lite, "/", lite.length()-2, 2);
            if(tagRef <= 0){
                continue;
            }

            lite = lite.substring(tagRef + 1, lite.length()-1) + ".html";

            sb.append(text.substring(lastPos, m.start()+2)).append(lite);

            lastPos = m.end();
        }
        sb.append(text.substring(lastPos));

        text = sb.toString();

        //<div class="fm-citation-pmcid"><span class="fm-citation-ids-label">PMCID: </span><span>PMC3100088</span></div>
        int tagPmc = text.indexOf("=\"fm-citation-pmcid\"");
        if(tagPmc >= 0) {
            int tagPmcDiv = text.lastIndexOf("<div", tagPmc);
            int tagPmcDivEnd = text.indexOf("</div>", tagPmc) + 6;
            text = text.substring(0, tagPmcDiv) + text.substring(tagPmcDivEnd);
        }

        return text;
    }

    private final static Pattern patIntReflink = Pattern.compile("href=\"/pmcc/articles/PMC[\\d]+/");
    public static String fixOutLinks4Reffers(String text) {
        //stag 2
        text = text.replaceAll(patRefImg, "=\"../bin/");
        text = text.replaceAll(patRefTableIcon, "=\"../../../../include/");

        StringBuffer sb = new StringBuffer();
        int lastPos = 0;
        Matcher m = patRef.matcher(text);
        while(m.find()) {
            String lite = text.substring(m.start(), m.end());
            int tagRef = lite.lastIndexOf("/", lite.length()-2);
            if(tagRef <= 0){
                continue;
            }

            lite = lite.substring(tagRef + 1, lite.length()-1) + ".html";

            sb.append(text.substring(lastPos, m.start()+2)).append(lite);

            lastPos = m.end();
        }
        sb.append(text.substring(lastPos));
        text = sb.toString();

        m = patIntReflink.matcher(text);
        if(m.find()) {
            sb = new StringBuffer();
            sb.append(text.substring(0, m.start()+6))
                    .append("../index.html")
                    .append(text.substring(m.end()));
            text = sb.toString();
        }

        return text;
    }

}
