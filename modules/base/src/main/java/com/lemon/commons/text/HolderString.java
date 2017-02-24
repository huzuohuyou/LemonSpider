package com.lemon.commons.text;

/**
 * 类似jdbc或者c、objectivec里面的string的用法，可以实现字符串的参数化，用名字、或者数字作为占位符。如下面的例子
 * private static HolderString cmdFfmpeg = new HolderString("$0ffmpeg -i $1 -i $2 -acodec copy -vcodec copy $3");
 * String cmd = cmdFfmpeg.setHolder(0, PhotoStreamingVideo.VIDEO_ROOT).setHolder(1, videoPath).setHolder(2, audioPath).setHolder(3, mergedPath).toString();
 *
 * @author bob
 * @date 2015/07/29
 */

public class HolderString {
	private String strWithHolder;
	
	public HolderString(String strWithHolder) {
		this.strWithHolder = strWithHolder;
	}
	
	public HolderString setHolder(int index, String val) {
		if(index<0)
			return this;
		String p = "\\$" + index;
		if(val==null) {
			val = "";
		}
//		System.out.println("$:" + p);
//		System.out.println("cont:" + val);
		strWithHolder = strWithHolder.replaceFirst(p, val);
		return this;
	}
	
	public HolderString setHolder(String name, String val) {
		if(name==null || name.length()<1)
			return this;
		String p = "\\$" + name;
		if(val==null) {
			val = "";
		}
		strWithHolder = strWithHolder.replaceFirst(p, val);
		return this;
	}
	
	@Override
	public String toString() {
		return strWithHolder;
	}
}
