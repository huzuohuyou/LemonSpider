package com.lemon.commons;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * http://www.w3.org/Protocols/rfc2616/rfc2616-sec10.html<br>
 * 状态码统一编码<br>
 * 主要分为1xx，2xx，3xx，4xx，5xx。参考w3c的http标准。<br>
 * 其中1xx表示信息正在构建过程中，包括100开始，101进行中，102信息不完整等。<br>
 * 2xx表示状态正常或正在运行中，如200ok，201created，202valid，等。<br>
 * 3xx表示需要进一步的动作，或者是采取些临时性的动作，如300暂停，301停止，302关闭等等<br>
 * 4xx表示一般性的错误、状态异常，如400错误请求，等<br>
 * 5xx表示内部server出错了<br>
 * <br>
 * 1xx Informational prepareing<br>
 * 100 start，开始<br>
 * 101 ongoing，进行中<br>
 * 102 incomplete，信息不完整<br>
 * <br>
 * 2xx valid/ok cases<br>
 * 200 ok，正常，可访问，可下载，etc<br>
 * 201 created，表示某个任务、东西创建成功<br>
 * 202 valid，合法的，对应401非法的<br>
 * 203 hide，隐藏的<br>
 * <br>
 * 3xx 需要进一步动作的<br>
 * 300 paused，暂停<br>
 * 301 stop，停止<br>
 * 302 closed，关闭<br>
 * 303 canceled，取消了<br>
 * <br>
 * 4xx 一般性的错误、状态异常<br>
 * 400 error request，错误的请求，非法的请求<br>
 * 401 unauthorized，未授权的，不允许的，非法的。<br>
 * 402 payment required，需付费的<br>
 * 403 Forbidden，禁止的。<br>
 * 404 Not Found，未能找到，缺失了<br>
 * 405 Method/Action Not allowed，动作不被允许<br>
 * 406 Not Acceptable，不识别的命令或调用<br>
 * 407 More Parameters Need，参数不完整，需要更多的参数<br>
 * 408 Timeout，超时<br>
 * 409 Conflict，冲突，可能是wiki编辑和别的冲突，也可以是组成员共同编辑东西发生冲突<br>
 * 410 Gone，资源不再存在了，之前曾经有过。<br>
 * <br>
 * 415 Unsupported Media Type，不支持的文库类型等。<br>
 * <br>
 * 5xx 内部Server错误<br>
 * 500 Internal Server Error，<br>
 * 501 Not Implemented<br>
 * 502 Bad Gateway不应该走<br>
 * 503 Service Unavailable<br>
 * 
 */
public class Code {
	/**
	 * 1xx表示信息正在构建过程中，包括100开始，101进行中，102信息不完整等。<br>
	 * 2xx表示状态正常或正在运行中，如200ok，201created，202valid，等。<br>
	 * 3xx表示需要进一步的动作，或者是采取些临时性的动作，如300暂停，301停止，302关闭等等<br>
	 * 4xx表示一般性的错误、状态异常，如400错误请求，等<br>
	 * 5xx表示内部server出错了<br>
	 */
	public final static String C1xx = "c1xx";
	/**
	 * start，开始
	 * 
	 * @see Code#C1xx
	 */
	public final static int C100 = 100;

	/**
	 * ongoing，进行中
	 * 
	 * @see Code#C1xx
	 */
	public final static int C101 = 101;

	/**
	 * incomplete，信息不完整
	 * 
	 * @see Code#C1xx
	 */
	public final static int C102 = 102;

	/**
	 * 2xx valid/ok cases<br>
	 * 200 ok，正常，可访问，可下载，etc<br>
	 * 201 created，表示某个任务、东西创建成功<br>
	 * 202 valid，合法的，对应401非法的<br>
	 */
	public final static String C2xx = "c2xx";
	/**
	 * ok，正常，可访问，可下载，etc
	 * 
	 * @see Code#C2xx
	 */
	public final static int C200 = 200;
	/**
	 * created，表示某个任务、东西创建成功
	 * 
	 * @see Code#C2xx
	 */
	public final static int C201 = 201;
	/**
	 * valid，合法的，对应401非法的
	 * 
	 * @see Code#C2xx
	 */
	public final static int C202 = 202;
	/**
	 * hide，隐藏的
	 * 
	 * @see Code#C2xx
	 */
	public final static int C203 = 203;

	/**
	 * 3xx 需要进一步动作的<br>
	 * 300 paused，暂停<br>
	 * 301 stop，停止<br>
	 * 302 closed，关闭<br>
	 * 303 canceled，取消了<br>
	 */
	public final static String C3xx = "c3xx";

	/**
	 * paused，暂停
	 * 
	 * @see Code#C3xx
	 */
	public final static int C300 = 300;
	/**
	 * stop，停止
	 * 
	 * @see Code#C3xx
	 */
	public final static int C301 = 301;
	/**
	 * closed，关闭
	 * 
	 * @see Code#C3xx
	 */
	public final static int C302 = 302;
	/**
	 * canceled，取消了
	 * 
	 * @see Code#C3xx
	 */
	public final static int C303 = 303;

