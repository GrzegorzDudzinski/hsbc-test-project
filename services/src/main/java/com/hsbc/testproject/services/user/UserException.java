package com.hsbc.testproject.services.user;

public class UserException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8283690075493940155L;


	public UserException(String message) {
		super(message);
	}


	public UserException(String message, Throwable cause) {
		super(message, cause);
	}

}
