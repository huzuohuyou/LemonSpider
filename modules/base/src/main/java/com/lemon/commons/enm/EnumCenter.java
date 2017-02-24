package com.lemon.commons.enm;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * @author bob 北京易智享科技有限公司
 * @version v3 2015年5月14日 上午11:20:57
 *
 */
final class EnumCenter {

	private static Map<String, Map<Integer, IEnum>> map = new TreeMap<String, Map<Integer, IEnum>>();
	private static Map<String, Map<String, IEnum>> vmap = new TreeMap<String, Map<String, IEnum>>();

	private static int[] mask31 = new int[31];
	static {
		for(int i=0; i<31; i++) {
			mask31[i] = 1<<i;
		}
	}

	/**
	 * 注册Code
	 * 包访问权限，仅该包内的类或枚举可以使用
	 * @param type
	 * @param val
	 */
	static void registe(Class<?> type, IEnum val) {
		String key = type.getCanonicalName();
		Map<Integer, IEnum> m = map.get(key);
		if(m == null) {
			m = new TreeMap<Integer, IEnum>();
			map.put(key, m);
		}
		m.put(val.getCode(), val);
	}

	/**
	 * 注册values
	 * 包访问权限，仅该包内的类或枚举可以使用
	 * @param type
	 * @param val
	 */
	static void registeValues(Class<?> type, IEnum val) {
		String key = type.getCanonicalName();
		Map<String, IEnum> m = vmap.get(key);
		if(m == null) {
			m = new TreeMap<String, IEnum>();
			vmap.put(key, m);
		}
		m.put(val.getValue(), val);
	}

	static IEnum explain(Class<?> type, int code) {
		String key = type.getCanonicalName();
		Map<Integer, IEnum> m = map.get(key);
		if(m == null) {
			return null;
		}
		return m.get(code);
	}

	static String explainAsString(Class<?> type, int code) {
		IEnum ie = explain(type, code);
		if(ie != null) {
			return ie.getValue();
		}
		return null;
	}

	static List<IEnum> explainMask(Class<?> type, int maskCode) {
		String key = type.getCanonicalName();
		Map<Integer, IEnum> m = map.get(key);
		if(m == null) {
			return null;
		}
		List<Integer> list = splitMaskCode31(maskCode);
		List<IEnum> ret = new ArrayList<IEnum>();
		for(int e : list) {
			IEnum en = m.get(e);
			if(en != null) {
				ret.add(en);
			}
		}
		return ret;
	}

	static String explainMaskAsString(Class<?> type, int maskCode) {
		List<IEnum> list = EnumCenter.explainMask(type, maskCode);
		StringBuilder sb = new StringBuilder();
		for(IEnum en : list) {
			sb.append("|").append(en.getValue());
		}
		String ss = sb.toString();
		if(ss.length()>0) {
			return ss.substring(1);
		}
		return null;
	}


	/**
	 * 统一的value查询中心
	 * @param type
	 * @param value
	 * @return
	 */
	static IEnum explainValue(Class<?> type, String value) {
		String key = type.getCanonicalName();
		Map<String, IEnum> m = vmap.get(key);
		if(m == null) {
			return null;
		}
		return m.get(value);
	}

	private static List<Integer> splitMaskCode31(int maskCode) {
		List<Integer> list = new ArrayList<Integer>();
		for(int i=0; i<mask31.length; i++) {
			int c = mask31[i];
			if( (c&maskCode)==c ) {
				list.add(c);
			}
		}
		return list;
	}
}