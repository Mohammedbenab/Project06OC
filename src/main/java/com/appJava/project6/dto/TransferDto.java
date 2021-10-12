package com.appJava.project6.dto;

public class TransferDto {

	private String bankAccountNumber;
	private double amountTransfer;

	public TransferDto() {

	}

	public TransferDto(String bankAccountNumber, double amountTransfer) {
		super();
		this.bankAccountNumber = bankAccountNumber;
		this.amountTransfer = amountTransfer;
	}

	public String getBankAccountNumber() {
		return bankAccountNumber;
	}

	public void setBankAccountNumber(String bankAccountNumber) {
		this.bankAccountNumber = bankAccountNumber;
	}

	public double getAmountTransfer() {
		return amountTransfer;
	}

	public void setAmountTransfer(double amountTransfer) {
		this.amountTransfer = amountTransfer;
	}

}
