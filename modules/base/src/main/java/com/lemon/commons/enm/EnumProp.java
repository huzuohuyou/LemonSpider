package com.lemon.commons.enm;

/**
 * @author bob 北京易智享科技有限公司
 * @version v3 2015年5月7日 下午9:18:18
 *
 */
public enum EnumProp implements IEnumProp {
	// ------------------fields -------------------------
	// 检查网站是否正常运行的时间间隔，单位秒
	Prop_Web_Health_Check("web.health.check", "60"),

	//--------------------------------------数据中心根目录--------------------------------------
	Prop_Root	("root.", "asset/"),
	Prop_Trash	("trash.", "trash/"),
	Prop_Temp	("temp.", "temp/"),
	Prop_Root_St_Temp	("st_temp.", "st_temp/"),
	Prop_Data_Excel	("excel.", "excel/"),//日常数据统计信息xls存档目录

	Prop_Root_WebUpload	("root.upload","asset/"),

	Prop_Ansj_UserLibrary	("root.ansj", "ansj/"),


	//--------------------------------------超级视频--------------------------------------
	// 老师创建视频的位置
	Prop_Root_Media		("root.media", "asset/media/"),
	// 老师视频删除后的位置
	Prop_Trash_Media	("trash.media", "trash/media/"),
	// 超级视频url连接
	Prop_Url_Media("url.media", "/media/"),

	// 超级视频转成mp4后的位置
	Prop_Root_Media_Mp4		("root.media.mp4", "asset/mp4/"),
	// 超级视频转成mp4后被删除后的位置
	Prop_Trash_Media_Mp4	("trash.media.mp4", "trash/mp4/"),

	//--------------------------------------用户上传文件--------------------------------------
	// 老师上传文件的位置
	Prop_Root_File	("root.file", "asset/file/"),
	// 老师上传文件被删除后的位置
	Prop_Trash_File	("trash.file", "trash/file/"),
	// 用户资料URL连接
	Prop_Url_User_File	("url.user.file", "/file/"),

	// 用户上传的视频（mp4）的位置
	Prop_Root_Ump4	("root.ump4", "asset/ump4/"),

    Prop_Url_Ump4("url.ump4", "/ump4/"),
	// 用户上传的视频（mp4）被删除后的位置
	Prop_Trash_Ump4	("trash.ump4", "trash/ump4/"),


	//--------------------------------------用户头像--------------------------------------
	// 用户头像存放位置
	Prop_Root_User_Head			("root.user.head", "asset/user/head/"),
	//未剪裁的用户头像存放位置
	Prop_Root_User_Head_Origin	("root.user.head_origin", "asset/user/head_origin/"),
	//缩放的用户头像存放位置
	Prop_Root_User_Head_Origin_Backup("root.user.head_origin.backup", "asset/user/head_origin/backup"),
	// 用户头像URL连接
	Prop_Url_User_Head			("url.user.head", "/user/head/"),
	// 未剪裁的用户头像存放连接
	Prop_Url_User_Head_Origin	("url.user.head_origin", "/user/head_origin/"),


	// 用户（老师）证件存放位置
	Prop_Root_User_Card	("root.user.card", "asset/user/card/"),
	// 用户（老师）证件URL连接
	Prop_Url_User_Card	("url.user.card", "/user/card/"),


	//--------------------------------------试题相关--------------------------------------
	PROP_Root_Question	("root.ques", "asset/ques/"),
	PROP_Trash_Question	("trash.ques", "trash/ques/"),
	PROP_Url_Question	("url.ques", "/ques/outimg/"),
	PROP_Url_QuestImg	("url.quesimg", "/ques/srcimg/"),


	//--------------------------------------其它--------------------------------------
	//活动存放位置
	Prop_Root_Activity	("root.activity","asset/activity"),


	// 用户问题返回图片存放目录
	Prop_Root_Feedback	("root.feedback", "asset/feedback/"),
	// 用户问题反馈图片URL
	Prop_Url_Feedback	("url.feedback", "/feedback/"),


	// 学生上传的作文的存放位置
	Prop_Root_Article	("root.article", "asset/article/"),




