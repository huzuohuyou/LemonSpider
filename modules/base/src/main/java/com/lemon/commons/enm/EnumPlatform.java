package com.lemon.commons.enm;



/**
 *
 *
平台       次数
"网站平台";		199272  -》 网站
"安卓学生版";		56018   -》 安卓手机客户端
"IOS移动客户端";	41380   -》 iPhone客户端
"IOS学生版";		11776   -》 iPhone客户端
"移动终端";		8121    -》 iPhone客户端
"Android移动客户端";4382  -》安卓手机客户端
"IOS教师版";		2960    -》 iPad客户端
"";				286     -》 iPad客户端
"移动客户端";		281     -》 iPhone客户端
"安卓客户端";		15      -》安卓手机客户端

安卓平板客户端

安卓手机浏览器
安卓平板浏览器
iPhone浏览器
iPad浏览器


 * @author bob 北京易智享科技有限公司
 * @version v3 2015年5月7日 下午9:18:18
 *
 */
class Cons {
	final static int ROLE_TEACHER = 2;
	final static int ROLE_STUDENT = 1;
	final static int ROLE_Any = 0;
	
	final static int PLAT_WEB = 0;
	final static int PLAT_IOS = 1;
	final static int PLAT_ANDROID = 2;
	final static int PLAT_WPhone = 3;
}

public enum EnumPlatform implements IEnum {
	
	//------------------fields -------------------------
	//16 + x
	Browser  			(17, "网站浏览器",      Cons.ROLE_Any, Cons.PLAT_WEB),
	Browser_iPhone  	(18, "浏览器(iPhone)", Cons.ROLE_Any, Cons.PLAT_WEB),
	Browser_iPad  		(19, "浏览器(iPad)",   Cons.ROLE_Any, Cons.PLAT_WEB),
	Browser_Android		(20, "浏览器(安卓手机)", Cons.ROLE_Any, Cons.PLAT_WEB),
	Browser_APad		(21, "浏览器(安卓平板)", Cons.ROLE_Any, Cons.PLAT_WEB),

	//0 ~ 15
	App_Student_iOS         (1, "学生(iOS)", Cons.ROLE_STUDENT, Cons.PLAT_IOS),
    App_Student_Android		(3, "学生(安卓)", Cons.ROLE_STUDENT, Cons.PLAT_ANDROID),

    App_Teacher_iOS         (2, "老师(iOS)", Cons.ROLE_TEACHER, Cons.PLAT_IOS),
    App_Teacher_Android     (4, "老师(安卓)", Cons.ROLE_TEACHER, Cons.PLAT_ANDROID),

//    App_Teacher_APad		(5, "老师(安卓平板)",Cons.ROLE_TEACHER, Cons.PLAT_ANDROID),
//    App_Teacher_iPhone	(6, "老师(iPhone)",Cons.ROLE_TEACHER, Cons.PLAT_IOS),

	Type_Other			(99, "其他", Cons.ROLE_Any, Cons.PLAT_WEB);

	//------------------instance methods -------------------------
	
	
	private int ch;
	private String value;
	private int role;//表示学生老师，0不限，1学生，2老师
	private int plat;//表示平台，0浏览器，1苹果,2安卓
	private EnumPlatform(int ch, String value,int role,int plat) {
		this.ch = ch;
		this.value = value;
		this.role=role;
		this.plat=plat;
	}


	@Override
	public String getValue() {
		return value;
	}

	@Override
	public int getCode() {
		return ch;
	}

	public int getRole() {
		return role;
	}

	public int getPlat(){
		return plat;
	}

	public boolean isIos(){
		return plat==1;
	}

	public boolean isAndroid(){
		return plat==2;
	}


	//------------------static methods -------------------------
	static {
		for(EnumPlatform e : EnumPlatform.values()) {
			EnumCenter.registe(EnumPlatform.class, e);
		}
	}

	public static String explainAsString(int code) {
		return EnumCenter.explainAsString(EnumPlatform.class, code);
	}

	public static EnumPlatform explain(int code) {
		IEnum ie = EnumCenter.explain(EnumPlatform.class, code);
		if(ie == null) {
			return null;
		} else {
			return (EnumPlatform)ie;
		}
	}
	public static boolean isStudent(int code) {
		IEnum ie = EnumCenter.explain(EnumPlatform.class, code);
		if(ie == null) {
			return false;
		} else {
			EnumPlatform en=(EnumPlatform)ie;
			return en.getRole()==1;
		}
	}

	public static boolean isTeacher(int code) {
		IEnum ie = EnumCenter.explain(EnumPlatform.class, code);
		if(ie == null) {
			return false;
		} else {
			EnumPlatform en=(EnumPlatform)ie;
			return en.getRole()==2;
		}
	}


}