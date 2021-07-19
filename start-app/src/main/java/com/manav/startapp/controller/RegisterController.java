package com.manav.startapp.controller;

import com.manav.startapp.exceptions.*;
import com.manav.startapp.services.GenerateTokenUserPass;
import com.manav.startapp.services.LoginCred;
import com.manav.startapp.services.RetUserDTO;
import com.manav.startapp.services.TransferFund;
import com.manav.startapp.services.UserDTO;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.manav.startapp.dao.UserRepository;
import com.manav.startapp.entities.User;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

@RestController

public class RegisterController {
	@Autowired
	UserRepository repo;

	@PostMapping(value = "register", consumes = { "application/json" })
	public ResponseEntity<?> register(@RequestBody UserDTO user) {
		System.out.println("user [UserName=" + user.username + " address=" + user.address + ", mobile=" + user.mobile
				+ ", email=" + user.email + "]");
		try {
			if ((user.username.length() < 8) || (!user.username.matches("[a-zA-Z0-9]+"))) {
				throw new InvalidPasswordLengthException("Either length of usename is not 8 or is not alphanumeric");
			}
		} catch (InvalidPasswordLengthException e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());

		}
		try {
			String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\."+
                    "[a-zA-Z0-9_+&*-]+)*@" +
                    "(?:[a-zA-Z0-9-]+\\.)+[a-z" +
                    "A-Z]{2,7}$";
                      
Pattern pat = Pattern.compile(emailRegex);

if(!pat.matcher(user.email).matches()) {
	throw new InvalidPasswordLengthException("Given email address is not in proper format..");
}

		}
		catch(InvalidPasswordLengthException e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
		try {
			if (repo.findByUsername(user.username) != null) {
				throw new DuplicateUsernameException();
			}
		} 
		
		catch (DuplicateUsernameException e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		} finally {
			System.out.println(user.username);

		}
		try {
			if (user.password.length() != 6) {
				throw new InvalidPasswordLengthException();
			}
		} catch (InvalidPasswordLengthException e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		} finally {
			System.out.println(user.password);
		}
		User regtdUser = new User(user);

		User saveUser = repo.save(regtdUser);
		Map<String, String> obj = new HashMap<>();
		obj.put("Response", "Registration successful");

		return ResponseEntity.status(HttpStatus.OK).body(obj);

	}

	@PostMapping(value = "login", consumes = { "application/json" })
	public ResponseEntity<?> login(@RequestBody LoginCred user) {

		User Login = repo.findByUsername(user.getUsername());

		try {
			if (Login == null)
				throw new UsernameNotFoundException();
		} catch (UsernameNotFoundException e) {
			Map<String, String> response = new HashMap<>();
			response.put("Response", e.getMessage());
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);

		}

		try {
			if (!user.getPassword().equals(Login.getPassword()))
				throw new InvalidCredentialsException();
		} catch (InvalidCredentialsException e) {
			Map<String, String> response = new HashMap<>();
			response.put("Response", e.getMessage());
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);

		}
		GenerateTokenUserPass generator = new GenerateTokenUserPass();
		String token = "Bearer " + (generator.GenerateTokenUserPass1(user.getUsername(), user.getPassword()));
		Login.setToken(token.toString());
		Login.setDateTime(LocalDateTime.now().plusHours(1));
		repo.save(Login);
		Map<String, String> loginToken = new HashMap<>();
		loginToken.put("loginToken", token);
		return ResponseEntity.status(HttpStatus.OK).body(loginToken);
	}

	@GetMapping("user-detail")
	public ResponseEntity<?> userDetail(@RequestHeader(value = "Authorization") String token) {
		User user_det = repo.findByToken(token);
		try {
			if (user_det == null || LocalDateTime.now().isAfter(user_det.getDateTime())) {
				throw new InvalidLoginException();
			}
			RetUserDTO retUser = new RetUserDTO(user_det);
			return ResponseEntity.status(HttpStatus.OK).body(retUser);
		} catch (InvalidLoginException e) {
			e.printStackTrace();
			Map<String, String> response = new HashMap<>();
			response.put("Response", e.getMessage());

			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
		}
	}

	@PostMapping(value = "fund-transfer", consumes = { "application/json" })

	public ResponseEntity<?> fundTransfer(@RequestHeader(value = "Authorization") String token,
			@RequestBody TransferFund transfer) {
		System.out.println(transfer.getTransfer_amount() + " " + transfer.getBeneficiary_acc_number());
		User remmiter = repo.findByToken(token);
		System.out.println(token);
		System.out.println(remmiter);
		if (remmiter == null || remmiter.getDateTime().isBefore(LocalDateTime.now())) {
			Map<String, String> response = new HashMap<>();
			response.put("Response", new InvalidLoginException("Your Token has expired!.. ").getMessage());
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
		}
		try {
			if ((remmiter.getAmount() < transfer.getTransfer_amount())) {
				System.out.println(remmiter.getAmount() + " " + transfer.getTransfer_amount());
				throw new NotEnoughFundsAvailableException();
			}
			User receiver = repo.findByAccountNumber(transfer.getBeneficiary_acc_number());
			if (receiver == null) {
				throw new NullPointerException("No account matched with the beneficiary account Number...");

			}
			if(receiver.getAccountNumber()==remmiter.getAccountNumber()) {
				throw new SenderReceiverSameException("Amount can not be transferred into your own account..");
			}
			remmiter.setAmount(remmiter.getAmount() - transfer.getTransfer_amount());
			receiver.setAmount(receiver.getAmount() + transfer.getTransfer_amount());
			repo.save(remmiter);
			repo.save(receiver);
			Map<String, Float> RemAmount = new HashMap<>();
			RemAmount.put("Remaining Balance", remmiter.getAmount());

			return ResponseEntity.status(HttpStatus.OK).body(RemAmount);
		} catch (NullPointerException e) {
			e.printStackTrace();
			Map<String, String> response = new HashMap<>();
			response.put("Response", e.getMessage());
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
		} catch (NotEnoughFundsAvailableException e) {
			e.printStackTrace();
			Map<String, String> response = new HashMap<>();
			response.put("Response", e.getMessage());
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
		}catch (SenderReceiverSameException e) {
			e.printStackTrace();
			Map<String, String> response = new HashMap<>();
			response.put("Response", e.getMessage());
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
		}

	}

}
