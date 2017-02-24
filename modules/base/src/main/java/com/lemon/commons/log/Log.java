package com.lemon.commons.log;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lemon.commons.text.StringMarker;

public class Log {

	protected static int logSwitch = 1;//1:log only; 2:console only; 0:close all.

	public static Log getLogger(Class<?> type) {
		return new Log(type);
	}

	public static Log getLogger(Class<?> type, String monitor) {
		return new Log(type);
	}


	private Logger _log = null;

	private Log(Class<?> type) {
		_log = LoggerFactory.getLogger(type);
	}

	public final void debug(String msg) {
		switch(logSwitch) {
		case 1:
			_log.debug(msg);
			break;
		case 2:
			System.out.println(msg);
			break;
		default:
			break;
		}
	}

	public final void debug(String format, Object... param) {
		switch(logSwitch) {
		case 1:
			_log.debug(format, param);
			break;
		case 2:
			System.out.println(new StringMarker(format, param));
			break;
		default:
			break;
		}
	}

	public final void info(String msg) {
		switch(logSwitch) {
		case 1:
			_log.info(msg);
			break;
		case 2:
			System.out.println(msg);
			break;
		default:
			break;
		}
	}

	public final void info(String format, Object... param) {
		switch(logSwitch) {
		case 1:
			_log.info(format, param);
			break;
		case 2:
			System.out.println(new StringMarker(format, param));
			break;
		default:
			break;
		}
	}

	public final void warn(String msg) {
		switch(logSwitch) {
		case 1:
			_log.warn(msg);
			break;
		case 2:
			System.out.println(msg);
			break;
		default:
			break;
		}
	}

	public final void warn(String format, Object... param) {
		switch(logSwitch) {
		case 1:
			_log.warn(format, param);
			break;
		case 2:
			System.out.println(new StringMarker(format, param));
			break;
		default:
			break;
		}
	}

	public final void error(String msg) {
		switch(logSwitch) {
		case 1:
			_log.error(msg);
			break;
		case 2:
			System.err.println(msg);
			break;
		default:
			break;
		}
	}

	public final void error(String format, Object... param) {
		switch(logSwitch) {
		case 1:
			_log.error(format, param);
			break;
		case 2:
			System.err.println(new StringMarker(format, param));
			for(Object o : param) {
				if(o instanceof Exception) {
					((Exception)o).printStackTrace();
				}
			}
			break;
		default:
			break;
		}
	}

	public final void error(Exception e) {
		switch(logSwitch) {
		case 1:
			_log.error("", e);
			break;
		case 2:
			System.err.println(e.getMessage());
			e.printStackTrace();
			break;
		default:
			break;
		}
	}
}
