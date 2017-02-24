package com.lemon.commons.enm;

/**
 * @author bob 北京易智享科技有限公司
 * @version v3 2015年5月7日 下午9:18:18
 *
 */
public enum EnumProcessStatus implements IEnumMask {
	//------------------fields -------------------------
	Type_Raw  				(0, "等待处理"),
	Type_StreamingDone  	(1, "播放就绪"),
	Type_Mp4SDReady			(1<<1, "MP4成功"),
	//	Type_Mp4HDReady			(1<<2, "MP4(HD)已创建"),
	//	ST_Mp4SDDownloaded		(1<<3, ""),
	//	ST_Mp4HDDownloaded		(1<<4, "");
    Type_Error			     (999, "处理失败");
	//------------------ instance methods -------------------------
	private int code;
	private String value;

	EnumProcessStatus(int code, String value) {
		this.code = code;
		this.value = value;
	}

	@Override
	public int getCode() {
		return code;
	}

	@Override
	public String getValue() {
		return value;
	}

	@Override
	public boolean isIncludeBit(int bitMask) {
		return (code & bitMask) == code;
	}

	@Override
	public int addMaskBit(int bitMask) {
		return bitMask |= code;
	}

	@Override
	public int clearMaskBit(int bitMask) {
		int m = ~code; //code取反
		return bitMask & m;
	}


	//------------------static methods -------------------------
	static {
		for(EnumProcessStatus e : EnumProcessStatus.values()) {
			EnumCenter.registe(EnumProcessStatus.class, e);
		}
	}

	public static String explainAsString(int code) {
		return EnumCenter.explainAsString(EnumProcessStatus.class, code);
	}

	public static EnumProcessStatus explain(int code) {
		IEnum ie = EnumCenter.explain(EnumProcessStatus.class, code);
		if(ie == null) {
			return null;
		} else {
			return (EnumProcessStatus)ie;
		}
	}
}