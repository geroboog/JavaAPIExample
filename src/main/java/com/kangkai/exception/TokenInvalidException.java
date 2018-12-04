package com.kangkai.exception;

/**
 * token失效异常
 * 
 * @author weidoukeji
 *
 */
public class TokenInvalidException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5451698428149075637L;
	public String message;

	public TokenInvalidException() {
		super();
	}

	public TokenInvalidException(String message) {
		super();
		this.message = message;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
