package com.lemon.spider.kqyxyj.parser;

import com.lemon.ds.entity.PaperAuthor;
import com.lemon.ds.service.PapaerAuthorService;
import com.lemon.ds.service.PaperAuthorOrgService;
import us.codecraft.xsoup.Xsoup;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.apache.batik.svggen.SVGStylingAttributes.set;

/**
 * Created by jishu12 on 2017/3/7.
 */
public class PaperAuthorParser extends ParserAbs  {
    PapaerAuthorService service =context.getBean(PapaerAuthorService.class);
    public PaperAuthorParser(Integer id){
        super(id);
    }

    @Override
    public void doc2Entity() {
        String email=Xsoup.compile(String.format("//*[@id=\"abstract_tab_content\"]/table[1]/tbody/tr[9]/td/span/text()")).evaluate(doc).get();

        String authors= Xsoup.compile(String.format("/html/body/table[2]/tbody/tr/td/table[2]/tbody/tr[4]/td/text()")).evaluate(doc).get();
        String authors2= Xsoup.compile(String.format("/html/body/table[2]/tbody/tr/td/table[2]/tbody/tr[8]/td/text()")).evaluate(doc).get();
        String[]  authoList = authors.trim().split(",");
        String[]  authoList2 = authors2.trim().split(",");
        Integer index = 0;
        for (String x:authoList ) {
            String name = x.trim();
            PaperAuthor a = new PaperAuthor();
            a.setPaper_id(1);
            a.setAuthor_sno(index);
            a.setAuthor_name(name);
            a.setAuthor_name2(authoList2[index]);

            a.setAuthor_corres(1);
            Pattern remail =  Pattern.compile("[\\w!#$%&'*+/=?^_`{|}~-]+(?:\\.[\\w!#$%&'*+/=?^_`{|}~-]+)*@(?:[\\w](?:[\\w-]*[\\w])?\\.)+[\\w](?:[\\w-]*[\\w])?");
            Matcher memail = remail.matcher(email);
            Pattern rphone =  Pattern.compile("\\d{3}-\\d{8}|\\d{4}-\\{7,8}");
            Matcher mphone = rphone.matcher(email);
            if(memail.find()|mphone.find())
            {
                if(email.contains(name))
                    a.setEmail(memail.group(0)==null?mphone.group(0):memail.group(0));
            }else{

                a.setEmail(null);
            }
            index++;
            service.saveEntity(a);
        }

    }

    @Override
    public ParserAbs getEntity() {
        return null;
    }
}
