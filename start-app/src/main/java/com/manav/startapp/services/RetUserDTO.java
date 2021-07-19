package com.manav.startapp.services;

import java.time.LocalDateTime;

import com.manav.startapp.entities.User;

public class RetUserDTO {

	private long accountNumber;
	private String username;
	public long getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(long accountNumber) {
		this.accountNumber = accountNumber;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
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

	public LocalDateTime getDateTime() {
		return DateTime;
	}

	public void setDateTime(LocalDateTime dateTime) {
		DateTime = dateTime;
	}

	private String address;
	private Long mobile;
	private String email;
	private String bank;

	private float amount;
	

	private LocalDateTime DateTime =null;

	public RetUserDTO(User user) {
		this.accountNumber = user.getAccountNumber();
		this.username = user.getUsername();
		this.address = user.getAddress();
		this.mobile = user.getMobile();
		this.email = user.getEmail();
		this.bank = user.getBank();
		this.amount = user.getAmount();
		this.DateTime=user.getDateTime();
	}
}