	//--------------------------------------临时文件--------------------------------------
	// 老师上传视频临时的存放位置
	Prop_Root_Temp_Media	("temp.media", "temp/media/"),
	// 用户上传头像临时存放位置
	Prop_Root_Temp_Photo	("temp.photo", "temp/photo/"),
	// 用户上传文档临时存放位置
	Prop_Root_Temp_File		("temp.file", "temp/file/"),
	//文档处理中间文件
	Prop_Root_St_Temp_File	("st_temp.file", "st_temp/file/"),



	// 用户上传文件大小的上限值，单位MB
	Prop_Upload_Maxsz("upload.maxsz", "100"),


	// APP客服电话
	Prop_App_Custom_Tel("app.custom.tel", "18910554360"),

	// APP客服QQ
	Prop_App_Custom_QQ("app.custom.qq", "2256466535"),

	// APP客服邮箱
	Prop_App_Custom_Email("app.custom.email", "faq@chaojijiangshi.cn"),


	//老师推荐视频
	Prop_App_Teacher_Ad("app.new.function", "A4C7C195-E265-473C-812A-EC1DB8540618|A4C7C195-E265-473C-812A-EC1DB8540618|A4C7C195-E265-473C-812A-EC1DB8540618|A4C7C195-E265-473C-812A-EC1DB8540618"),

	//学生首页推荐视频
	Prop_App_Student_Ad("app.adverttisement", "8EF2DAC2-A8B6-45B8-A0DA-1D5FD528BC7B|5130D552-26E5-4882-A3E2-D53DCB9CAB28|68AD3EE4-63AB-4A24-8206-E32B9BFC3282|5AA1B86F-F4D9-462C-9350-4B64EC924120"),

	//网站视频教程
	Prop_Web_VedioCourseFunction("web.vedio.course.function", "14995950-06EB-4D07-B13C-5A15BC01A8B4|07AE3E44-D313-45F4-B750-B216BC74A380|80A22CE5-FE45-4329-BEA2-DFDC9EA44D1F|A4BBA917-5629-4E4A-A6F8-E1C47075EE38|DB682B4F-9338-45D5-8BF0-28CD2603CDA5|3E6F154A-B07B-414E-8220-8E80F6BDE0C0"),

	//网站视频教程
	Prop_Web_VedioCourseTotal("web.vedio.course.total", "622AFDC0-A401-4766-8884-41EE6850AA34"),

	// 数据信息收件邮箱
	Prop_Web_Data_Email("web.data.email", "yunwei@chaojijiangshi.cn"),

	Prop_Office_Transfor_Email("office.transfor.email", "yunwei@chaojijiangshi.cn"),

	Prop_Teacher_Info_Email("teacher.info.email", "zhuting@chaojijiangshi.cn"),

	Prop_Register_AddClazzId("register.clazzId","17374"),
	Prop_TeacherRegister_AddClazzId("teacherregister.clazzId","17372"),
	//
	Prop_Sms_App_Id("sms.id", "ccc02661e367bc128ac00769f776e912"),
	Prop_WeiXin_App_Id("weixin.appid","wxbd8dde674bc5f43c"),
	Prop_WeiXin_App_Key("weixin.appkey","94c0c51c24366c10e3a9b8336049b739"),
	Prop_Message_ApplyActivity("msg.applyactivity","【超级讲师】恭喜报名成功，获得10000积分(100元)！请登录www.chaojijiangshi.cn查看。您的账号为本机号码，密码{密码}。"),
	Prop_Message_StudentPhoneRegister("msg.student.phoneregister","您的奖品请到使用本手机{账号信息}登录超级讲师APP学生版领取，请使用iPad下载APP！"),
	Prop_Message_TeacherPhoneRegister("msg.teacher.phoneregister","您的奖品请到使用本手机{账号信息}登录超级讲师APP教师版领取，请使用iPad下载APP！"),
	Prop_Message_WarnChangeTel("msg.warn.changetel","该手机已经注册了账号，确定绑定新账号？一旦绑定，之前的账号将无法登陆！如不是本人操作，请无视！"),

	Prop_APP_PayMent("pay.payment.app","wx|alipay"),
	Prop_Web_PayMent("pay.payment.web","wx_pub_qr"),
	Prop_WX_PayMent("pay.payment.wx","wx_pub"),

