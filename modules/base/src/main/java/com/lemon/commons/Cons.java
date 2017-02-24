package com.lemon.commons;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Cons {

	public static final long CACHE_INVALIDATE_TIME_LITTLE_DAY = 24 * 3600000L;// one
	// day
	public static final long CACHE_INVALIDATE_TIME_LITTLE_MINUTE = 5 * 60000L;// five
	// minute

	public static final long CACHE_INVALIDATE_TIME_LITTLE = 3600000L; // one
	// hour
	public static final long CACHE_INVALIDATE_TIME_LITTLE_SHORT = 1800000L; // half

	public static final int USER_HEAD_WIDTH = 500; // half

	// old data user type
	public static final Integer TYPE_ACCOUNT_ADMINISTRATOR = 1;
	public static final Integer TYPE_ACCOUNT_TEACHER = 2;
	public static final Integer TYPE_ACCOUNT_STUDENT = 3;
	public static final Integer TYPE_ACCOUNT_PARENT = 4;
	public static final Integer TYPE_ACCOUNT_AGENCY_ADMIN = 31; // =16+8+4+2+1,
	// 其中16，即0b
	// 10000 表示机构，
	// 0b 11000
	// 表示机构高级管理账号，0b
	// 10001
	// 机构学生等等，


	public static class Error {
		public static final String ErrNum = "errNum";
		public static final String ErrMsg = "errMsg";
		public static final String ErrDefaultPage = "redirect:/error";
		public static final String Unauthorized401 	= "redirect:/error/401";
		public static final String Forbidden403 	= "redirect:/error/403";
		public static final String PageNotFound404 	= "redirect:/error/404";
		public static final String ServerCrashed500 = "redirect:/error/500";
		
		public static final String VipOnlyPage = "redirect:/error/vipOnly";
	}

	public static class AppLink {
		private static final String URL_OUR = "http://www.chaojijiangshi.cn/";
		private static final String URL_APPLE = "https://itunes.apple.com/cn/app/";

		public static final String teacherGate = URL_OUR + "app4teacher";
		public static final String teacherShortUrl = "http://t.cn/R2dN8ND";
		public static final String teacherOnIOS = URL_APPLE + "chao-ji-jiang-shi-lao-shi-ban/id1007978729?mt=8";
		public static final String teacherOnAndroid = URL_OUR + "static/download/SuperTeacher.apk";
        public static final String teacherOnAndroidPad = URL_OUR + "static/download/SuperTeacherPad.apk";

		public static final String studentGate = URL_OUR + "app4student";
		public static final String studentShortUrl = "http://t.cn/R2dpzG4";
		public static final String studentOnIOS = URL_APPLE + "chao-ji-jiang-shi-xue-sheng-ban/id1007979315?mt=8";
		public static final String studentOnAndroid = URL_OUR + "static/download/SuperStudent.apk";

	}

	public static class Message {
		public static final String MessageNum = "messageNum";
		public static final String Message = "message";
	}

    public static class Search {
        public final static String PrefixSearch = "search_";

    }

	public static class Pagination {
        public final static String PrefixPaging     = "pag_";
        public final static String KeyPageSize      = "size";
        public final static String KeyPageNum       = "page";
        public final static String KeySortBy        = "sortBy";
        public final static String KeySortDesc      = "sortDesc";
        public final static String KeySortAsc       = "sortAsc";

        // 列表的默认PageSize
		public final static int DefaultPageSize12 = 12;		//四行三列 表格
        public final static int DefaultPageSize4Grid = 9;
        public final static int DefaultPageSize = 10;
		public final static int DefaultPageSize3 = 3;
		public final static int DefaultPageSize15 = 15;
		public final static int DefaultPageSize20 = 20;
		public final static int DefaultPageSize100 = 100;
		public final static int DefaultNavigationPage5 = 5;
		public final static int PageSize4Class = 7;
		public final static int DefaultNavigationPage10 = 10;
		public final static int DefaultPageNum = 1;
	}

	public static class User {
		public final static String ResetPwd = "chaojijiangshi";
		public final static String DefaultUserName = "超级讲师";
		public final static String DefaultStuNickName = "学童";
		public final static String DefaultTeaNickName = "讲师";
		public final static Integer active=50;
	}


	public static class Date {
		public final static long HOUR_IN_MS = 3600000L;
		public final static long DAY_IN_MS = 3600000L * 24L;
		public final static long WEEK_IN_MS = 7 * DAY_IN_MS;
		public final static long DAY = 86400000L, HOUR = 3600000L, MINUTE = 60000L;

		// date format
		public static final SimpleDateFormat DATE_TIME_FORMAT = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		public static final SimpleDateFormat DATE_TIME_SHORT_FORMAT = new SimpleDateFormat("yyyy/MM/dd HH:mm");
		public static final SimpleDateFormat DATE_TIME_FORMAT_LONG = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
		public static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy/MM/dd");
		public static final SimpleDateFormat DATE_MONTH = new SimpleDateFormat("yyyyMM");
		public static final SimpleDateFormat DATE_INT = new SimpleDateFormat("yyyyMMdd");
		public static final SimpleDateFormat DATE_FORMAT_CREATE_CLASSMGR = new SimpleDateFormat("yyyy-MM-dd");
		public static final SimpleDateFormat DATE_SHORT_FORMAT = new SimpleDateFormat("yy/MM/dd");

		public static final SimpleDateFormat TIME_FORMAT = new SimpleDateFormat("HH:mm:ss");
		public static final SimpleDateFormat TIME_SHORT_FORMAT = new SimpleDateFormat("HH:mm");

		public static final DecimalFormat MONEY_DEICAML_FORMAT = new DecimalFormat("###,###,###.##");

		public static int currentYear() {
			Calendar c = Calendar.getInstance();
			return c.get(Calendar.YEAR);
		}

		public static int currentMonth() {
			Calendar c = Calendar.getInstance();
			return c.get(Calendar.MONTH) + 1;// 月份从0开始，
		}

		public static int currentDay() {
			Calendar c = Calendar.getInstance();
			return c.get(Calendar.DAY_OF_MONTH);// 月份从0开始，
		}

		public static int currentYearMonthDay() {
			Calendar c = Calendar.getInstance();
			int ymd = 0;
			ymd = c.get(Calendar.YEAR) * 10000 + (c.get(Calendar.MONTH) + 1) * 100 + c.get(Calendar.DATE);
			return ymd;
		}

	}

	public static class NumberFormat {
		public static final DecimalFormat MONEY_DEICAML_FORMAT = new DecimalFormat("###,###,###.##");
		public static final DecimalFormat Int1Digit5 = new DecimalFormat("0.00000");// 格式化小数
		public static final DecimalFormat Int1Digit2 = new DecimalFormat("0.00");// 格式化小数
		public static final DecimalFormat Int1Digit1 = new DecimalFormat("0.0");// 格式化小数
	}

	public static class Num {
		private static Map<Integer, String> num2Chinese = new ConcurrentHashMap<Integer, String>();
		private static boolean isInitNum2Chinese = false;

		private static void initNum2Chinese() {
			num2Chinese.put(1, "一");
			num2Chinese.put(2, "二");
			num2Chinese.put(3, "三");
			num2Chinese.put(4, "四");
			num2Chinese.put(5, "五");
			num2Chinese.put(6, "六");
			num2Chinese.put(7, "七");
			num2Chinese.put(8, "八");
			num2Chinese.put(9, "九");
			num2Chinese.put(10, "十");
		}



		public static String num2Chinese(int i) {
			synchronized (Num.class) {
				if (!isInitNum2Chinese) {
					initNum2Chinese();
					isInitNum2Chinese = true;
				}
			}
			return num2Chinese.get(i);
		}

		public final static Integer birthdayYmdToGradeAge(Integer birthdayYmd){
			if(birthdayYmd == null){
				return 7;
			}
			Integer grade = Cons.Date.currentYear() - birthdayYmd  + Cons.Date.currentMonth() / 9;
			return grade;
		}

		/**
		 * 学制岁数 转换成学段中文
		 * @param gradeAge
		 * @return
		 */
		public final  static  String GradeAgeToSegmentStr(Integer gradeAge){
			if(gradeAge < 7){
				return "学年班";
			}
			else if(7 <= gradeAge && gradeAge <13 ){
				return "小学";
			}else if( gradeAge < 16){
				return "初中";
			} else  if(gradeAge < 19){
				return "高中";
			}else if(gradeAge < 30){
				return "大学";
			}
			else if(gradeAge < 34){
				return "职教";
			}else {
				return "企业";
			}
		}


		/**
		 * 学制出生年 转成 真实年级
		 * @param birthdayYmd
		 * @return
		 */
		public final static Integer  birthdayToGrade(Integer birthdayYmd){
			Integer grade = birthdayYmdToGradeAge(birthdayYmd);

			return gradeAgeToGrade(grade);
		}

		/**
		 * 学制年级 转换成 真实年级
		 * @param gradeAge
		 * @return
		 */
		public final static Integer gradeAgeToGrade(Integer gradeAge){
			if(gradeAge < 7){
				return gradeAge;
			}
			if(7 <= gradeAge && gradeAge <13 ){
				return gradeAge - 6;
			}
			else if( gradeAge < 16){
				return gradeAge - 12;
			}
			else if(gradeAge < 19){
				return gradeAge - 15;
			}
			else if(gradeAge < 30){
				return gradeAge - 18;
			}
			else if(gradeAge < 34){
				Integer grade = gradeAge - 29;

				return grade > 4 ? 4:grade;
			}
			else{
				Integer grade = gradeAge - 40;
				return grade;
			}
		}

		/**学制出生年换算 end**/

	}

	public static class Cache {
		public static final long CACHE_INVALIDATE_TIME_LITTLE = 3600000L; // onehour
		public static final long CACHE_INVALIDATE_TIME_LITTLE_SHORT = 1800000L; // halfhour

		/**
		 * 缓存相关
		 */
		private static final int MINUTE = 60 * 1000, HOUR = 60 * 60 * 1000;
		public static final Integer INDEX_TEACHER_REFRESH_TIME = 10 * MINUTE;
		public static final Integer CACHE_LECTRUE_LIST_UPDATE_TIME = 10 * MINUTE;
		public static final long CACHE_INVALIDATE_TIME_SHORTEST = MINUTE;
		public static final long CACHE_INVALIDATE_TIME_SHORT = 10 * MINUTE;
		public static final long CACHE_INVALIDATE_TIME_MEDIAN = 12 * HOUR; // halfday
		public static final long CACHE_INVALIDATE_TIME_LONG = 10 * 24 * HOUR; // 10days
	}


	public static class Session {
		/**
		 * Session
		 */
		public static final String SESSION_KEY_USER_TYEP = "userType";
		public static final String SESSION_KEY_USER = "user";
		public static final String SESSION_KEY_USER_GROUPS = "userGroups";
		public static final String SESSION_KEY_SUGGEST_USER = "suggestUser";
		public static final String SESSION_KEY_ADMIN_ID = "adminId";
		public static final String SESSION_KEY_ADMIN = "admin";
		public static final String SESSION_KEY_ADMIN_NAME = "adminName";
		public static final String SESSION_KEY_IF_LANDED = "ifLand";
		public static final String SESSION_KEY_ORIGIN_PHOTO_URL = "originPhotoUrl";
		public static final String SESSION_KEY_ORIGIN_PHOTO_TYPE = "originPhotoType";

		public static final String SESSION_KEY_SEARCH_COURSE_PAGE_COUNT = "searchCoursePageCount";
		public static final String SESSION_KEY_SEARCH_TEACHER_NAME = "searchTeacherName";
		public static final String SESSION_KEY_SEARCH_TEACHER_GENDER = "searchTeacherGender";
		public static final String SESSION_KEY_SEARCH_TEACHER_TYPE = "searchTeacherType";
		public static final String SESSION_KEY_SEARCH_TEACHER_SUBJECT = "searchTeacherSubject";
		public static final String SESSION_KEY_SEARCH_TEACHER_LOCATION = "searchTeacherLocation";
		public static final String SESSION_KEY_SEARCH_TEACHER_PAGE_COUNT = "searchTeacherPageCount";
		public static final String SESSION_KEY_SEARCH_LECTURE_PAGE_COUNT = "searchLecturePageCount";
		public static final String SESSION_KEY_SEARCH_TEACHER_COURSE_PAGE_COUNT = "searchTeacherCoursePageCount";
		public static final String SESSION_KEY_ACHIEVEMENT_TEACHER_ORDER_PAGE_COUNT = "achievementTeacherOrderPageCount";
		public static final String SESSION_KEY_LOGIN_PREPAGE = "loginPrePage";
		public static final String SESSION_KEY_VALIDATE_IMAGE_CODE = "validateImageCode";
		public static final String SESSION_KEY_UPLOAD_PHOTO_LOCATION = "uploadPhotoLocation";

	}


	public static class Rex {
		public static final String User_tel = "(1[34578][0-9]{9})";
		public static final String User_username = "([\u4E00-\u9FA5]{2}|[\u4E00-\u9FA5][a-zA-Z0-9]{2}|[a-zA-Z][a-zA-Z0-9][\u4E00-\u9FA5]|[a-zA-Z][\u4E00-\u9FA5][a-zA-Z0-9]|[a-zA-Z\u4E00-\u9FA5][a-zA-Z0-9\u4E00-\u9FA5]{3,19}|[\u4E00-\u9FA5]{3}|[\u4E00-\u9FA5][a-zA-Z0-9][\u4E00-\u9FA5]|[\u4E00-\u9FA5]{2}[a-zA-Z0-9]|[a-zA-Z][\u4E00-\u9FA5]{2})";
		public static final String User_nickName = "([\u4E00-\u9FA5]{2}|[a-zA-Z0-9\u4E00-\u9FA5]{4,20}|[\u4E00-\u9FA5][a-zA-Z0-9]{2}|[a-zA-Z0-9]{2}[\u4E00-\u9FA5]|[a-zA-Z0-9][\u4E00-\u9FA5][a-zA-Z0-9]|[\u4E00-\u9FA5]{3}|[a-zA-Z0-9][\u4E00-\u9FA5]{2}|[\u4E00-\u9FA5][a-zA-Z0-9][\u4E00-\u9FA5]|[\u4E00-\u9FA5]{2}[a-zA-Z0-9])";
		public static final String User_fullName = "([\u4E00-\u9FA5]{2,10})";
		public static final String User_Email = "^(([a-zA-Z0-9_\\-\\.]+)@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.)|(([a-zA-Z0-9\\-]+\\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(\\]?))$";
		public static final String User_pwd = "([A-Za-z0-9~!@#$%^&*()_+\\.]{6,20})";
		public static final String User_smsAuthc = "([0-9]{6})";
		public static final String User_role = "[st]";
		public static final String User_gender = "[男女]";
		public static final String User_qq = "([1-9][0-9]{4,14})?";
		public static final String Subject_wenkuIds = "(((8|9|31|72|73)[0-9]{4}[0]{12})\\|)*";
		public static final String Asset_ids = "(([0-9]+)\\|)*";
		public static final String Segment = "(8|9|31|72|73|74)[0]{16}";
		public static final String Classmgr_openDate = "((20)[0-9]{2}(-)(01|02|03|04|05|06|07|08|09|10|11|12)(-)(0|1|2|3)[0-9])";
		public static final String Base_yesOrNo = "(y|n)";
		public static final String Date = "((20)[0-9]{2}(-)(01|02|03|04|05|06|07|08|09|10|11|12)(-)(0|1|2|3)[0-9])";
		public static final String NormalCharacter = "(市|第|学|部|路|区|县|-|（|）|\\(|\\)| )";

		public static final String InvisibleCharacter = "[\\s]*"; // 匹配任何不可见字符，包括空格、制表符、换页符等等。等价于[
		// \f\n\r\t\v]。
		public static final String VisibleCharacter = "[\\S]*"; // 匹配任何可见字符。等价于[^
		// \f\n\r\t\v]。

		public static final String ChineseWord = "[\u4E00-\u9FA5]*";// 匹配中国的汉字
		public static final String NotChineseWord = "[^\u4E00-\u9FA5]";// 匹配中国的汉字
		public static final String NotChineseWordAbndNum = "[^\u4E00-\u9FA50-9]";// 匹配中国的汉字

		public static final String NotDirNameCharacterPattern="(\\*|\\\\|\\||/|\\?|<|>)";
		public static final String NotDirNameCharacter="*\\|/?<>";

		public static boolean match(String str,String pattern){
			if(str == null){
				return false;
			}

			return str.matches(pattern);
		}
	}

	public static class FileExt {
		// 文件相关
		public static final String FILEEXT_WORD = "doc";
		public static final String FILEEXT_WORDNEW = "docx";
		public static final String FILEEXT_PPT = "ppt";
		public static final String FILEEXT_PPTNEW = "pptx";
		public static final String FILEEXT_XLS = "xls";
		public static final String FILEEXT_XLSNEW = "xlsx";
		public static final String FILEEXT_PDF = "pdf";
		public static final String FILEEXT_TXT = "txt";
		public static final String FILEEXT_JPG = "jpg";
		public static final String FILEEXT_JPEG = "jpeg";
		public static final String FILEEXT_GIF = "gif";
		public static final String FILEEXT_PNG = "png";
		public static final String FILEEXT_BMP = "bmp";
		public static final String FILEEXT_MP3 = "mp3";
		public static final String FILEEXT_WMA = "wma";
		public static final String FILEEXT_RAR = "rar";
		public static final String FILEEXT_ZIP = "zip";
		public static final String FILEEXT_JSON = "json";
		public static final String FILEEXT_MEDIA = "m";// 数据库sub_type字段，用于保存视频
		public static final String FILEEXT_OTHER = "other";

	}

	public static class Media {
		public static String Thumb_Image = "tn-0.jpg";
		public static String Original_Image = "tn-0.jpg";
		public static String DefaultThumbnail = "tn-0.jpg";
		public static String DefaultScreenShot = "ss-0.jpg";
	}


	public static class File {
		public static int Max_Post_Size = 110 * 1024 * 1024;// 文件最大限制100M
		public static int Max_Photo_Size = 50 * 1024 * 1024;// 图片最大限制50M
		public static int Folder_Num = 10000;// 子文件夹数量
		public static int Max_Upload_Ump4_Size = 310 * 1024 * 1024;// 文件最大限制300M
	}

	public static class Encode {
		public static String UTF_8 = "UTF-8";
		public static String GBK = "GBK";

	}

	public static class ProcessStatus {
		public static Integer Reday = 0;
		public static Integer Success = 1;
		public static Integer Fail = 2;

	}

	public static class Excel {
		public static String[] Student = {"视频编号", "视频标题", "观看进度", "最后观看时间", "最后观看设备"};
		public static String[] Clazz = {"视频编号", "学生姓名", "视频标题", "观看进度", "最后观看时间", "最后观看设备"};
		public static String[] School_Stu = {"姓名", "年级", "用户名", "密码"};
		public static String[] School_Tea = {"姓名", "科目", "用户名", "密码"};
		public static String[] School_TeaDetail = {"编号","用户名","真实姓名","性别","手机","邮箱","地区","学校","视频数","文档数","班级数","学生数","注册时间","最后登录"};
        public static String[] Vip_School_Media = {"编号", "标题", "老师", "知识点", "视频时长", "观看次数"};
		public static String[] User_Active = {"日期", "总活跃数", "网站端活跃数", "iOS活跃数", "Andriod活跃数", "老师活跃数","学生活跃数","网站端老师活跃数","网站端学生活跃数","iOS老师活跃数","Android老师活跃数","iOS学生活跃数","Android学生活跃数"};
		public static String[] Clazz_Day = {"日期", "班级总数", "新增班级数", "新增实名班级数", "新增网络班级数","活跃班级数", "活跃网络班数","活跃实名班数"};
		public static String[] Asset_Day = {"日期", "视频总数", "新增视频数", "视频观看数", "文档总数", "新增文档数","文档下载数"};
		public static String[] User_Day = {"日期", "累计老师用户","累计学生用户","总新增数", "网站端新增", "iOS新增", "Andriod新增", "老师新增","学生新增","网站端老师新增","网站端学生新增","iOS老师新增数","Android老师新增","iOS学生新增","Android学生新增"};
		public static String[] School_User_Day = {"学校", "地区", "新增用户数","网站端新增", "iOS新增", "Andriod新增"};
		public static String[] School_User_Active_Day = {"学校", "地区", "活跃用户数","网站端活跃", "iOS活跃", "Andriod活跃"};
		public static String[] Media_User_Watch_Day = {"视频观看次数", "网站端观看次数", "网站端观看人数","网站端活跃", "iOS活跃", "Andriod活跃"};
		public static String[] Activity_User = {"编号", "姓名", "手机号码","地区", "学校", "报名时间"};
		public static String[] Medai_Watch = {"类别","总数","网站端","iOS端","Android端","老师","学生","网站端老师","网站端学生","iOS端老师","iOS端学生","Android端老师","Android学生"};

	}

	public static class UserStatusInfo {
		// 按位|运算
		public static Integer Card_Not_Upload = 1017;
		public static Integer Card_Upload = 1019;

		// 整除求余
		public static Integer Card_Not_Upload_ = 2;
		public static Integer Card_Upload_ = 4;
		public static Integer Phone = 64;
	}

	public static final List<Integer> Net_Clazz = new ArrayList<Integer>();
	public static final List<Integer> Real_Clazz = new ArrayList<Integer>();
	public static final List<Integer> Total_Clazz = new ArrayList<Integer>();


	public static class DirUtil{

		public  static  boolean isRightName(String name){
			if(name == null || name.trim().length() == 0)
				return false;
			if(name.length() > 100){
				return false;
			}
			Pattern p_src = Pattern.compile(Rex.NotDirNameCharacterPattern, Pattern.CASE_INSENSITIVE);
			Matcher m_src = p_src.matcher(name);
			return !m_src.find();
		}

		public static String genenalReplaceReg(String dir){
			String tmp = dir;
			tmp = tmp.replaceAll("\\(","\\\\(");
			tmp = tmp.replaceAll("\\)","\\\\)");
			tmp = tmp.replaceAll("\\[","\\\\[");
			tmp = tmp.replaceAll("\\]","\\\\]");
			tmp = tmp.replaceAll("\\{","\\\\{");
			tmp = tmp.replaceAll("\\}","\\\\}");
			tmp = tmp.replaceAll("\\.","\\\\.");
			tmp = tmp.replaceAll("\\!","\\\\!");
			tmp = tmp.replaceAll("\\|","\\\\|");
			return "^"+tmp;
		}
	}

	public static class  YunFolder{
		public static  String MyFavorites = "我的收藏";
	}

}
