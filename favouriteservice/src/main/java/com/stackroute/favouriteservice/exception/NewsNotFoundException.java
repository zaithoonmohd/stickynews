package com.stackroute.favouriteservice.exception;

/**
 * @author Zaithoon M
 *
 */
public class NewsNotFoundException extends Exception {

	private String message;

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public NewsNotFoundException(String message) {
		super();
		this.message = message;
	}

	@Override
	public String toString() {
		return "NewsNotFoundException [message=" + message + "]";
	}
	
}
