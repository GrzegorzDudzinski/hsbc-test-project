package com.hsbc.testproject.rest.exceptions;

public class ErrorResult {

	private String exceptionType;
	private String exceptionMessage;
	
	public ErrorResult(Throwable t) {
		super();
		this.exceptionType    = t.getClass().getSimpleName();
		this.exceptionMessage = t.getMessage();
	}
	
	public String getExceptionType() {
		return exceptionType;
	}
	public void setExceptionType(String exceptionType) {
		this.exceptionType = exceptionType;
	}
	public String getExceptionMessage() {
		return exceptionMessage;
	}
	public void setExceptionMessage(String exceptionMessage) {
		this.exceptionMessage = exceptionMessage;
	}
	
	

}
