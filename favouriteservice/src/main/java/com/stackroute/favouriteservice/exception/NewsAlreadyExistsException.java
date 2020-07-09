package com.stackroute.favouriteservice.exception;

/**
 * @author Zaithoon M
 *
 */
public class NewsAlreadyExistsException extends Exception {

	private String message;

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public NewsAlreadyExistsException(String message) {
		super();
		this.message = message;
	}

	@Override
	public String toString() {
		return "NewsAlreadyExistsException [message=" + message + "]";
	}
	
	
	
}
