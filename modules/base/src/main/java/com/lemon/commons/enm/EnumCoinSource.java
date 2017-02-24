package com.lemon.commons.enm;

/**
 * Created by ywp on 15/8/31.
 */
public enum EnumCoinSource implements IEnum {
	Login		(1,"Login"),
	Activity	(2,"Activity"),
	Book		(3,"Booking"),


	Media2Mp4DownloadSD	(4, "下载转换好的标清mp4"),
	Media2Mp4DownloadHD	(5, "下载转换好的标清mp4"),
	CoinRule            (6,"系统规则"),
	FeatureVideo        (7,"精品视频补贴"),
	SellVideo           (8,"出售视频"),
	SellClazz           (9,"班级收费"),
	Admin				(10,"管理员操作"),
	Other		        (9999,"其它");

	private int code;
	private String value;
	private EnumCoinSource(int code, String value) {
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

	//------------------static methods -------------------------
	static {
		for(EnumCoinSource e : EnumCoinSource.values()) {
			EnumCenter.registe(EnumCoinSource.class, e);
		}
	}

	public static String explainAsString(int code) {
		return EnumCenter.explainAsString(EnumCoinSource.class, code);
	}
}