package com.manav.startapp.exceptions;

public class UsernameNotFoundException extends Exception{
	public UsernameNotFoundException(){
		super("username not found");
		System.out.println("username not found");
	}
	public UsernameNotFoundException(String msg){
		super(msg);
		System.out.println(msg);
	}
}
