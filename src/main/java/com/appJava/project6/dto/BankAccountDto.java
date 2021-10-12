package com.appJava.project6.dto;

public class BankAccountDto {

	private String accountNumber;

	public BankAccountDto() {

	}

	public BankAccountDto(String accountNumber) {
		super();
		this.accountNumber = accountNumber;
	}

	public String getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}

}
