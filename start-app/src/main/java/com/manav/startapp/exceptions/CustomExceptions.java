package com.manav.startapp.exceptions;





public class CustomExceptions {
	
	// Expected Exceptions During Registration
	
	public class DuplicateUsernameException extends Exception {
		public DuplicateUsernameException() {
			super("That username is taken. Try another.");
		}

		public DuplicateUsernameException(String msg) {
			super(msg);
		}
	}
	

	


	//Expected Exceptions During Login

	

	

	//Expected Exceptions During Fetching of User-Detail
	


	//Expected Exceptions During Fetching of User Fund Transfer

	


	

}
