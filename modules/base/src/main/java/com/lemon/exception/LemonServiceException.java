package com.lemon.exception;

public class LemonServiceException extends Exception{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public LemonServiceException(String msg) {
		super(msg);
	}
	
	@Override
	public Throwable fillInStackTrace() {
		return this;
	}

}
