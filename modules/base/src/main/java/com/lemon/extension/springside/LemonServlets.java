package com.lemon.extension.springside;

import java.util.Enumeration;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;

import javax.servlet.ServletRequest;

import org.apache.commons.lang3.Validate;
import org.springframework.ui.Model;
import org.springside.modules.utils.Collections3;

/**
 * @author bob 北京易智享科技有限公司
 * @version v3 2015年5月18日 下午8:59:34
 *
 */
public class LemonServlets {


	/**
	 * 取得带相同前缀的Request Parameters, copy from spring WebUtils.
	 *
	 * 返回的结果的Parameter名已去除前缀.
	 */
	public static Map<String, Object> getParametersStartingWith(ServletRequest request, String prefix) {
		Validate.notNull(request, "Request must not be null");
		Enumeration<String> paramNames = request.getParameterNames();
		Map<String, Object> params = new TreeMap<String, Object>();
		if (prefix == null) {
			prefix = "";
		}
		while ((paramNames != null) && paramNames.hasMoreElements()) {
			String paramName = paramNames.nextElement();
			if ("".equals(prefix) || paramName.startsWith(prefix)) {
				String unprefixed = paramName.substring(prefix.length());

				String[] values = request.getParameterValues(paramName);
				if ((values == null) || (values.length == 0)) {
					// Do nothing, no values found at all.
				} else if (values.length > 1) {
					params.put(unprefixed, values);
				} else {
					String v0 = values[0];
					if(v0!=null) {
                        v0 = v0.trim();
                        if(v0.length() > 0) {
                            params.put(unprefixed, v0);
                        }
					}
				}
			}
		}
		params = LemonSearchFilter.filterEmptyString(params);
		return params;
	}


	/**
	 * 组合Parameters生成Query String的Parameter部分, 并在paramter name上加上prefix.
	 *
	 * @see #getParametersStartingWith
	 */
	public static String encodeParameterStringWithPrefix(Map<String, Object> params, String prefix, Model model) {
		if (Collections3.isEmpty(params)) {
			return "";
		}

		if (prefix == null) {
			prefix = "";
		}

		StringBuilder queryStringBuilder = new StringBuilder();
		Iterator<Entry<String, Object>> it = params.entrySet().iterator();
		while (it.hasNext()) {
			Entry<String, Object> entry = it.next();

			String key = entry.getKey();
			if(model!=null && key.contains(".")) {
				String key2 = prefix + key.replaceAll("\\.", "DOT");
				model.addAttribute(key2, entry.getValue());
			}

			queryStringBuilder.append(prefix).append(key).append('=').append(entry.getValue());
			if (it.hasNext()) {
				queryStringBuilder.append('&');
			}
		}
		return queryStringBuilder.toString();
	}
}
