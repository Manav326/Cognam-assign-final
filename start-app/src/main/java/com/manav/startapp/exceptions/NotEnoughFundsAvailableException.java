package com.manav.startapp.exceptions;

public class NotEnoughFundsAvailableException extends Exception {
	public NotEnoughFundsAvailableException() {
		super("not enough funds available");
		System.out.println("not enough funds available");
	}

	public NotEnoughFundsAvailableException(String msg) {
		super(msg);
		System.out.println(msg);
	}
}
