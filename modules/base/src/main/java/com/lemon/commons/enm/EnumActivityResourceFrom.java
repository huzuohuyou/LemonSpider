package com.lemon.commons.enm;

/**
 * Created by yp on 2015/8/20.
 */
public enum EnumActivityResourceFrom implements IEnum {
	Question(1, "com.lemon.ds.a.service.resourceService.QuestionResourceService"),
	Asset(2, "com.lemon.ds.a.service.ActivityAssetService"),
	QuestLab(3,"com.lemon.ds.a.service.resourceService.QuestLabResuourceService");

	private EnumActivityResourceFrom(int code, String value) {
		this.code = code;
		this.description = value;
	}

	private int code;
	private String description;

	@Override
	public int getCode() {
		return code;
	}

	@Override
	public String getValue() {
		return description;
	}

	// ------------------static methods -------------------------
	static {
		for (EnumActivityResourceFrom e : EnumActivityResourceFrom.values()) {
			EnumCenter.registe(EnumActivityResourceFrom.class, e);
		}
	}

	public static EnumActivityResourceFrom explain(int code) {
		IEnum ie = EnumCenter.explain(EnumActivityResourceFrom.class, code);
		if(ie == null) {
			return null;
		} else {
			return (EnumActivityResourceFrom)ie;
		}
	}
}