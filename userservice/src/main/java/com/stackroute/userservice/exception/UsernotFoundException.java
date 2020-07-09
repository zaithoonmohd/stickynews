package com.stackroute.userservice.exception;

/**
 * @author Zaithoon M
 *
 */
@SuppressWarnings("serial")
public class UsernotFoundException extends Exception{

	private String message;

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public UsernotFoundException(String message) {
		super();
		this.message = message;
	}

	@Override
	public String toString() {
		return "UsernotFoundException [message=" + message + "]";
	}
	
}