	Prop_Pingpp_APP_Id("pingpp.appid","app_1WPCW5yT4Wr1WLOe"),
	Prop_Pingpp_Stu_APP_Id("pingpp.stu.appid","app_aLarf5fvbnvHuvfX"),
	Prop_Pingpp_APP_Key("pingpp.appkey","sk_test_54CK40KG84aD14044Kz9GuD8"),
	Prop_Pingpp_Public_Key("pingpp.publickey", "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAojlJF8tUUrTKcF8OdBFYcO8V9XXW/2rAPnzJgmTlztmB+MocqdXiF2gufi2ze4coJsu3ikFyvrox/tDMZP1CH/HDXQlecy7LS4UkskrS67BmTx8U7YfMc3DdEdYn4DSl7MYsXYw1+XZxAmgkanDI3UacNd6nejWa9LeyWRLV7y7PXBv3z1bgyw/iGWrsPf082J7BIBVW8JSYJWRVpcSYvYKgVLLe+4U7OL6qnY7QkbuYPCU3bzmqvfcqL83jjGutTyP07mzlMcdFd54kzuumDCdxLfjl2k7FYGfuHAUC5KvSxOjH2P7AGnPfvFW633Fr4Q7hIC+Wb8+lQl+LU5CcwIDAQAB")
	,
	Prop_Pay_Test("pay.test","1"),
	Prop_Normal_File("normal.file","22687|22894"),
	Prop_Server_Debug("server.debug","true"),
	//友盟API
	Prop_Umeng_iOSTea_AppKey("umeng.iosTea.appkey","55f0fd67e0f55a474b0030e8"),
	Prop_Umeng_iOSTea_AppMasterSecret("umeng.iosTea.appMasterSecret","wvfxtbomzjbfgymnzu3b9ycdcfs8l7f7"),

	Prop_Umeng_iOSStu_AppKey("umeng.iosstu.appkey","55d42967e0f55a92f0003780"),
	Prop_Umeng_iOSStu_AppMasterSecret("umeng.iosstu.appMasterSecret","ib57tepiaznwfzevcvq4oqghiszjge8n"),

	Prop_Umeng_AndroidTea_AppKey("umeng.androidTea.appkey","55f93155e0f55a3cd5000e09"),
	Prop_Umeng_AndroidTea_AppMasterSecret("umeng.appMasterSecret","vkhkrpuza0q0zl6zbvxet8zijztrwqvr"),

	Prop_Umeng_AndroidStu_AppKey("umeng.appkey","55eea3ce67e58e4b7b000e7d"),
	Prop_Umeng_AndroidStu_AppMasterSecret("umeng.appMasterSecret","nsge644mp6ierg8isvnxt6bpau2nq4vt"),

	Prop_Stu_Join_Cla("stu.join.cla",""),
	Prop_Super_Pwd("super.pwd","Laoshi360"),

    Prop_Word_Image("word.image","upload/barcode/code4teacher.png"),
	;




	// ------------------instance methods -------------------------
	private String name;
	private String value;
	private long lastTime = 0L;

	private EnumProp(String name, String value) {
		this.name = name;
		this.value = value;
	}

	@Override
	public int getCode() {
		return name.hashCode();
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public String getValue() {
		String v;
		if (name.startsWith("root.")
				|| name.startsWith("trash.")
				|| name.startsWith("temp.")
				|| name.startsWith("st_temp.")||name.startsWith("excel.")) {
			v = OS_ROOT + value;
		} else if(name.startsWith("url.")) {
			v = value;
		} else {
			v = getValueFromDb();
		}
		return v;
	}

	public String[] getValues(){
		String v = getValue();
		return v.split("\\|");
	}

	public String getValueFromDb() {
		return value;
	}

	@Override
	public int getValueAsInt() {
		int v = 0;
		try {
			v = Integer.parseInt(getValue());
		} catch (Exception e) {
		}
		return v;
	}

	@Override
	public long getValueAsLong() {
		long v = 0;
		try {
			v = Long.parseLong(getValue());
		} catch (Exception e) {
		}
		return v;
	}

	//	 ------------------static methods -------------------------
	private static final long ReloadInterval = 3600000L;

	private static String OS_ROOT = EnumOS.currentOsRoot();
}
