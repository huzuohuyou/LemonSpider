package com.lemon.exception;

public class LemonException extends Exception {

	private static final long serialVersionUID = -1623103344824534840L;
	private String exceptionCode;

	public String getExceptionCode() {
		return "2" + exceptionCode;
	}

	public void setExceptionCode(String exceptionCode) {
		this.exceptionCode = exceptionCode;
	}

	public LemonException() {
		super();
	}

	public LemonException(String errMsg) {
		super(errMsg);
	}

	public LemonException(String message, Throwable cause, String code) {
		super(message, cause);
		this.exceptionCode = code;
	}

	public LemonException(String message, String code) {
		super(message);
		this.exceptionCode = code;
	}

	public LemonException(Throwable cause, String code) {
		super(cause);
		this.exceptionCode = code;
	}

}