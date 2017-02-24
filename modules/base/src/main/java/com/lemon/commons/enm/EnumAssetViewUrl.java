package com.lemon.commons.enm;

public enum EnumAssetViewUrl implements IEnum{
	View_Word	(1,"/wv/wordviewerframe.aspx"),
	View_Ppt	(2,"/p/PowerPointFrame.aspx?PowerPointView=ReadingView"),
	View_Xlsx	(3,"/x/_layouts/xlviewerinternal.aspx"),
	View_Pdf	(4,"/wv/wordviewerframe.aspx?PdfMode=1"),

	MobileView_Word (5,"/wv/mWord.aspx"),
	MobileView_Ppt (6,"/p/mPPT.aspx"),
	MobileView_Xlsx (7,"/x/_layouts/mobile/mXL.aspx"),
	MobileView_Pdf (8,"/wv/wordviewerframe.aspx?embed=1&PdfMode=1");


	private int code;
	private String value;
	private EnumAssetViewUrl(int code,String value){
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
		for(EnumAssetViewUrl e : EnumAssetViewUrl.values()) {
			EnumCenter.registe(EnumAssetViewUrl.class, e);
		}
	}

	public static EnumAssetViewUrl explain(int code) {
		return (EnumAssetViewUrl)EnumCenter.explain(EnumAssetViewUrl.class, code);
	}
}
