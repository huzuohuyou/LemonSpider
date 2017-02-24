package com.lemon.exception;

import org.apache.shiro.authc.DisabledAccountException;

public class ErrorStatusException extends DisabledAccountException {

	private static final long serialVersionUID = -1896823851671662076L;

	/**
	 * Creates a new ErrorStatusException.
	 */
	public ErrorStatusException() {
		super();
	}

	/**
	 * Constructs a new ErrorStatusException.
	 *
	 * @param message
	 *            the reason for the exception
	 */
	public ErrorStatusException(String message) {
		super(message);
	}

	/**
	 * Constructs a new ErrorStatusException.
	 *
	 * @param cause
	 *            the underlying Throwable that caused this exception to be
	 *            thrown.
	 */
	public ErrorStatusException(Throwable cause) {
		super(cause);
	}

	/**
	 * Constructs a new ErrorStatusException.
	 *
	 * @param message
	 *            the reason for the exception
	 * @param cause
	 *            the underlying Throwable that caused this exception to be
	 *            thrown.
	 */
	public ErrorStatusException(String message, Throwable cause) {
		super(message, cause);
	}
}
