package com.lemon.commons.text;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

public class ChineseUrlParam {

	public static String decodeChinese(String chinese) {
		String decoded = null;
		if(chinese != null) {
			try {
				decoded = URLDecoder.decode(chinese, "utf-8");
			} catch (UnsupportedEncodingException e) {
				decoded = chinese;
			}
		} else {
			decoded = chinese;
		}
		return decoded;
	}
}
