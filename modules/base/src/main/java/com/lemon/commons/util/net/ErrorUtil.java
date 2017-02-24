package com.lemon.commons.util.net;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.validation.Errors;
import org.springframework.validation.ObjectError;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.lemon.commons.Cons;
import com.lemon.commons.log.Log;

public class ErrorUtil {
	private final static Log log = Log.getLogger(ErrorUtil.class);

	public static final List<String> getErrorsValue(Errors errors) {
		List<String> list = new ArrayList<String>();
		for (ObjectError oe : errors.getAllErrors()) {
			log.warn(oe.getDefaultMessage());
			list.add(oe.getDefaultMessage());
		}
		return list;
	}

	/**
	 * 返回错误页面
	 * @author swe
	 * @param errMsg
	 * @param re
	 * @return
	 */
	public static String redirect2ErrorPage(String errMsg,RedirectAttributes re){
		if(errMsg!=null)
			re.addAttribute(Cons.Error.ErrMsg, errMsg);
		return "redirect:/error";
	}

	public static boolean LogErrorForModel(Errors errors) {
		if (errors != null) {
			for (ObjectError error : errors.getAllErrors()) {
				log.warn("[err]"+error.getDefaultMessage());
				log.warn("[err]"+error.toString());
			}
			return true;
		} else {
			return false;
		}

	}

	public static String addAllFlashAttribute(Map<String,Object> errMap,RedirectAttributes redirectAttributes){
		if(errMap.size()>0){
			for(Map.Entry<String, Object> entry : errMap.entrySet()){
				redirectAttributes.addFlashAttribute(entry.getKey(), entry.getValue());
			}
			return Cons.Error.ErrDefaultPage;
		}
		return null;
	}

}
