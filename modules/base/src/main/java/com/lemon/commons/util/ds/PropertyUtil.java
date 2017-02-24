package com.lemon.commons.util.ds;

import com.lemon.commons.enm.EnumProp;

public class PropertyUtil {
	private static PropertyUtil instance = new PropertyUtil();
	public static PropertyUtil sharedInstance() {
		if (instance == null) {
			instance = new PropertyUtil();
		}
		return instance;
	}

	private PropertyUtil() {
	}

	// TODO
	public static String getPropertyValue(EnumProp p) {

		return null;
	}
}
