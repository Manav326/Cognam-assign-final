# Cognam-assign
Part 1. Mandatory assignment User onboading:
********************************************

Registration:
-------------
*. Create an user registration API [POST type].
	that takes the follwings as json or formdata body type: 
	username(unique, 8 characters, alphanumeric), pass(6 characters), address, mobile, email
*. Register at least two users.
	default: 	bank_name(HDFC), available_balance(initialize with 5000)
	auto_generate:	10 digit acc_number(unique)
*. If the username already exists in the db, create a custom exception: DuplicateUsernameExcepion and show "That username is taken. Try another." message on the API json response with a valid http status code.
*. Validate password length, if not 6 characters then create a custom exception: InvalidPasswordLengthException and show "The password should be 6 characters long." message on the API json response with a valid http status code.
*. Store in the db.
*. If data is successfully stored, Show msg: "Registration successful" on the API json reponse.
*. If any exception occurs while storing the data in the db, create a custom exception: InternalProcessException and show "System error" message on the API json reponse.

Login:
-------------
*. Create an user login API [POST type] that takes username, password as form body or json.
*. If username does not exist in the db, create a custom exception: UsernameNotFoundException and show "username not found" message on the API json response with a valid http status code.
*. If username exists but the given password is incorrect, create a custom exception: InvalidCredentialsException and show "invalid credentials" message on the API json response with a valid http status code.
*. On successful login returns an unique generated loginToken in the API response (that you can manage on the application level).

User Details:
-------------
*. Create an API [GET type] that takes that loginToken as input in the header and after validating the token it returns user details on the API json response, And if the loginToken is invalid then throw a custom exception InvalidLoginException with a message "Login is invalid.".


***********************************************
Part 2. Optional assignment User fund transfer:
***********************************************

*. Create a fund_transfer API [POST type] that takes that loginToken as input in the header and beneficiary_acc_number, transfer_amount in the body and do the following:
	- Fetch available_balance of associated user with loginToken.
	- If transfer_amount > available_balance, create a custom exception: NotEnoughFundsAvailableException and show "not enough funds available" message on the API json response.
	- If transfer_amount <= available_balance, deduct transfer_amount from the available_balance of the sender and update in the db.
	- Fetch beneficiary details from the beneficiary_acc_number.
	- Add the transfer_amount to the beneficiary available_balance and update in the db.
	- Finally return the user remaining balance in the success reponse on the json of API.
