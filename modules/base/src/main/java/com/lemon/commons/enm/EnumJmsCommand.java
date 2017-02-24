package com.lemon.commons.enm;

import java.util.Map;
import java.util.TreeMap;


/**
 * @author bob 北京易智享科技有限公司
 * @version v3 2015年5月7日 下午9:18:18
 *
 */
public enum EnumJmsCommand implements IEnum {
	//------------------fields -------------------------
	AudioStreamingChecking	(0, "AudioStreamingChecking"),
	AudioStreamingProcess	(1, "AudioStreamingProcess"),
	OfficeDocumentChecking	(2, "OfficeDocumentChecking"),
	OfficeDocumentProcess	(3, "OfficeDocumentProcess"),
	Mp4VideoGeneration		(4, "Mp4VideoGeneration");


	//------------------instance methods -------------------------
	private int code;
	private String value;
	private EnumJmsCommand(int code, String value) {
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

	public Map<String, Object> newEmptyCommandMap() {
		Map<String, Object> map = new TreeMap<String, Object>();
		map.put("cmd", code);
		return map;
	}

	//------------------static methods -------------------------
	static {
		for(EnumServer e : EnumServer.values()) {
			EnumCenter.registe(EnumServer.class, e);
		}
	}

	public static String explainAsString(int code) {
		return EnumCenter.explainAsString(EnumServer.class, code);
	}
}