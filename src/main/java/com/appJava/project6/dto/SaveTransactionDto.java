package com.appJava.project6.dto;

public class SaveTransactionDto {

	private String email;
	private String motif;
	private double amount;

	public SaveTransactionDto() {

	}

	public SaveTransactionDto(String email, String motif, double amount) {
		super();
		this.email = email;
		this.motif = motif;
		this.amount = amount;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getMotif() {
		return motif;
	}

	public void setMotif(String motif) {
		this.motif = motif;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

}
