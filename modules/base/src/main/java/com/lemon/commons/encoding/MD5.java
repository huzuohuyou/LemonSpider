package com.lemon.commons.encoding;

/**
 * Created by ywp on 15/8/27.
 */
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import com.lemon.commons.log.Log;
/*
 * MD5 算法
 */
public class MD5 {
	private final static Log log = Log.getLogger(MD5.class);

	// 全局数组
	private final static String[] strDigits = { "0", "1", "2", "3", "4", "5",
			"6", "7", "8", "9", "a", "b", "c", "d", "e", "f" };

	// 返回形式为数字跟字符串
	private static String byteToArrayString(byte bByte) {
		int iRet = bByte;
		// System.out.println("iRet="+iRet);
		if (iRet < 0) {
			iRet += 256;
		}
		int iD1 = iRet / 16;
		int iD2 = iRet % 16;
		return strDigits[iD1] + strDigits[iD2];
	}

	// 转换字节数组为16进制字串
	private static String byteToString(byte[] bByte) {
		StringBuffer sBuffer = new StringBuffer();
		for (int i = 0; i < bByte.length; i++) {
			sBuffer.append(byteToArrayString(bByte[i]));
		}
		return sBuffer.toString();
	}

	public static String GetMD5Code(String strObj) {
		String resultString = null;
		try {
			resultString = new String(strObj);
			MessageDigest md = MessageDigest.getInstance("MD5");
			// md.digest() 该函数返回值为存放哈希值结果的byte数组
			resultString = byteToString(md.digest(strObj.getBytes()));
		} catch (NoSuchAlgorithmException e) {
			log.error(e);
		}
		return resultString;
	}
	public static String GetSha1Code(String strObj) {
		String resultString = null;
		try {
			resultString = new String(strObj);
			MessageDigest md = MessageDigest.getInstance("SHA-1");
			// md.digest() 该函数返回值为存放哈希值结果的byte数组
			resultString = byteToString(md.digest(strObj.getBytes()));
		} catch (NoSuchAlgorithmException e) {
			log.error(e);
		}
		return resultString;
	}

	public static void main(String[] args) {
		String str = "wDoctorXSsdsdSUDSFSDFASds#$$3235456222sds1452650959508tlipingliping13717842533";
		System.out.println(MD5.GetMD5Code(str));
	}
}