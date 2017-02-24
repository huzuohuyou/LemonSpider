package com.lemon.spider.kqyxyj;

import com.lemon.commons.file.FileUtil;
import com.lemon.spider.FileSession;

/**
 * Created by bob on 2017/1/13.
 */
public class HtmlWraper {

    /**
     * @ref template.html
     *
     <!DOCTYPE html>
     <html lang="en">
     <head>
     <meta charset="UTF-8">
     <title>比美特模板页</title>
     <link rel="stylesheet" href="../../../include/ncbi.min.css" type="text/css">
     <link rel="stylesheet" href="../../../include/pmc.min.css" type="text/css">
     <link rel="stylesheet" href="../../../include/jquery.uiw.min.css" type="text/css" >
     <link rel="stylesheet" href="../../../include/bimt-tpl.css" type="text/css">
     <script type="text/javascript" src="../../../include/jig.min.js"></script>
     <script type="text/javascript" src="../../../include/jquery.uiw.min.js"></script>
     <script type="text/javascript" src="../../../include/common.min.js"></script>
     </head>
     <body>

     <!-- head -->
     <div id="header-bar" class="header-bar header-black">
     <a href="http://www.bimt.com" class="logo"></a>
     </div>
     <!-- //head -->

     <div class="grid">
     <div class="layout-page-body">
     <div class="hide-overflow article lit-style content pmc-wm slang-en page-box">
     <!--main-content-->


     内容


     <!--post-content-->
     <hr class="whole_rhythm no_bottom_margin"/>
     <div class="courtesy-note no_margin small">Articles from <span class="acknowledgment-journal-title">Environmental Health Insights</span>
     are provided here courtesy of <strong>Libertas Academica</strong>
     </div>
     </div>
     </div>
     </div>


     <!--底-->
     <div class="foot-bar">
     <div class="foot-copyright">
     <span>京ICP备09056041号-8</span>
     <span>京公网安备110106000705号</span>
     <span>违法和不良信息举报电话：4006-120-585</span>
     <span>Copyright © 2004-2016 比美特医护在线（北京）科技有限公司 版权所有</span>
     </div>
     </div>
     <!--底end-->

     </body>
     </html>

     * <!DOCTYPE html><html lang="en"><head><meta charset="UTF-8"><title>比美特</title><link rel="stylesheet" href="../../../include/ncbi.min.css" type="text/css"><link rel="stylesheet" href="../../../include/pmc.min.css" type="text/css"><link rel="stylesheet" href="../../../include/jquery.uiw.min.css" type="text/css"><link rel="stylesheet" href="../../../include/bimt-tpl.css" type="text/css"><script type="text/javascript" src="../../../include/jig.min.js"></script><script type="text/javascript" src="../../../include/jquery.uiw.min.js"></script><script type="text/javascript" src="../../../include/common.min.js"></script></head><body><div id="header-bar" class="header-bar header-black"><a href="http://www.bimt.com" class="logo"></a></div><div class="grid"><div class="layout-page-body"><div class="hide-overflow article lit-style content pmc-wm slang-en page-box">内容 <hr class="whole_rhythm no_bottom_margin"><div class="courtesy-note no_margin small">Articles from <span class="acknowledgment-journal-title">Environmental Health Insights</span> are provided here courtesy of <strong>Libertas Academica</strong></div></div></div></div><div class="foot-bar"><div class="foot-copyright"><span>京ICP备09056041号-8</span> <span>京公网安备110106000705号</span> <span>违法和不良信息举报电话：4006-120-585</span> <span>Copyright © 2004-2016 比美特医护在线（北京）科技有限公司 版权所有</span></div></div></body></html>
     */
    class PaperWraper {
        public final static String HEAD = "<!DOCTYPE html><html lang=\"en\"><head><meta charset=\"UTF-8\"><title>比美特</title><link rel=\"stylesheet\" href=\"/pmcc/include/ncbi.min.css\" type=\"text/css\"><link rel=\"stylesheet\" href=\"/pmcc/include/pmc.min.css\" type=\"text/css\"><link rel=\"stylesheet\" href=\"/pmcc/include/jquery.uiw.min.css\" type=\"text/css\"><link rel=\"stylesheet\" href=\"/pmcc/include/bimt-tpl.css\" type=\"text/css\"><script type=\"text/javascript\" src=\"/pmcc/include/jig.min.js\"></script><script type=\"text/javascript\" src=\"/pmcc/include/jquery.uiw.min.js\"></script><script type=\"text/javascript\" src=\"/pmcc/include/common.min.js\"></script></head><body><div id=\"header-bar\" class=\"header-bar header-black\"><a href=\"http://www.bimt.com\" class=\"logo\"></a></div><div class=\"toolbar\"><a href=\"{prev}\">上一篇</a> <a href=\"/pmcc/articles/\" class=\"fold\">目录</a> <a href=\"{next}\">下一篇</a> <a href=\"{pdf}\" class=\"viewpdf\" style=\"display: {visible};\">查看PDF版本</a></div><div class=\"grid\"><div class=\"layout-page-body\"><div class=\"hide-overflow article lit-style content pmc-wm slang-en page-box\">";
        public final static String FOOT = "</div></div></div><div class=\"toolbar\"><a href=\"{prev}\">上一篇</a> <a href=\"/pmcc/articles/\" class=\"fold\">目录</a> <a href=\"{next}\">下一篇</a> <a href=\"{pdf}\" class=\"viewpdf\" style=\"display:{visible};\">查看PDF</a></div><div class=\"foot-bar\"><div class=\"foot-copyright\"><span>京ICP备09056041号-8</span> <span>京公网安备110106000705号</span> <span>违法和不良信息举报电话：4006-120-585</span> <span>Copyright © 2004-2016 比美特医护在线（北京）科技有限公司 版权所有</span></div></div></body></html>";
    }


