package com.manav.startapp.exceptions;

public class SenderReceiverSameException extends Exception {
	public SenderReceiverSameException(){
		super("Sender and Receiver are having with same account Number..");
		System.out.println("Sender and Receiver are having with same account Number..");
	}
	public SenderReceiverSameException(String msg){
		super(msg);
		System.out.println(msg);
	}

}
