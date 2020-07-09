package com.stackroute.userservice.exception;

/**
 * @author Zaithoon M
 *
 */
@SuppressWarnings("serial")
public class UserAlreadyExistsException extends Exception{

	private String message;

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public UserAlreadyExistsException(String message) {
		super();
		this.message = message;
	}

	@Override
	public String toString() {
		return "UserAlreadyExistsException [message=" + message + "]";
	}
	
	
	
}
