package com.lemon.commons.enm;

/**
 * Created by ywp on 15/10/5.
 */
public enum  EnumQuestActivityType implements IEnum {
	Chinese     (1, "语文题库",	"com.lemon.ds.e.service.QuestYunWenService"),
	Math		(2, "数学题库", 	"com.lemon.ds.e.service.QuestMathService"),
	EnglishWeek (3, "英语题库","com.lemon.ds.e.service.QuestEnglishWeekService"),
	Physis		(4, "物理题库", 	"com.lemon.ds.e.service.QuestPhysisService"),
	Chemical	(5, "化学题库", "com.lemon.ds.e.service.QuestChemicalService"),
	Biology		(6, "生物题库",	"com.lemon.ds.e.service.QuestBiologyService"),
	Politics	(7, "政治题库",	"com.lemon.ds.e.service.QuestPoliticsService"),
	History		(8, "历史题库",	"com.lemon.ds.e.service.QuestHistoryService"),
	Geography	(9, "地理题库","com.lemon.ds.e.service.QuestGeographyService")
	;

	private int ch;
	private String value;
	private String className;
	private EnumQuestActivityType(int ch, String value,String className) {
		this.ch = ch;
		this.value = value;
		this.className = className;
	}


	@Override
	public String getValue() {
		return value;
	}

	@Override
	public int getCode() {
		return ch;
	}

	public String getClassName() {
		return className;
	}

	//------------------static methods -------------------------
	static {
		for(EnumQuestActivityType e : EnumQuestActivityType.values()) {
			EnumCenter.registe(EnumQuestActivityType.class, e);
		}
	}

	public static EnumQuestActivityType explain(int code) {
		IEnum ie = EnumCenter.explain(EnumQuestActivityType.class, code);
		if(ie == null) {
			return null;
		} else {
			return (EnumQuestActivityType)ie;
		}
	}

	public static String explainAsString(int code) {
		return EnumCenter.explainAsString(EnumQuestActivityType.class, code);
	}
}