package com.lemon.commons.enm;

/**
 * @author bob 北京易智享科技有限公司
 * @version v3 2015年5月7日 下午9:18:18
 */
public enum EnumOS implements IEnum {
	//------------------fields -------------------------
	OS_Linux  			(0, "linux"), 		 	//超级视频
	OS_MacOSX  			(1, "mac os x"), 		//已实测
	OS_Windows			(2, "windows"), 		//试题
	; //视频转换MP4

	//------------------instance methods -------------------------
	private int platform;
	private String value;
	private EnumOS(int platform, String value) {
		this.platform = platform;
		this.value = value;
	}
	
	@Override
	public String getValue() {
		return value;
	}

	@Override
	public int getCode() {
		return platform;
	}


	//------------------static methods -------------------------
	static {
		for(EnumOS e : EnumOS.values()) {
			EnumCenter.registe(EnumOS.class, e);
            EnumCenter.registeValues(EnumOS.class, e);
		}
	}

	public static EnumOS explain(int code) {
		IEnum ie = EnumCenter.explain(EnumOS.class, code);
		if(ie == null) {
			return null;
		} else {
			return (EnumOS)ie;
		}
	}

	public static String explainAsString(int code) {
		return EnumCenter.explainAsString(EnumOS.class, code);
	}

	/**
	 * 请注意对应的在上面static里面注册registeValues
	 * @param val
	 * @return
	 */
	public static EnumOS explainValue(String val) {
		IEnum ie = EnumCenter.explainValue(EnumOS.class, val);
		if(ie == null) {
			return null;
		} else {
			return (EnumOS)ie;
		}
	}
	
	
	//------------------extended static methods -------------------------
	public final static EnumOS[] OSList = {OS_Linux, OS_MacOSX, OS_Windows};
	
	private final static String[] OS_ROOTS = {"/home/lemon/data/", "/Users/ywp/lemon/data/", "e:/data/"};
	public final static String currentOsRoot() {
		return OS_ROOTS[currentOs.getCode()];
	}
	
	private final static String[] FFmpeg_ROOTS = {"/root/bin/", "/usr/local/bin/", null};
	public final static String currentFFmpegRoot() {
		return FFmpeg_ROOTS[currentOs.getCode()];
	}
	
	private final static String[] FFmpeg_WORK_ROOTS = {"/home/lemon/data/ffmpeg/", "/Users/bob/lemon/data/ffmpeg/", null};
	public final static String currentFFmpegWorkRoot() {
		return FFmpeg_WORK_ROOTS[currentOs.getCode()];
	}
	
	private final static String[] FFmpeg_WORK_TMP_ROOTS = {"/home/lemon/data/ffmpeg/tmp/", "/Users/bob/lemon/data/ffmpeg/tmp/", null};
	public final static String currentFFmpegWorkTmpRoot() {
		return FFmpeg_WORK_TMP_ROOTS[currentOs.getCode()];
	}
	
	private final static EnumOS currentOs;
	static {
		String OS = System.getProperty("os.name").toLowerCase();
		if (OS.indexOf(EnumOS.OS_Linux.getValue()) >= 0) {
			currentOs = EnumOS.OS_Linux;
		} else if (OS.indexOf(EnumOS.OS_MacOSX.getValue()) >= 0) {
			currentOs = EnumOS.OS_MacOSX;
		} else if (OS.indexOf(EnumOS.OS_Windows.getValue()) >= 0) {
			currentOs = EnumOS.OS_Windows;
		} else { //others as linux
			currentOs = EnumOS.OS_Linux;
		}
	}

}