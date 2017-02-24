package com.lemon.extension.springside;

import com.google.common.collect.Maps;
import com.lemon.commons.enm.EnumSearchOperator;
import org.apache.commons.lang3.StringUtils;

import java.util.Map;
import java.util.Map.Entry;

/** 
 * @author bob 北京易智享科技有限公司
 * @version v3 2015年5月18日 下午9:02:10
 * 
 */
public class LemonSearchFilter {

	public String fieldName;
	public Object value;
	public EnumSearchOperator operator;

	public LemonSearchFilter(String fieldName, EnumSearchOperator operator, Object value) {
		this.fieldName = fieldName;
		this.value = value;
		this.operator = operator;
	}

	/**
	 * searchParams中key的格式为OPERATOR_FIELDNAME
	 */
	public static Map<String, LemonSearchFilter> parse(Map<String, Object> searchParams) {
		Map<String, LemonSearchFilter> filters = Maps.newHashMap();

		for (Entry<String, Object> entry : searchParams.entrySet()) {
			// 过滤掉空值
			String key = entry.getKey();
			Object value = entry.getValue();
			if ((value instanceof String) && (StringUtils.isBlank((String) value))) {
				continue;
			}

			// 拆分operator与filedAttribute
			String[] names = StringUtils.split(key, "_");
			if (names.length != 2) {
				throw new IllegalArgumentException(key + " is not a valid search filter name");
			}
			String filedName = names[1];
			EnumSearchOperator operator = EnumSearchOperator.valueOf(names[0]);

			// 创建searchFilter
			if(value==null || "".equals(value)) {
				continue;
			}
			Object v = value;
			switch (operator) {
				case EQ4Char:
					v = ((String)value).charAt(0);
					break;
				case NE4Int:;
				case EQ4Int:
					v = Integer.parseInt((String)value);
					break;
				default:
					break;
			}
			LemonSearchFilter filter = new LemonSearchFilter(filedName, operator, v);
			filters.put(key, filter);
		}

		return filters;
	}
	
	
	public static Map<String, Object> filterEmptyString(Map<String, Object> map) {
		if(map==null || map.size()<1) {
			return map;
		}
		for(String key : map.keySet()) {
			Object val = map.get(key);
			if(val instanceof String) {
				String sval = (String)val;
				sval = sval.trim();
				map.put(key, sval);
			}
		}
		return map;
	}
}
