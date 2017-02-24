package com.lemon.commons.enm;


import java.util.ArrayList;
import java.util.List;

/**
 * @author bob 北京易智享科技有限公司
 * @version v3 2015年5月7日 下午9:18:18
 */
public enum EnumAsset implements IEnumChinese {
	//------------------fields -------------------------
	Type_File  		(1, "file", "文档图片"), //文件
	Type_Media  	(2, "media", "超级视频"), //超级视频
	Type_Question	(3, "ques",  "试题习题"), 	//试题
	Type_Questions	(12, "quests",  "课后练习"), 	//试题
	Type_Photo		(4, "photo", "图片"),

	Type_Group		(5, "group",  "资源集"), //

	Type_FileStack	(6, "folder",  "文件夹"),
	Type_Serial		(7, "serial",  "视频专辑"),
	Type_Exam		(8, "exam", "试卷"),
	Type_Album		(9, "ablum", "相册"),

	Type_Ump4		(10, "ump4", "传统视频"),

	Type_Automp4	(11, "mmp4", "导出标清视频"),
	Type_ApplyExam  (13,"aexam","报名"),
	Type_Url        (14,"url","链接资源")
	; //视频转换MP4

	//------------------instance methods -------------------------
	public final static EnumAsset[] SupportAsset = {Type_Media, Type_File, Type_Ump4};

	private int ch;
	private String value;
	private String chineseName;
	private EnumAsset(int ch, String value, String chineseName) {
		this.ch = ch;
		this.value = value;
		this.chineseName = chineseName;
	}


	@Override
	public String getValue() {
		return value;
	}

	@Override
	public int getCode() {
		return ch;
	}

	@Override
	public String getChineseName() {
		return chineseName;
	}

	public String getRootPath() {
		switch(ch) {
		case 1:
		{
			return EnumProp.Prop_Root_File.getValue();
		}
		case 2:{
			return EnumProp.Prop_Root_Media.getValue();
		}
		case 3:{
			return EnumProp.PROP_Root_Question.getValue();
		}
		case 10:{
			return EnumProp.Prop_Root_Ump4.getValue();
		}
		}
		return "";
	}

	public String getTrashPath() {
		switch(ch) {
		case 1:
		{
			return EnumProp.Prop_Trash_File.getValue();
		}
		case 2:{
			return EnumProp.Prop_Trash_Media.getValue();
		}
		case 3:{
			return EnumProp.PROP_Trash_Question.getValue();
		}
		case 10:{
			return EnumProp.Prop_Trash_Ump4.getValue();
		}
		}
		return "";
	}

	//------------------static methods -------------------------
	static {
		for(EnumAsset e : EnumAsset.values()) {
			EnumCenter.registe(EnumAsset.class, e);
			EnumCenter.registeValues(EnumAsset.class, e);
		}
	}

	public static EnumAsset explain(int code) {
		IEnum ie = EnumCenter.explain(EnumAsset.class, code);
		if(ie == null) {
			return null;
		} else {
			return (EnumAsset)ie;
		}
	}

	public static String getFolderName(int code){
		String folderName = "其他资源";

		if(code == EnumAsset.Type_Media.getCode()){
			folderName = "我的超级视频";
		}
		else if(code == EnumAsset.Type_Questions.getCode()){
			folderName = "我的题库";
		}
		else if(code == EnumAsset.Type_Ump4.getCode()){
			folderName = "我的视频";
		}
		else if(code == EnumAsset.Type_File.getCode()){
			folderName = "我的文档";
		}

		return folderName;
	}

	public static String explainAsString(int code) {
		return EnumCenter.explainAsString(EnumAsset.class, code);
	}

	/**
	 * 请注意对应的在上面static里面注册registeValues
	 * @param val
	 * @return
	 */
	public static EnumAsset explainValue(String val) {
		IEnum ie = EnumCenter.explainValue(EnumAsset.class, val);
		if(ie == null) {
			return null;
		} else {
			return (EnumAsset)ie;
		}
	}

    public static final List<Integer> COMMON_AsType = new ArrayList<Integer>();

    static{
        COMMON_AsType.add(EnumAsset.Type_File.getCode());
        COMMON_AsType.add(EnumAsset.Type_Media.getCode());
        COMMON_AsType.add(EnumAsset.Type_Ump4.getCode());

    }

}