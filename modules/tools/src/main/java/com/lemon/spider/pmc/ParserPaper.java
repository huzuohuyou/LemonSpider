package com.lemon.spider.pmc;

import com.lemon.commons.file.FileUtil;
import com.lemon.commons.spider.XDownloader;
import com.lemon.ds.entity.Paper;
import com.lemon.ds.entity.PaperAsset;
import com.lemon.ds.entity.PaperEmail;
import com.lemon.ds.entity.PaperLog;
import com.lemon.ds.service.PaperAssetService;
import com.lemon.ds.service.PaperEmailService;
import com.lemon.ds.service.PaperLogService;
import com.lemon.ds.service.PaperService;
import com.lemon.spider.FileSession;
import com.lemon.spider.GlobalClean;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;
import org.springframework.stereotype.Component;
import us.codecraft.xsoup.XElements;
import us.codecraft.xsoup.Xsoup;

import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * sample links
 * only pdf
 * http://pubmedcentralcanada.ca/pmcc/articles/PMC2125087/;jsessionid=0CC6B01018E52C5C995F585DBE299DAE.eider?lang=en-ca#__abstractid439074title
 *
 * no publish online date
 * http://pubmedcentralcanada.ca/pmcc/articles/PMC3327067/;jsessionid=0CC6B01018E52C5C995F585DBE299DAE.eider?lang=en-ca#S9
 *
 * full
 * http://pubmedcentralcanada.ca/pmcc/articles/PMC3325087/;jsessionid=0CC6B01018E52C5C995F585DBE299DAE.eider?lang=en-ca#__abstractid439074title
 * http://pubmedcentralcanada.ca/pmcc/articles/PMC3127067/;jsessionid=0CC6B01018E52C5C995F585DBE299DAE.eider?lang=en-ca#S9
 * http://pubmedcentralcanada.ca/pmcc/articles/PMC3000000/;jsessionid=0CC6B01018E52C5C995F585DBE299DAE.eider?lang=en-ca#S9
 * full v2
 * http://pubmedcentralcanada.ca/pmcc/articles/PMC3125087/;jsessionid=0CC6B01018E52C5C995F585DBE299DAE.eider?lang=en-ca#__abstractid439074title
 *
 */

@Component
public final class ParserPaper extends Thread {
	private static final String UrlPrefix4Spider = "/pmcc/articles/PMC";


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


	private List<PaperEmail> listEmail = new ArrayList<>();
	private FileSession fileSession;

