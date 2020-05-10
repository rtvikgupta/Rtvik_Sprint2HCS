package com.capgemini.rtvik.healthcare.exceptions;

public class AppointmentNotFound extends RuntimeException {

	public AppointmentNotFound(String msg) {
		super(msg);
	}
}
