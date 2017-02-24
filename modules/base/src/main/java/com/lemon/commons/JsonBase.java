package com.lemon.commons;


/**
 * @author bob 北京易智享科技有限公司
 * @version v3 2015年5月5日 下午9:45:58
 *
 */
public abstract class JsonBase<T, S> extends Lemon {
	public static final int OK = 0;
	public static final String MsgOK = "OK";

	protected boolean overideErrMsg = false;
	protected int errNum;
	protected String errMsg = "";
	protected T retData = null;

	protected String actual;
	protected String expected;

	protected JsonBase() {
		errNum = Err.OK.num;
		errMsg = Err.OK.msg;
		retData = null;
	}

	public JsonBase(Err err) {
		errNum = err.num;
		errMsg = err.msg;
		retData = null;
	}

	public JsonBase(Err err, T retData) {
		errNum = err.num;
		errMsg = err.msg;
		this.retData = retData;
	}

	public JsonBase(String okMsg) {
		errNum = Err.OK.num;
		if(okMsg != null) {
			errMsg = okMsg;
		} else {
			errMsg = Err.OK.msg;
		}
		actual = okMsg;
		retData = null;
	}


	public abstract boolean isOk();


	@SuppressWarnings("unchecked")
	public S setErr(Err err) {
		errNum = err.num;
		errMsg = err.msg;
		return (S)this;
	}

	@SuppressWarnings("unchecked")
	public S setErr(Err err, String actual) {
		errNum = err.num;
		errMsg = err.msg;
		this.actual = actual;
		return (S)this;
	}

	@SuppressWarnings("unchecked")
	public S setErr(Err err, String actual, String expected) {
		errNum = err.num;
		errMsg = err.msg;
		this.actual = actual;
		this.expected = expected;
		return (S)this;
	}

	@SuppressWarnings("unchecked")
	public S setErrMsgOverride(String errMsg) {
//		if(errNum == 0) {
//			errNum = Err.E1000.num;
//		}
		this.errMsg = errMsg;
		this.overideErrMsg = true;
		return (S)this;
	}

	@SuppressWarnings("unchecked")
	public S setMsgActual(String actual) {
		this.actual = actual;
		return (S)this;
	}

	@SuppressWarnings("unchecked")
	public S setMsgExpected(String expected) {
		this.expected = expected;
		return (S)this;
	}

	@SuppressWarnings("unchecked")
	public S setRetData(T retData) {
		//leave subclass to implementate
		this.retData = retData;
		return (S)this;
	}

	public T getRetData() {
		return this.retData;
	}

	public int getErrNum() {
		return errNum;
	}
	public String getErrMsg() {
		return errNum == OK && actual != null ? actual:this.toString();
	}


	@Override
	public String toString() {
		if(overideErrMsg) {
			return errMsg;
		}
		StringBuilder sb = new StringBuilder("[");
		sb.append(errNum).append("] ").append(errMsg);
		if(actual!=null) {
			sb.append(" [当前] ").append(actual);
		}
		if(expected != null) {
			sb.append(" [期望] ").append(expected);
		}
		return sb.toString();
	}
}
