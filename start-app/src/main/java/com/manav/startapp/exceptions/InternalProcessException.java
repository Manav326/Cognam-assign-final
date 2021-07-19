package com.manav.startapp.exceptions;

public class InternalProcessException extends Exception {
	public InternalProcessException() {
		super("System error");
		System.out.println("System error");
	}

	public InternalProcessException(String msg) {
		super(msg);
		System.out.println(msg);
	}
}