    /**
     * @ref template-table.html
     * <!DOCTYPE html>
    <html lang="en">
    <head>
    <meta charset="UTF-8">
    <title>比美特</title>

    <link rel="stylesheet" href="../../../../include/ncbi.min.css" type="text/css">
    <link rel="stylesheet" href="../../../../include/pmc.min.css" type="text/css">
    <link rel="stylesheet" href="../../../../include/jquery.ui.widgets.min.css" type="text/css">
    <link rel="stylesheet" href="../../../../include/bimt-tpl.css" type="text/css">
    <script type="text/javascript" src="../../../../include/jig.min.js"></script>
    <script type="text/javascript" src="../../../../include/jquery.ui.widgets.min.js"></script>
    <script type="text/javascript" src="../../../../include/common.min.js"></script>
    </head>
    <body>

    <!-- head -->
    <div id="header-bar" class="header-bar header-black">
    <a href="http://www.bimt.com" class="logo"></a>
    </div>
    <!-- //head -->
    <div class="grid">
    <div class="layout-page-body">




    </div>
    </div>
    <!--底-->
    <div class="foot-bar">
    <div class="foot-copyright">
    <span>京ICP备09056041号-8</span>
    <span>京公网安备110106000705号</span>
    <span>违法和不良信息举报电话：4006-120-585</span>
    <span>Copyright © 2004-2016 比美特医护在线（北京）科技有限公司 版权所有</span>
    </div>
    </div>
    <!--底end-->
    </body>
    </html>

    <!DOCTYPE html><html lang="en"><head><meta charset="UTF-8"><title>比美特</title><link rel="stylesheet" href="../../../../include/ncbi.min.css" type="text/css"><link rel="stylesheet" href="../../../../include/pmc.min.css" type="text/css"><link rel="stylesheet" href="../../../../include/jquery.ui.widgets.min.css" type="text/css"><link rel="stylesheet" href="../../../../include/bimt-tpl.css" type="text/css"><script type="text/javascript" src="../../../../include/jig.min.js"></script><script type="text/javascript" src="../../../../include/jquery.ui.widgets.min.js"></script><script type="text/javascript" src="../../../../include/common.min.js"></script></head><body><div id="header-bar" class="header-bar header-black"><a href="http://www.bimt.com" class="logo"></a></div><div class="grid"><div class="layout-page-body"></div></div><div class="foot-bar"><div class="foot-copyright"><span>京ICP备09056041号-8</span> <span>京公网安备110106000705号</span> <span>违法和不良信息举报电话：4006-120-585</span> <span>Copyright © 2004-2016 比美特医护在线（北京）科技有限公司 版权所有</span></div></div></body></html>
     */
    class Ref {
        public final static String HEAD = "<!DOCTYPE html><html lang=\"en\"><head><meta charset=\"UTF-8\"><title>比美特</title><link rel=\"stylesheet\" href=\"../../../../include/ncbi.min.css\" type=\"text/css\"><link rel=\"stylesheet\" href=\"../../../../include/pmc.min.css\" type=\"text/css\"><link rel=\"stylesheet\" href=\"../../../../include/jquery.ui.widgets.min.css\" type=\"text/css\"><link rel=\"stylesheet\" href=\"../../../../include/bimt-tpl.css\" type=\"text/css\"><script type=\"text/javascript\" src=\"../../../../include/jig.min.js\"></script><script type=\"text/javascript\" src=\"../../../../include/jquery.ui.widgets.min.js\"></script><script type=\"text/javascript\" src=\"../../../../include/common.min.js\"></script></head><body><div id=\"header-bar\" class=\"header-bar header-black\"><a href=\"http://www.bimt.com\" class=\"logo\"></a></div><div class=\"grid\"><div class=\"layout-page-body\">";
        public final static String FOOT = "</div></div><div class=\"foot-bar\"><div class=\"foot-copyright\"><span>京ICP备09056041号-8</span> <span>京公网安备110106000705号</span> <span>违法和不良信息举报电话：4006-120-585</span> <span>Copyright © 2004-2016 比美特医护在线（北京）科技有限公司 版权所有</span></div></div></body></html>";
    }

