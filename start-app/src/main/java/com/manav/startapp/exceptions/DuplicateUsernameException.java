package com.manav.startapp.exceptions;

public class DuplicateUsernameException extends Exception {
	public DuplicateUsernameException() {
		super("That username is taken. Try another.");
		System.out.println("That username is taken. Try another.");
	}

	public DuplicateUsernameException(String msg) {
		super(msg);
		System.out.println(msg);
	}
}
