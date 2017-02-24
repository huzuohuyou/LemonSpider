package com.lemon.commons.enm;

import com.lemon.commons.Cons;

/**
 * @author bob 北京易智享科技有限公司
 * @version v3 2015年5月7日 下午9:18:18
 *
 */
public enum EnumRex{
	//------------------fields -------------------------


	Rex_User_tel  		(Cons.Rex.User_tel,"手机格式不正确"),
	Rex_User_username  	(Cons.Rex.User_username,""),
	Rex_User_fullName	(Cons.Rex.User_fullName,""),
	Rex_User_Email  	(Cons.Rex.User_Email,"邮箱格式不正确"),
	Rex_User_nickName 	(Cons.Rex.User_nickName,""),
	Rex_User_pwd		(Cons.Rex.User_pwd,"密码只能使用字母、数字和!@#$%^&*()_+."),
	Rex_User_smsAuthc	(Cons.Rex.User_smsAuthc,""),
	Rex_User_role		(Cons.Rex.User_role,""),
	Rex_User_gender		(Cons.Rex.User_gender,""),
	Rex_User_qq			(Cons.Rex.User_qq,""),
	Rex_Subject_wenkuIds(Cons.Rex.Subject_wenkuIds,""),
	Rex_Segment			(Cons.Rex.Segment,""),
	Rex_Classmgr_openDate(Cons.Rex.Classmgr_openDate,""),
	Rex_Base_yesOrNo	(Cons.Rex.Base_yesOrNo,"");


	//------------------instance methods -------------------------
	private String rex;
	private String msg;
	private EnumRex(String rex, String msg) {
		this.rex = rex;
		this.msg = msg;
	}

	public String getRex() {
		return rex;
	}


	public String getMsg() {
		return msg;
	}

	//------------------static methods -------------------------
}
