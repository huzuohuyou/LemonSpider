package com.lemon.commons.util.net;

import javax.servlet.http.HttpServletRequest;

public final class IpUtil {
	/**
	 * 将字符串型ip转成int型ip
	 *
	 * @param strIp
	 * @return
	 */
	public static int Ip2Int(String strIp) {
		String[] ss = strIp.split("\\.");
		if (ss.length != 4) {
			return 0;
		}
		byte[] bytes = new byte[ss.length];
		for (int i = 0; i < bytes.length; i++) {
			bytes[i] = (byte) Integer.parseInt(ss[i]);
		}
		return byte2Int(bytes);
	}

	/**
	 * 将int型ip转成String型ip
	 *
	 * @param intIp
	 * @return
	 */
	public static String int2Ip(int intIp) {
		byte[] bytes = int2byte(intIp);
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < 4; i++) {
			sb.append(bytes[i] & 0xFF);
			if (i < 3) {
				sb.append(".");
			}
		}
		return sb.toString();
	}

	private static byte[] int2byte(int i) {
		byte[] bytes = new byte[4];
		bytes[0] = (byte) (0xff & i);
		bytes[1] = (byte) ((0xff00 & i) >> 8);
		bytes[2] = (byte) ((0xff0000 & i) >> 16);
		bytes[3] = (byte) ((0xff000000 & i) >> 24);
		return bytes;
	}

	private static int byte2Int(byte[] bytes) {
		int n = bytes[0] & 0xFF;
		n |= ((bytes[1] << 8) & 0xFF00);
		n |= ((bytes[2] << 16) & 0xFF0000);
		n |= ((bytes[3] << 24) & 0xFF000000);
		return n;
	}

	public static void main(String[] args) {
		String ip1 = "192.168.0.1";
		int intIp = Ip2Int(ip1);
		String ip2 = int2Ip(intIp);
		System.out.println(ip2.equals(ip1));
	}

	public static String getIp(HttpServletRequest request) {
		return getIpAddr(request);
	}

	public static String getIpAddr(HttpServletRequest request) {
		String ip = request.getHeader("x-forwarded-for");
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("Proxy-Client-IP");
		} if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("WL-Proxy-Client-IP");
		} if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getRemoteAddr();
		}
		if(ip.equals("0:0:0:0:0:0:0:1")){
			ip = "127.0.0.1";
		}
		return ip;
	}
}