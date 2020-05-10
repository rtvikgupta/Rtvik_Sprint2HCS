package com.capgemini.rtvik.healthcare.exceptions;

public class UserNotFound extends RuntimeException {
	
	public UserNotFound(String msg) {
		super(msg);
	}
}
