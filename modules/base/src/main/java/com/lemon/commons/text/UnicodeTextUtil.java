package com.lemon.commons.text;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.lemon.commons.log.Log;
import com.lemon.commons.math.MathPlus;

public class UnicodeTextUtil {
	@SuppressWarnings("unused")
	private final static Log log = Log.getLogger(UnicodeTextUtil.class);

	private final static Pattern patChineseNum = Pattern.compile("[一二三四五六七八九十]{1}");
	private final static Map<String, String> mapChineseNum = new HashMap<String, String>();
	static {
		mapChineseNum.put("一", "1");
		mapChineseNum.put("二", "2");
		mapChineseNum.put("三", "3");
		mapChineseNum.put("四", "4");
		mapChineseNum.put("五", "5");
		mapChineseNum.put("六", "6");
		mapChineseNum.put("七", "7");
		mapChineseNum.put("八", "8");
		mapChineseNum.put("九", "9");
		mapChineseNum.put("十", "1");
	}
	/**
	 * convert 一 ~ 十  to  1~1
	 * @param chinese
	 * @return
	 */
	public static String replaceChineseNum(String chinese) {
		if(chinese == null || "".equals(chinese)) {
			return chinese;
		}
		String replaced = chinese;
		Matcher m = patChineseNum.matcher(chinese);
		while(m.find()) {
			String sub = chinese.substring(m.start(), m.end());
			String rep = mapChineseNum.get(sub);
			replaced = replaced.replaceFirst(sub, rep);
		}
		return replaced;
	}

	public static int calculateStringDistance(String strA, String strB) {
		if(strA==null && strB==null) {
			return 0;
		}
		if(strA==null) {
			return strB.length();
		}
		if(strB==null) {
			return strA.length();
		}
		if(strA.equals(strB)) {
			return 0;
		}
		return calculateStringDistance(strA, 0, strA.length()-1,  strB, 0, strB.length()-1);
	}

	public static float levenshtein(String str1,String str2) {
		//计算两个字符串的长度。
		int len1 = str1.length();
		int len2 = str2.length();
		//建立上面说的数组，比字符长度大一个空间
		int[][] dif = new int[len1 + 1][len2 + 1];
		//赋初值，步骤B。
		for (int a = 0; a <= len1; a++) {
			dif[a][0] = a;
		}
		for (int a = 0; a <= len2; a++) {
			dif[0][a] = a;
		}
		//计算两个字符是否一样，计算左上的值
		int temp;
		for (int i = 1; i <= len1; i++) {
			for (int j = 1; j <= len2; j++) {
				if (str1.charAt(i - 1) == str2.charAt(j - 1)) {
					temp = 0;
				} else {
					temp = 1;
				}
				//取三个值中最小的
				dif[i][j] = MathPlus.min(dif[i - 1][j - 1] + temp, dif[i][j - 1] + 1, dif[i - 1][j] + 1);
			}
		}
		//计算相似度
		float similarity =1 - (float) dif[len1][len2] / Math.max(str1.length(), str2.length());
		//取数组右下角的值，同样不同位置代表不同字符串的比较
		//		log.debug("比较字符串[{}]与[{}]，差异步骤:{}，相似度:[{}]", str1, str2, dif[len1][len2], similarity);
		return similarity;
	}


	private static int calculateStringDistance(String strA, int pABegin, int pAEnd, String strB, int pBBegin, int pBEnd) {
		if(pABegin > pAEnd)
		{
			if(pBBegin > pBEnd)
				return 0;
			else
				return pBEnd - pBBegin + 1;
		}

		if(pBBegin > pBEnd)
		{
			if(pABegin > pAEnd)
				return 0;
			else
				return pAEnd - pABegin + 1;
		}

		if(strA.charAt(pABegin) == strB.charAt(pBBegin))
		{
			return calculateStringDistance(strA, pABegin+1, pAEnd, strB, pBBegin+1, pBEnd);
		}
		else
		{
			int t1 = calculateStringDistance(strA, pABegin, pAEnd, strB, pBBegin+1, pBEnd);
			int t2 = calculateStringDistance(strA, pABegin+1, pAEnd, strB, pBBegin, pBEnd);
			int t3 = calculateStringDistance(strA, pABegin+1, pAEnd, strB, pBBegin+1, pBEnd);
			return MathPlus.min(t1, t2, t3) + 1;
		}
	}

}
