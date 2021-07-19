package com.manav.startapp.exceptions;

public class InvalidPasswordLengthException extends Exception {
	public InvalidPasswordLengthException() {
		super("The password should be 6 characters long.");
		System.out.println("The password should be 6 characters long.");
	}

	public InvalidPasswordLengthException(String msg) {
		super(msg);
		System.out.println(msg);
	}
}
