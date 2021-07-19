package com.manav.startapp.entities;

import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.Session;

import com.manav.startapp.services.GenerateAccount;
import com.manav.startapp.services.UserDTO;

@Entity
public class User {

	@Id
	private long accountNumber;
	private String username;
	private String password;
	private String address;
	private Long mobile;
	private String email;
	private String bank = "HDFC";

	private float amount = 5000;
	private String token = null;
	private LocalDateTime DateTime =LocalDateTime.now().plusHours(1);

	public User() {

	}

	public User(UserDTO user) {
		this.username = user.username;
		this.password = user.password;
		this.address = user.address;
		this.mobile = user.mobile;
		this.email = user.email;
		this.accountNumber = GenerateAccount.getAccount();

	}
	

	public long getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(int accountNumber) {
		this.accountNumber = accountNumber;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Long getMobile() {
		return mobile;
	}

	public void setMobile(Long mobile) {
		this.mobile = mobile;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getBank() {
		return bank;
	}

	public void setBank(String bank) {
		this.bank = bank;
	}

	public float getAmount() {
		return amount;
	}

	public void setAmount(float amount) {
		this.amount = amount;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public LocalDateTime getDateTime() {
		return DateTime;
	}

	public void setDateTime(LocalDateTime dateTime) {
		DateTime = dateTime;
	}

}
