package com.manav.startapp.exceptions;

public class InvalidCredentialsException extends Exception {
	public InvalidCredentialsException() {
		super("invalid credentials");
		System.out.println("invalid credentials");
	}

	public InvalidCredentialsException(String msg) {
		super(msg);
		System.out.println(msg);
	}
}