    private final static String Holder_Prev = "\\{prev\\}", Holder_Next = "\\{next\\}", Holder_PDF = "\\{pdf\\}", Holder_Visible = "\\{visible\\}";

    public static void writePaperHtml(String cleanText, FileSession fileSession) {
        String tailer = cleanText.substring(cleanText.length()-50);
        if(!tailer.contains("</ul>")) {
            cleanText = cleanText + "</div>";
        }

        FileUtil.writeAllText(cleanText, fileSession.getJspOsPath());

        String prev = "/pmcc/articles/" + fileSession.parent + "/" + (fileSession.pmcId-1);
        String h = PaperWraper.HEAD.replaceFirst(Holder_Prev, prev);
        String f = PaperWraper.FOOT.replaceFirst(Holder_Prev, prev);

        String next = "/pmcc/articles/" + fileSession.parent + "/" + (fileSession.pmcId+1);
        h = h.replaceFirst(Holder_Next, next);
        f = f.replaceFirst(Holder_Next, next);

        if(fileSession.isPdfExists()) {
            String pdf = "/pmcc/articles/"+fileSession.parent+"/"+fileSession.pmcId +"/"+fileSession.pmcId+".pdf";
            h = h.replaceFirst(Holder_PDF, pdf);
            f = f.replaceFirst(Holder_PDF, pdf);

            h = h.replaceFirst(Holder_Visible, "block");
            f = f.replaceFirst(Holder_Visible, "block");
        } else {
            h = h.replaceFirst(Holder_PDF, "#");
            f = f.replaceFirst(Holder_PDF, "#");

            h = h.replaceFirst(Holder_Visible, "none");
            f = f.replaceFirst(Holder_Visible, "none");
        }

        FileUtil.writeAllText(h + cleanText + f, fileSession.getHtmlOsPath());
    }

}
