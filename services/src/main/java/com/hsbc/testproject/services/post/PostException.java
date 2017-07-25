package com.hsbc.testproject.services.post;

public class PostException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8283690075493940155L;


	public PostException(String message) {
		super(message);
	}


	public PostException(String message, Throwable cause) {
		super(message, cause);
	}

}
