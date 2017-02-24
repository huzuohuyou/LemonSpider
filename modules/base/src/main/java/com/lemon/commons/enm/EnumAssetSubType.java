package com.lemon.commons.enm;

import java.util.ArrayList;
import java.util.List;

public enum EnumAssetSubType implements IEnum{
    Type_Folder     (0, "folder"),//文件夹
	Type_Doc  		(1, "doc"), //文件
	Type_Docx  		(2, "docx"), //超级视频
	Type_Ppt		(3, "ppt"), 	//试题
	Type_Pptx		(4, "pptx"),
	Type_Xls		(5, "xls"), //
	Type_Xlsx		(6, "xlsx"),
	Type_Pdf		(7, "pdf"),
	Type_Txt		(8, "txt"),
	Type_Mp4		(9, "mp4"),
	Type_Jpg		(10, "jpg"),
	Type_Jpeg		(11, "jpeg"),
	Type_Bmp		(12, "bmp"),
	Type_Png		(13, "png"),
	Type_Other		(14, "other"),
	Type_Quest      (15,"q"),
	Type_Media		(16,"m"),
	Type_YangCong   (17,"yangcong"),
	Type_lexue      (18,"lexue");

	final static String[] videoList = {"mp4","mov","3gp","avi","flv","wmv","mov","rmvb","mp3"};

	public final static String[] docList = {"doc","docx","ppt","pptx","xls","xlsx","txt","pdf"};

	public final static String[] picList = {"jpg","jpeg","png","bmp","gif"};

	private int code;
	private String value;
	private EnumAssetSubType(int code,String value){
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
		for(EnumAssetSubType e : EnumAssetSubType.values()) {
			EnumCenter.registe(EnumAssetSubType.class, e);
			EnumCenter.registeValues(EnumAssetSubType.class, e);
		}
	}

	public static EnumAssetSubType explain(int code) {
		return (EnumAssetSubType)EnumCenter.explain(EnumAssetSubType.class, code);
	}

	public static EnumAssetSubType explainValue(String val) {
		IEnum ie = EnumCenter.explainValue(EnumAssetSubType.class, val);
		if(ie == null) {
			return EnumAssetSubType.Type_Other;
		} else {
			return (EnumAssetSubType)ie;
		}
	}

	public static boolean isVideo(String ext){
		if(ext==null || ext.trim().length() == 0)
			return false;

		for(String videoExt:videoList){
			if(videoExt.equalsIgnoreCase(ext)){
				return true;
			}
		}
		return false;
	}

}
