package com.manav.startapp.exceptions;

public class InvalidLoginException extends Exception {
	public InvalidLoginException() {
		super("Login is invalid.");
		System.out.println("Login is invalid.");
	}

	public InvalidLoginException(String msg) {
		super(msg);
		System.out.println(msg);
	}
}
