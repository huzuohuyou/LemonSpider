package com.lemon.commons.encoding;

import com.lemon.commons.log.Log;

public class SHA {
	private final static Log log = Log.getLogger(SHA.class);

	public static void main(String[] args) {
		String ok = "hvfkN/qlp/zhXR3cuerq6jd2Z7g=";
		String s = SHA.getMD5("a".getBytes());
		if (s.equals(ok)) {
			System.out.println("---------ok-----------");
		}
		System.out.println(s);
	}

	public static String getMD5(byte[] source) {
		String s = null;
		try {
			java.security.MessageDigest md = java.security.MessageDigest.getInstance("SHA-1");
			md.update(source);
			s = Base64.encode(md.digest());// MD5 的计算结果是一个 128 位的长整数，
		} catch (Exception e) {
			log.error("Get MD5", e);
		}
		return s;
	}

	public static String MD5(String source){

		String s = null;
		try {
			java.security.MessageDigest md = java.security.MessageDigest.getInstance("MD5");
			md.update(source.getBytes());
			s = Base64.encode(md.digest());// MD5 的计算结果是一个 128 位的长整数，
		} catch (Exception e) {
			log.error("MD5", e);
		}
		return s;
	}

	public static String SHA256(byte[] source){

		String s = null;
		try {
			java.security.MessageDigest md = java.security.MessageDigest.getInstance("SHA-256");
			md.update(source);
			s = Base64.encode(md.digest());
		} catch (Exception e) {
			log.error("SHA-256", e);
		}
		return s;
	}
	public static String SHA256(String source){

		String s = null;
		try {
			java.security.MessageDigest md = java.security.MessageDigest.getInstance("SHA-256");
			md.update(source.getBytes());
			s = Base64.encode(md.digest());
		} catch (Exception e) {
			log.error("SHA-256", e);
		}
		return s;
	}



}
