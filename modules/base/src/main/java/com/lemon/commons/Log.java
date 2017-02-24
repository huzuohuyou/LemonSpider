package com.lemon.commons;

import com.lemon.commons.text.StringMarker;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * Created by bob on 16/9/14.
 */
public final class Log {
	private final static int Level_Debug = 1;
	private final static int Level_Info = 2;
	private final static int Level_Warn = 3;
	private final static int Level_Error = 4;


	//这里需要根据项目情况进行调整设定
	private final static int logDestination = 2;              //1:console only; 2:log only;  0:close all.
	private final static boolean appendLocationInfo = true;   //是否把函数,行号拼接到日志中。
	private final static int lowestLogLevel = Level_Debug;


	private final static Logger _log = LoggerFactory.getLogger(Log.class);

	private Log() {

	}


	public final static void d(String msg) {
		p(Level_Debug, msg);
	}

	public final static void d(String format, Object... params) {
		p(Level_Debug, format, params);
	}

	public final static void i(String msg) {
		p(Level_Info, msg);
	}

	public final static void i(String format, Object... params) {
		p(Level_Info, format, params);
	}

	public final static void w(String msg) {
		p(Level_Warn, msg);
	}

	public final static void w(String msg, Exception e) {
		e.printStackTrace();
		p(Level_Warn, msg + e.getMessage());
	}

	public final static void w(String format, Object... params) {
		p(Level_Warn, format, params);
	}

	public final static void w(Exception e) {
		e.printStackTrace();
		p(Level_Error, e.getMessage());
	}


	public final static void e(String msg) {
		p(Level_Error, msg);
	}

	public final static void e(String msg, Exception e) {
		e.printStackTrace();
		p(Level_Error, msg + e.getMessage());
	}

	public final static void e(String format, Object... params) {
		p(Level_Error, format, params);
	}

	public final static void e(Exception e) {
		e.printStackTrace();
		p(Level_Error, e.getMessage());
	}

	//--------- private methods --------------
	private static String getLocationInfo() {
		StackTraceElement ste = Thread.currentThread().getStackTrace()[4];
		String fileName = ste.getFileName();
		int lineNum = ste.getLineNumber();
		return String.format("[%s # %d]\n", fileName, lineNum);
	}

	private final static void p(int level, String msgOrFormat, Object... params) {
		//是否需要打印
		if(logDestination <= 0 && level < Level_Warn) {
			return;
		}

		if(level < lowestLogLevel) {
			return;
		}

		String msg = "";
		//是否需要添加位置信息
		if(appendLocationInfo) {
			msg = getLocationInfo();
		}

		//是否是带格式的
		if(params != null && params.length > 0) {
			msg += new StringMarker(msgOrFormat, params).toString();
		} else {
			msg += msgOrFormat;
		}

		//根据日志level使用不同的方法
		switch (level) {
			case Level_Debug: {
				if(logDestination == 1) {
					System.out.print(msg);
				} else {
					_log.debug(msg);
				}
				break;
			}

			case Level_Info: {
				if(logDestination == 1) {
					System.out.print(msg);
				} else {
					_log.info(msg);
				}
				break;
			}

			case Level_Warn: {
				if(logDestination == 1) {
					System.err.print(msg);
				} else {
					_log.warn(msg);
				}
				break;
			}

			case Level_Error: {
				if(logDestination == 1) {
					System.err.print(msg);
				} else {
					_log.error(msg);
				}
				break;
			}
		}
	}
}
