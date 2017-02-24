package com.lemon.commons;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CommonRegex {

	private final static String REG_EMAIL = "^([a-z0-9A-Z\\-_]+[-|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";
	private final static Pattern PAT_EMAIL = Pattern.compile(REG_EMAIL);

	public static boolean isValidEmail(String email) {
		Matcher m = PAT_EMAIL.matcher(email);
		return m.find();
	}
}
