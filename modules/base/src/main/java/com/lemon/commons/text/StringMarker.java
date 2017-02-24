package com.lemon.commons.text;

import java.util.regex.Pattern;

import org.slf4j.helpers.FormattingTuple;
import org.slf4j.helpers.MessageFormatter;

/**
 * 类似c、objectivec里面的string的用法，可以实现字符串的参数化，用名字、或者数字作为占位符。如下面的例子
 * @author bob
 * @date 2015/07/29
 */

public class StringMarker {
	private String text;

	@SuppressWarnings("unused")
	private final static Pattern pat = Pattern.compile("\\{\\}");
	static final String DELIM_STR = "{}";
	/**
	 *
	 * @param format such as: "aa{}bb{}cc{}"
	 * @param param  such as:  123, "abc", new Date(). 遇到Exception类自动停止。
	 */
	public StringMarker(String format, Object... param) {
		if(format == null) {
			text = "";
		}
		if(param==null && format.indexOf("{}")>0) {
			text = format;
		}
		FormattingTuple tp = MessageFormatter.arrayFormat(format, param);
		//        log(level, tp.getMessage(), tp.getThrowable());
		text = tp.getMessage();

		//		StringBuilder sb = new StringBuilder();
		//		Matcher m = pat.matcher(format);
		//		int base = 0;
		//		for(int i=0; m.find(); i++) {
		//			sb.append(format.substring(base, m.start()));
		//			String s = ""+param[i];
		//			sb.append(s);
		//		}
		//		if(base < format.length()) {
		//			sb.append(format.substring(base));
		//		}
		//		text = sb.toString();
	}

	/**
	 * Same principle as the {@link #format(String, Object)} and
	 * {@link #format(String, Object, Object)} methods except that any number of
	 * arguments can be passed in an array.
	 *
	 * @param messagePattern
	 *          The message pattern which will be parsed and formatted
	 * @param argArray
	 *          An array of arguments to be substituted in place of formatting
	 *          anchors
	 * @return The formatted message
	 */
	//	public String parse(String messagePattern, Object[] argArray) {
	//		if(messagePattern == null) {
	//			text = "";
	//		}
	//		if(argArray==null) {
	//			text = messagePattern;
	//		}
	//
	//		int i = 0;
	//		int j;
	//		// use string builder for better multicore performance
	//		StringBuilder sbuf = new StringBuilder(messagePattern.length() + 50);
	//
	//		int L;
	//		for (L = 0; L < argArray.length; L++) {
	//			j = messagePattern.indexOf(DELIM_STR, i);
	//			if (j == -1) {
	//				// no more variables
	//				if (i == 0) { // this is a simple string
	//					return messagePattern;
	//				} else { // add the tail string which contains no variables and return
	//					// the result.
	//					sbuf.append(messagePattern.substring(i, messagePattern.length()));
	//					return new FormattingTuple(sbuf.toString(), argArray,
	//							throwableCandidate);
	//				}
	//			} else {
	//				if (isEscapedDelimeter(messagePattern, j)) {
	//					if (!isDoubleEscaped(messagePattern, j)) {
	//						L--; // DELIM_START was escaped, thus should not be incremented
	//						sbuf.append(messagePattern.substring(i, j - 1));
	//						sbuf.append(DELIM_START);
	//						i = j + 1;
	//					} else {
	//						// The escape character preceding the delimiter start is
	//						// itself escaped: "abc x:\\{}"
	//						// we have to consume one backward slash
	//						sbuf.append(messagePattern.substring(i, j - 1));
	//						deeplyAppendParameter(sbuf, argArray[L], new HashMap<Object[], Object>());
	//						i = j + 2;
	//					}
	//				} else {
	//					// normal case
	//					sbuf.append(messagePattern.substring(i, j));
	//					deeplyAppendParameter(sbuf, argArray[L], new HashMap<Object[], Object>());
	//					i = j + 2;
	//				}
	//			}
	//		}
	//		// append the characters following the last {} pair.
	//		sbuf.append(messagePattern.substring(i, messagePattern.length()));
	//		if (L < argArray.length - 1) {
	//			return new FormattingTuple(sbuf.toString(), argArray, throwableCandidate);
	//		} else {
	//			return new FormattingTuple(sbuf.toString(), argArray, null);
	//		}
	//	}

	@Override
	public String toString() {
		return text;
	}
}