	/**
	 * 4xx 一般性的错误、状态异常<br>
	 * 400 error request，一般性的错误，错误的请求，非法的请求<br>
	 * 401 unauthorized，未授权的，不允许的，非法的。<br>
	 * 402 payment required，需付费的。<br>
	 * 403 Forbidden，禁止的。<br>
	 * 404 Not Found，未能找到，缺失了。<br>
	 * 405 Method/Action Not allowed，动作不被允许。<br>
	 * 406 Not Acceptable，不识别的命令或调用。<br>
	 * 407 More Parameters Need，参数不完整，需要更多的参数。<br>
	 * 408 Timeout，超时。<br>
	 * 409 Conflict，冲突，可能是wiki编辑和别的冲突，也可以是组成员共同编辑东西发生冲突。<br>
	 * 410 Gone，资源不再存在了，之前曾经有过。<br>
	 * 
	 * 415 Unsupported Media Type，不支持的文库类型等。<br>
	 */
	public final static String C4xx = "c4xx";

	/**
	 * error request，一般性的错误，错误的请求，非法的请求
	 * 
	 * @see Code#C4xx
	 */
	public final static int C400 = 400;

	/**
	 * unauthorized，未授权的，不允许的，非法的。
	 * 
	 * @see Code#C4xx
	 */
	public final static int C401 = 401;

	/**
	 * payment required，需付费的
	 * 
	 * @see Code#C4xx
	 */
	public final static int C402 = 402;

	/**
	 * Forbidden，禁止的。
	 * 
	 * @see Code#C4xx
	 */
	public final static int C403 = 403;

	/**
	 * Not Found，未能找到，缺失了
	 * 
	 * @see Code#C4xx
	 */
	public final static int C404 = 404;

	/**
	 * Method/Action Not allowed，动作不被允许
	 * 
	 * @see Code#C4xx
	 */
	public final static int C405 = 405;

	/**
	 * Not Acceptable，不识别的命令或调用
	 * 
	 * @see Code#C4xx
	 */
	public final static int C406 = 406;

	/**
	 * More Parameters Need，参数不完整，需要更多的参数
	 * 
	 * @see Code#C4xx
	 */
	public final static int C407 = 407;

	/**
	 * Timeout，超时
	 * 
	 * @see Code#C4xx
	 */
	public final static int C408 = 408;

	/**
	 * Conflict，冲突，可能是wiki编辑和别的冲突，也可以是组成员共同编辑东西发生冲突
	 * 
	 * @see Code#C4xx
	 */
	public final static int C409 = 409;

	/**
	 * Gone，资源不再存在了，之前曾经有过。
	 * 
	 * @see Code#C4xx
	 */
	public final static int C410 = 410;

	/**
	 * Unsupported Media Type，不支持的文库类型等。
	 * 
	 * @see Code#C4xx
	 */
	public final static int C415 = 415;

	/**
	 * 5xx 内部Server错误<br>
	 * 500 Internal Server Error，<br>
	 * 501 Not Implemented<br>
	 * 502 Bad Gateway不应该走<br>
	 * 503 Service Unavailable<br>
	 */
	public final static String C5xx = "c5xx";
	/**
	 * Internal Server Error，
	 * 
	 * @see Code#C5xx
	 */
	public final static int C500 = 500;

	/**
	 * Not Implemented
	 * 
	 * @see Code#C5xx
	 */
	public final static int C501 = 501;

	/**
	 * Bad Gateway不应该走
	 * 
	 * @see Code#C5xx
	 */
	public final static int C502 = 502;

	/**
	 * Service Unavailable
	 * 
	 * @see Code#C5xx
	 */
	public final static int C503 = 503;

	public static final List<Integer> COMMON_STATUS_ALL = new ArrayList<Integer>();
	public static final List<Integer> COMMON_STATUS_VALID = new ArrayList<Integer>();
	public static final List<Integer> COMMON_STATUS_SHOW = new ArrayList<Integer>();
	public static final List<Integer> COMMON_STATUS_HIDE = new ArrayList<Integer>();

	public static final Map<Integer, String> CODE_EXPLAIN = new HashMap<Integer, String>();

	static {
		COMMON_STATUS_ALL.add(Code.C200);
		COMMON_STATUS_ALL.add(Code.C203);
		COMMON_STATUS_ALL.add(Code.C303);
		COMMON_STATUS_ALL.add(Code.C404);

		COMMON_STATUS_VALID.add(Code.C200);
		COMMON_STATUS_VALID.add(Code.C203);

		COMMON_STATUS_SHOW.add(Code.C200);

		COMMON_STATUS_HIDE.add(Code.C203);

		CODE_EXPLAIN.put(Code.C200, "正常");
		CODE_EXPLAIN.put(Code.C203, "隐藏");
		CODE_EXPLAIN.put(Code.C303, "删除");
		CODE_EXPLAIN.put(Code.C404, "不存在");
	}

}