	private int mode;
	private int left;
	private int count = 0;
	private static ApplicationContext context = new FileSystemXmlApplicationContext("classpath*:applicationContext.xml");
	public ParserPaper(int mode, int left) {
		this.mode = mode;
		this.left = left;
		service = context.getBean(PaperService.class);
		plService = context.getBean(PaperLogService.class);
		peService = context.getBean(PaperEmailService.class);
		prService = context.getBean(PaperAssetService.class);

//		aService = context.getBean(AuthorService.class);
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

		Integer pmcId = plService.findNextStartId(mode, left, Spider.PMC_Start, Spider.PMC_Stop);
		if(pmcId == null) {
			pmcId = Spider.PMC_Start;
		} else if(pmcId < Spider.PMC_Start) {
			pmcId = Spider.PMC_Start;
		}
		for(; pmcId%mode!=left; pmcId++) {
		}
		System.out.println("started success. spider from " + pmcId);
		for (; pmcId<Spider.PMC_Stop; pmcId += mode) {

			long start = System.currentTimeMillis();
			PaperLog pl = new PaperLog();
			pl.setPmcId(pmcId);
			pl.setStatus(ERROR);
			try {
				Paper p = runOne(pmcId, pl);
				long timeElips = (System.currentTimeMillis() - start) / 1000;
				if(p != null) {
					++XDownloader.Total_Papers;
					System.out.println("OK :  " + left + "\t" + (++count) + "\tPMC" + pmcId + "\tused(s): " + timeElips + "  \tsize(KB): " + p.getSizeKB());

					if(XDownloader.Total_Papers % 10 == 0) {
						System.out.println("----------------------------------------------------------------------------------");
						Date now = new Date();
						long elaped = (now.getTime() - Spider.APP_Start)/(1000*60);
						String time;
						if(elaped > 60) {
							time = "" + elaped/60 + "h " + elaped%60 + "m";
						} else {
							time = elaped + "m";
						}
						if(elaped < 1) {
							elaped = 1;
						}
						long speed = (XDownloader.Total_Papers * 60) / elaped;
						System.out.println("Time: " + now + "\tElaped: " + time + "\tPapers/Hour: " + speed + "\nTotal Request: " + XDownloader.Total_Requests + "\tTotal Paper: " + XDownloader.Total_Papers + "  \tavg(R/P): " + (1.0*XDownloader.Total_Requests/XDownloader.Total_Papers));
						System.out.println("----------------------------------------------------------------------------------");
					}
					continue;
				}
			} catch (Exception e) {
				pl.setErrMsg(e.getMessage());
				try {
					plService.saveEntity(pl);
				} catch (Exception ee) {}
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
		fileSession = new FileSession(pmcId);

		//1. 摘要
		String url = UrlPrefix4Spider + pmcId + "/";
		String rawData = XDownloaderPMC.sharedInstance().getHtml(url);
		if(rawData==null) {
			HtmlWraper.writePaperHtml(SkipText, fileSession);
			pl.setStatus(NOTFOUND);
			pl.setErrMsg("Page not available");
			plService.saveOrUpdate(pl);
			return p;
		}
		pl.onAbstractOK();

		//2. PDF
		Document doc = Jsoup.parse(rawData);
		String sideNav = Xsoup.compile("//div[@class='layout-page-col-nav']").evaluate(doc).get();
		String sideText = Xsoup.compile("//ul[@class=alternative-views-of-article]").evaluate(doc).get();
		if(!parseAndDownloadPdf(sideNav, sideText, pl, p)) {
			pl.setErrMsg("download pdf error.");
			plService.saveOrUpdate(pl);
			return null;
		}

		//3. Html fulltext
		String fullText = Xsoup.compile("//div[@class=jig-ncbiinpagenav]").evaluate(doc).get();
		if(fullText == null) {
			fullText = Xsoup.compile("//div[@class='hide-overflow article lit-style content pmc-wm slang-en page-box']").evaluate(doc).get();
		}
		if (fullText == null) {
			pl.setErrMsg("can't get html body.");
			plService.saveOrUpdate(pl);
			return null;
		}
		if(!parseFulltext(fullText, pl, p)) {
			pl.setErrMsg("paper html error.");
			plService.saveOrUpdate(pl);
			return null;
		}

		for(PaperEmail pe : listEmail) {
			peService.saveOrUpdatePE(pe);
		}
		pl.setStatus(YES);
		service.saveEntity(p);
		plService.saveOrUpdate(pl);

//		//4. parse author tuple <sup>\d+</sup>
//		if(!parseAuthors(p)) {
//			pl.setErrMsg("paper author tuple error.");
//			plService.saveEntity(pl);
//			return null;
//		}

		return p;
	}


	private final static Pattern patLink = Pattern.compile("href=\"/pmcc/articles/PMC[\\d]+/pdf/[^\\.]+\\.pdf");
	private final static Pattern patSize = Pattern.compile("\\([\\d\\.]+(K|M)");
	/**
	 * <a class="sidefm-pmclink" href="/pmcc/articles/PMC3000000/pdf/ehi-2010-087.pdf">PDF (539K)</a>
	 *
	 * http://pubmedcentralcanada.ca/pmcc/articles/PMC3000000/pdf/ehi-2010-087.pdf
	 * @param text
	 * @return true,继续;  false,有错误,跳过。
     */
	public boolean parseAndDownloadPdf(String sideFull, String text, PaperLog pl, Paper p) {
		FileUtil.writeAllText(sideFull, fileSession.getHtmlSideOsPath());

		Matcher m = patLink.matcher(text);
		if(!m.find()) {
			p.setSizeKB(0);
			return true;
		}

		String url = text.substring(m.start()+6, m.end());
		m = patSize.matcher(text);
		if(!m.find()) {
			return false;
		}
		String sizeStr = text.substring(m.start()+1, m.end()).toUpperCase();
		int sizeKB;
		if(sizeStr.endsWith("M")) {
			sizeKB = (int)(Float.parseFloat(sizeStr.substring(0, sizeStr.length()-1)) * 1024);
		} else {
			sizeKB = (int)(Float.parseFloat(sizeStr.substring(0, sizeStr.length()-1)));
		}


		String imgRelativePath = url.substring(url.indexOf("/pdf/"));
		if(prService.existsByPmcIdAndImgPath(p.getPmcId(), imgRelativePath)) {
			p.setSizeKB(sizeKB);
			return true;
		}
		PaperAsset pi = new PaperAsset();
		pi.setPmcId(p.getPmcId());
		pi.setImgPath(imgRelativePath);

		if(!XDownloaderPMC.sharedInstance().download2File(url, fileSession.getPdfOsPath())) {
			pi.setStatus(PaperAsset.ST_NO);
			prService.saveEntity(pi);
			return false;
		}
		prService.saveEntity(pi);


		pl.onPdfOK();
		p.setSizeKB(sizeKB);

		return true;
	}


	private final static Pattern patPageRange = Pattern.compile("\\d+–\\d+");
	private final static String PubDate = "Published online ";
	private final static Pattern patNihmsId = Pattern.compile("NIHMS\\d+");
	private final static Pattern patInverseEmail = Pattern.compile("(?:[\\w](?:[\\w-]*[\\w])?\\.)+[\\w](?:[\\w-]*[\\w])?@[\\w!#$%&'*+/=?^_`{|}~-]+(?:\\.[\\w!#$%&'*+/=?^_`{|}~-]+)*");
	/**
	 *
	 * @param text
	 * @param p
     * @return
     */
	private boolean parseFulltext(String text, PaperLog pl, Paper p) throws Exception {
		//1. save cleaned jsp & html
		String cleanText = GlobalClean.fixOutLinks4Papers(text);
		HtmlWraper.writePaperHtml(cleanText, fileSession);

		Document doc = Jsoup.parse(text);
		/**
		 * for 1 ~ 3
		 * <span class="cit">Environ Health Insights. 2010; 4: 87–91. </span>
		 * "clt" is unique
         */
		//1. journal
		String journalInfo = Xsoup.compile("//div[@class=small]/div/div/div//span[@class=cit]/allText()").evaluate(doc).get();
		if(journalInfo == null) {
			return false;
		}
		int tagVolEnd = journalInfo.indexOf(". ");
		if(tagVolEnd < 0) {
			return false;
		}
		p.setJournal(journalInfo.substring(0, tagVolEnd));

		//2. page range
		Matcher m = patPageRange.matcher(journalInfo);
		if(m.find()) {
			int tagMid = journalInfo.indexOf('–', m.start());
			p.setPageBegin(Integer.parseInt(journalInfo.substring(m.start(), tagMid)));
			p.setPageEnd(Integer.parseInt(journalInfo.substring(tagMid+1, m.end())));

			//3. volume
			String vol = journalInfo.substring(tagVolEnd + 2, m.start() - 2);
			p.setJournalVolume(vol);
		} else {
			//3. volume
			String vol = journalInfo.substring(tagVolEnd + 2);
			p.setJournalVolume(vol);
		}

		/**
		 * for 4 ~ 5
		 * published date & doi
		 * <div><span class="fm-vol-iss-date">Published online 2010 November 11. </span>  <span class="doi">doi:&nbsp; <a href="http://dx.doi.org/10.4137%2FEHI.S5392" target="pmc_ext" ref="reftype=other&amp;article-id=1754079&amp;issue-id=70386&amp;journal-id=1034&amp;FROM=Article%7CFront%20Matter&amp;TO=Content%20Provider%7CCrosslink%7CDOI&amp;rendering-type=normal">10.4137/EHI.S5392</a></span></div>
		 * or only doi
		 * <span class="doi">doi:&nbsp; <a href="http://dx.doi.org/10.4103%2F0019-5049.93341" target="pmc_ext" ref="reftype=other&amp;article-id=2070712&amp;issue-id=96197&amp;journal-id=1038&amp;FROM=Article%7CFront%20Matter&amp;TO=Content%20Provider%7CCrosslink%7CDOI&amp;rendering-type=normal">10.4103/0019-5049.93341</a></span>
		 * or neither
		 *
		 * class  fm-vol-iss-date 不唯一
		 * Published online  唯一
         */
		//4. publish online, maybe null
		String onlineInfo = Xsoup.compile("//div[@class=small]/div/div/div/span[@class=fm-vol-iss-date]/allText()").evaluate(doc).get();
		if(onlineInfo != null) {
			int tagPub = text.indexOf(PubDate);
			if(tagPub >= 0) {
				tagPub += PubDate.length();
				int end = text.indexOf(".", tagPub);
				String dateStr = text.substring(tagPub, end);
				Date date = null;
				try {
					date = PubDateFormate.parse(dateStr);
				} catch (Exception e) {
					try {
						date = PubDateFormate2.parse(dateStr);
					} catch (Exception e2) {
					}
				}
				if(date != null) {
					p.setOnlineDate(date);
				}
			}
		}

		//5. doi
		String doiInfo = Xsoup.compile("//div[@class=small]/div/div/div/span[@class=doi]/a/allText()").evaluate(doc).get();
		if(doiInfo != null) {
			p.setDoi(doiInfo);
		}

		/**
		 * <span>NIHMS301993</span>
         */
		//6. NIHMSID maybe exists
		String nihmsInfo = Xsoup.compile("//div[@class=fm-citation-ids]/div[@class=fm-citation-manuscriptid]/span/allText()").evaluate(doc).get();
		if(nihmsInfo != null) {
			m = patNihmsId.matcher(nihmsInfo);
			if (m.find()) {
				p.setNihmsId(Integer.parseInt(nihmsInfo.substring(m.start() + 5, m.end())));
			}
		}

		/**
		 * <h1 class="content-title">Bioremediation of Fluorophenols by Glycosylation with Immobilized Marine Microalga <em>Amphidinium Crassum</em></h1>
         */
		//7. title
		String titleInfo = Xsoup.compile("//div[@class='fm-sec half_rhythm no_top_margin']/h1[@class=content-title]/allText()").evaluate(doc).get();
		if(titleInfo == null) {
			return false;
		}
		p.setTitle(titleInfo);

		//8. author info
		String authors = Xsoup.compile("//div[@class='fm-sec half_rhythm no_top_margin']/div[@class=half_rhythm]/div[@class='contrib-group fm-author']/html()").evaluate(doc).get();
		if(authors != null) {
			p.setAuthors(authors);
		}

		//9. authorOrgs & contact email
		String authorOrgs = Xsoup.compile("//div[@class='fm-panel small half_rhythm']/html()").evaluate(doc).get();
		if(authorOrgs != null) {
			authorOrgs = authorOrgs.replaceAll("style=\"display:none\"", "");
			p.setAuthorOrgs(authorOrgs);

			m = patInverseEmail.matcher(authorOrgs);
			Set<String> emails = new TreeSet<>();
			while(m.find()) {
				String e = authorOrgs.substring(m.start(), m.end());
				emails.add(e);
			}
			for (String em : emails) {
				char[] arr = em.toCharArray();
				char[] dst = new char[arr.length];
				for(int i=arr.length-1, j=0; i>=0; --i, ++j) {
					dst[j] = arr[i];
				}

				String email = new String(dst);
				PaperEmail pe = new PaperEmail();
				pe.setPmcId(p.getPmcId());
				pe.setEmail(email);
				listEmail.add(pe);
			}
		}

		XElements els = Xsoup.compile("//div[@class='tsec sec']").evaluate(doc);
		if(els!=null && els.list().size()>0) {
			//10. abstract
			String firstSect = els.list().get(0);
			Document docFirstSec = Jsoup.parse(firstSect);
			String abstInfo = Xsoup.compile("//div[@class='tsec sec']/div").evaluate(docFirstSec).get();
			if(abstInfo != null) {
				p.setAbstr(abstInfo);
			} else {
				System.out.println("[warn]  No Abstract found!!!!");
			}

			//11. keywords
			String keywordInfo = Xsoup.compile("//div[@class='tsec sec']/div[@class=sec]/span[@class=kwd-text]/html()").evaluate(doc).get();
			if(keywordInfo != null) {
				p.setKeyword(keywordInfo);
			}
		}

		//12. spider all img
		if(!spiderImage(p.getPmcId(), text)) {
			return false;
		}

		XElements tableList = Xsoup.compile("//div[@class='table-wrap iconblock ten_col whole_rhythm clearfix']/html()").evaluate(doc);
		for(String tableStr : tableList.list()) {
			int tagRefId = tableStr.indexOf("rid-figpopup=\"");
			if(tagRefId < 0) {
				continue;
			}
			tagRefId += 14;
			int tagRefIdEnd = tableStr.indexOf("\"", tagRefId);
			String refId = tableStr.substring(tagRefId, tagRefIdEnd);

			String urlRef = UrlPrefix4Spider + p.getPmcId() + "/table/" + refId + "/";
			String html = XDownloaderPMC.sharedInstance().getHtml(urlRef);
			if(!parseRefTable(html, pl, p, refId)) {
				continue;
			}
		}

		XElements figureList = Xsoup.compile("//div[@class='fig iconblock ten_col whole_rhythm clearfix']/html()").evaluate(doc);
		for(String figureStr : figureList.list()) {
			int tagRefId = figureStr.indexOf("rid-figpopup=\"");
			if(tagRefId < 0) {
				continue;
			}
			tagRefId += 14;
			int tagRefIdEnd = figureStr.indexOf("\"", tagRefId);
			String refId = figureStr.substring(tagRefId, tagRefIdEnd);

			String urlRef = UrlPrefix4Spider + p.getPmcId() + "/figure/" + refId + "/";
			String html = XDownloaderPMC.sharedInstance().getHtml(urlRef);
			if(!parseRefFigure(html, pl, p, refId)) {
				continue;
			}
		}

		pl.onHtmlOK();

		return true;
	}


	private boolean parseRefFigure(String html, PaperLog pl, Paper p, String refId) throws Exception {
		Document doc = Jsoup.parse(html);

		String body = Xsoup.compile("//div[@class='hide-overflow article lit-style content pmc-wm slang-en page-box']").evaluate(doc).get();
		String cleanText = GlobalClean.fixOutLinks4Reffers(body);
		FileUtil.writeAllText(cleanText, fileSession.getFigureJspOsPath(refId));
		FileUtil.writeAllText(HtmlWraper.Ref.HEAD + cleanText + HtmlWraper.Ref.FOOT, fileSession.getFigureHtmlOsPath(refId));

		return spiderImage(p.getPmcId(), body);
	}


	private boolean parseRefTable(String html, PaperLog pl, Paper p, String refId) throws Exception {
		Document doc = Jsoup.parse(html);

		String body = Xsoup.compile("//div[@class='hide-overflow article lit-style content pmc-wm slang-en page-box-wide']").evaluate(doc).get();
		String cleanText = GlobalClean.fixOutLinks4Reffers(body);
		FileUtil.writeAllText(cleanText, fileSession.getTableJspOsPath(refId));
		FileUtil.writeAllText(HtmlWraper.Ref.HEAD + cleanText + HtmlWraper.Ref.FOOT, fileSession.getTableHtmlOsPath(refId));

		return spiderImage(p.getPmcId(), body);
	}


	private final static Pattern patImgSrc = Pattern.compile("=\"/pmcc/articles/PMC[\\d]+/bin/[^\"]+\"", Pattern.CASE_INSENSITIVE);
	private final static Pattern patImgInstanceSrc = Pattern.compile("src=\"/pmcc/articles/instance/[^\"]+\"", Pattern.CASE_INSENSITIVE);
	private final static Pattern PatImgCoreHtmlSrc = Pattern.compile("src=\"/corehtml/[^\"]+\"", Pattern.CASE_INSENSITIVE);

	/**
	 * <img src="/pmcc/articles/PMC3327067/bin/IJA-56-34-g003.gif"
	 * class="small-thumb" alt="Figure 1" title="Figure 1"
	 * src-large="/pmcc/articles/PMC3327067/bin/IJA-56-34-g003.jpg">
	 *
	 *
	 * <img src="/corehtml/pmc/pmcgifs/corrauth.gif" alt="corresponding author">
	 *
	 * @param text
	 * @return
	 */
	private boolean spiderImage(int pmcId, String text) {
		Matcher m = patImgSrc.matcher(text);
		while (m.find()) {
			String url = text.substring(m.start()+2, m.end()-1);
//			String urllower = url.toLowerCase();
//			if(!urllower.endsWith(".jpg") && !urllower.endsWith(".gif") && !urllower.endsWith(".png") && !urllower.endsWith(".jpeg")) {
//				continue;
//			}
			String imgRelativePath = url.substring(url.indexOf("/bin"));
			if(prService.existsByPmcIdAndImgPath(pmcId, imgRelativePath)) {
				continue;
			}
			PaperAsset pi = new PaperAsset();
			pi.setPmcId(pmcId);
			pi.setImgPath(imgRelativePath);

			if(!XDownloaderPMC.sharedInstance().download2File(url, fileSession.getImgPath(imgRelativePath))) {
				pi.setStatus(PaperAsset.ST_NO);
				prService.saveEntity(pi);
				return false;
			}
			prService.saveEntity(pi);
		}

		if(!parseCommonImgSrcPattern(patImgInstanceSrc, text)) {
			return false;
		}

		if(!parseCommonImgSrcPattern(PatImgCoreHtmlSrc, text)) {
			return false;
		}

		return true;
	}

	private boolean parseCommonImgSrcPattern(Pattern pat, String text) {
		Matcher m = pat.matcher(text);
		while (m.find()) {
			String url = text.substring(m.start()+5, m.end()-1);
			if(prService.existsByPmcIdAndImgPath(0, url)) {
				continue;
			}

			PaperAsset pi = new PaperAsset();
			pi.setPmcId(0);
			pi.setImgPath(url);

			if(!XDownloaderPMC.sharedInstance().download2File(url, fileSession.getCommonResourcePath(url))) {
				pi.setStatus(PaperAsset.ST_NO);
				prService.saveEntity(pi);
				return false;
			}
			prService.saveEntity(pi);
		}
		return true;
	}


	/**
	 * authorsName
	 * <div class="contrib-group fm-author"><a href="http://www.ncbi.nlm.nih.gov/pubmed/?term=Shimoda%20K%5Bauth%5D">Kei Shimoda</a><sup>1</sup> and  <a href="http://www.ncbi.nlm.nih.gov/pubmed/?term=Hamada%20H%5Bauth%5D">Hiroki Hamada</a><sup>2</sup><sup></sup></div>
	 *
	 * authorsInfo
	 * <div class="fm-panel small half_rhythm"><div class="fm-authors-info fm-panel half_rhythm" id="id643518_ai" style="display: block;"><div class="fm-affl"><sup>1</sup>Department of Chemistry, Faculty of Medicine, Oita University, 1-1 Hasama-machi, Oita 879-5593, Japan</div><div class="fm-affl"><sup>2</sup>Department of Life Science, Faculty of Science, Okayama University of Science, 1-1 Ridai-cho, Kita-ku, Okayama 700-0005, Japan</div><div id="c1-ehi-2010-087">Corresponding author email: <a href="mailto:dev@null" data-email="pj.ca.suo.sld@adamah" class="oemail">pj.ca.suo.sld@adamah</a></div></div><div class="togglers"><a href="http://pubmedcentralcanada.ca/pmcc/articles/PMC3000000/;jsessionid=0CC6B01018E52C5C995F585DBE299DAE.eider?lang=en-ca#" class="pmctoggle toggled" rid="id643518_ai">Author information <span>▼</span></a> <a href="http://pubmedcentralcanada.ca/pmcc/articles/PMC3000000/;jsessionid=0CC6B01018E52C5C995F585DBE299DAE.eider?lang=en-ca#" class="pmctoggle" rid="id643518_cpl">Copyright and License information <span>►</span></a></div><div class="fm-article-notes fm-panel half_rhythm"></div><div class="fm-cpl-info fm-panel hide half_rhythm" id="id643518_cpl" style="display:none"><div class="fm-copyright half_rhythm"><a href="http://pubmedcentralcanada.ca/pmc/about/copyright.html">Copyright</a> © 2010 the author(s), publisher and licensee Libertas Academica Ltd.</div><div class="fm-copyright half_rhythm">This is an open access article. Unrestricted non-commercial use is permitted provided the original work is properly cited.</div></div></div>
	 * @param authorsName
	 * @param authorsInfo
     * @return
     */
//	private final static Pattern patSup = Pattern.compile("<sup>[\\d]+</sup>");
//	private final static Pattern patSupTag = Pattern.compile(">[^<]+</a><");
//	private final static Pattern patEmail = Pattern.compile(Cons.Rex.User_Email);
//
//	private boolean parseAuthors(Paper p) {
//		String authorsName = p.getAuthors();
//		String authorsAddr = p.getAuthorOrgs();
//
//		Map<Integer, String> orgMap = new TreeMap<>();
//
//		Document docInfo = Jsoup.parse(authorsAddr);
//		XElements xe = Xsoup.compile("//div[@class=fm-affl]/").evaluate(docInfo);
//		for(String info : xe.list()) {
//			int sup = 0;
//			String org = info;
//
//			Matcher m = patSup.matcher(info);
//			if (m.find()) {
//				sup = Integer.parseInt(info.substring(m.start()+5, m.end()-6));
//				org = info.substring(m.end());
//			}
//
//			orgMap.put(sup, org);
//		}
//
//		authorsName = authorsName.replaceAll("<sup></sup>", "");
//		authorsName = authorsName.replaceAll(" and  ", "");
//		Matcher m = patSupTag.matcher(authorsName);
//		while(m.find()) {
//			String name = authorsName.substring(m.start()+1, m.end()-5);
//			int sup = 0;
//			if(authorsName.length() >= m.end()+12) {
//				String nameLater = authorsName.substring(m.end() - 1, m.end() + 12);
//				Matcher m2 = patSup.matcher(nameLater);
//				if(m2.find()) {
//					sup = Integer.parseInt(nameLater.substring(m2.start()+5, m.end()-6));
//				}
//			}
//			String addr = orgMap.get(sup);
//			if(addr == null) {
//				addr = orgMap.get(0);
//				sup = 0;
//			}
//
//			Author au = new Author();
//			au.setName(name);
//			au.setAddress(addr);
//
//			int pos = addr.lastIndexOf(",");
//			au.setCountry(addr.substring(pos + 1));
//
//			int pos2 = addr.lastIndexOf(",", pos-1);
//			au.setCity(addr.substring(pos2, pos));
//
//			Matcher m3 = patEmail.matcher(addr);
//			if(m3.find()) {
//				au.setEmail(addr.substring(m3.start(), m3.end()));
//			}
//
//			aService.saveEntity(au);
//
//			AuthorPapers ap = new AuthorPapers();
//			ap.setAuthorId(au.getId());
//			ap.setPaperId(p.getId());
//			ap.setPmcId(p.getPmcId());
//			ap.setRank(sup);
//			apService.saveEntity(ap);
//		}
//
//		return true;
//	}
}