package com.lemon.commons.enm;



/**
 * @author bob 北京易智享科技有限公司
 * @version v3 2015年5月7日 下午9:18:18
 *
 */
public enum EnumVideoMp4 implements IEnum{
	//------------------fields -------------------------
	x1  (1, "x1", 1024, 680),
	x2	(2, "x2", 2048, 1360),
	Xx4	(4, "x4", 4096, 2720),

	h2	(12, "h2", 512, 340), //12 means 1/2
	h4	(14, "h4", 256, 170), //14 means 1/4

	i5 (25, "i5", 964, 640); //iPhone5(s) 640x1136 scale to 640x964
	//------------------instance methods -------------------------
	private int ch;
	private String value;
	private int width;
	private int height;
	private EnumVideoMp4(int ch, String value, int width, int heigth) {
		this.ch = ch;
		this.value = value;
		this.width = width;
		this.height = heigth;
	}

	@Override
	public String getValue() {
		return value;
	}

	@Override
	public int getCode() {
		return ch;
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

	//------------------static methods -------------------------
	static {
		for(EnumVideoMp4 e : EnumVideoMp4.values()) {
			EnumCenter.registe(EnumVideoMp4.class, e);
		}
	}

	public static String explainAsString(int code) {
		return EnumCenter.explainAsString(EnumVideoMp4.class, code);
	};

	public static EnumVideoMp4 explain(int code) {
		IEnum ie = EnumCenter.explain(EnumVideoMp4.class, code);
		if(ie == null) {
			return null;
		} else {
			return (EnumVideoMp4)ie;
		}
	}
}