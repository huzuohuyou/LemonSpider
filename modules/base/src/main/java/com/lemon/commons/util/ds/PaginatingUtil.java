package com.lemon.commons.util.ds;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

public class PaginatingUtil {

	/*
	 * 生成翻页字符串
	 */
	public static String genPageHtml(HttpServletRequest request, int pageCount, int pageSeq) {
		StringBuffer pageBuffer = new StringBuffer();

		String url = request.getRequestURL().toString();
		String para = request.getQueryString();
		Map<String, String> paraMap = parseUrlPara(para);

		pageBuffer.append("<div class=\"paginating\">");
		if (pageSeq <= 1) {
			pageBuffer.append("<span class=\"disabled\"> &lt; 上一页</span>");
		} else {
			pageBuffer.append("<a href=\"").append(composeUrl(url, pageSeq - 1, paraMap)).append("\"> &lt; 上一页</a>");
		}
		if (pageSeq >= 2) {
			pageBuffer.append("<a href=\"").append(composeUrl(url, 1, paraMap)).append("\">1</a>");
		}
		if (pageSeq >= 4) {
			pageBuffer.append("...");
			pageBuffer.append("<a href=\"").append(composeUrl(url, pageSeq - 2, paraMap)).append("\">").append(pageSeq - 2).append("</a>");
			pageBuffer.append("<a href=\"").append(composeUrl(url, pageSeq - 1, paraMap)).append("\">").append(pageSeq - 1).append("</a>");
		} else if (pageSeq == 3) {
			pageBuffer.append("<a href=\"").append(composeUrl(url, pageSeq - 1, paraMap)).append("\">").append(pageSeq - 1).append("</a>");
		}
		pageBuffer.append("<span class=\"current\">").append(pageSeq).append("</span>");
		if (pageCount - pageSeq >= 3) {
			pageBuffer.append("<a href=\"").append(composeUrl(url, pageSeq + 1, paraMap)).append("\">").append(pageSeq + 1).append("</a>");
			pageBuffer.append("<a href=\"").append(composeUrl(url, pageSeq + 2, paraMap)).append("\">").append(pageSeq + 2).append("</a>");
			pageBuffer.append("...");
		} else if (pageCount - pageSeq == 2) {
			pageBuffer.append("<a href=\"").append(composeUrl(url, pageSeq + 1, paraMap)).append("\">").append(pageSeq + 1).append("</a>");
		}
		if (pageCount > pageSeq) {
			pageBuffer.append("<a href=\"").append(composeUrl(url, pageCount, paraMap)).append("\">").append(pageCount).append("</a>");
			pageBuffer.append("<a href=\"").append(composeUrl(url, pageSeq + 1, paraMap)).append("\">下一页 &gt; </a>");
		} else {
			pageBuffer.append("<span class=\"disabled\">下一页 &gt; </span>");
		}
		pageBuffer.append("</div>");

		return pageBuffer.toString();
	}

	// 分析链接之中的参数
	private static Map<String, String> parseUrlPara(String urlPara) {
		Map<String, String> paraMap = new HashMap<String, String>();

		if (urlPara == null || urlPara.equals("")) {
			return paraMap;
		}

		String[] parameters = urlPara.split("&");
		for (String parameter : parameters) {
			if (parameter == null || parameter.equals("")) {
				continue;
			}
			String[] paras = parameter.split("=");
			if (paras[0] == null || "page".equals(paras[0])) {
				continue;
			}
			if (paras.length == 1) {
				paraMap.put(paras[0], "");
			} else {
				paraMap.put(paras[0], paras[1]);
			}
		}

		return paraMap;
	}

	// 组装链接
	private static String composeUrl(String url, int page, Map<String, String> paraMap) {
		StringBuffer urlBuffer = new StringBuffer(url);
		urlBuffer.append("?");
		int paraSeq = 1;

		if (paraMap != null) {
			Set<String> keySet = paraMap.keySet();
			for (String key : keySet) {
				if (paraSeq != 1) {
					urlBuffer.append("&");
				}
				urlBuffer.append(key).append("=").append(paraMap.get(key));
				paraSeq++;
			}
		}

		if (paraSeq != 1) {
			urlBuffer.append("&");
		}
		urlBuffer.append("page=").append(page);

		return urlBuffer.toString();
	}

	/* 根据个数计算页数 */
	public static Integer getPageCount(Integer totalNumber, Integer pageNumber) {
		Integer pageCount;
		if (!totalNumber.equals(0)) {
			pageCount = (totalNumber + pageNumber - 1) / pageNumber;
		} else {
			pageCount = 1;
		}
		return pageCount;
	}
}