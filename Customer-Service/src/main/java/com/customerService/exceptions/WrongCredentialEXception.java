package com.customerService.exceptions;

public class WrongCredentialEXception extends RuntimeException {

	public WrongCredentialEXception(String message, Throwable cause) {
		super(message, cause);
	}

	public WrongCredentialEXception(String message) {
		super(message);

	}

	public WrongCredentialEXception(Throwable cause) {
		super(cause);

	}

	public WrongCredentialEXception() {
		// TODO Auto-generated constructor stub
	}
}
