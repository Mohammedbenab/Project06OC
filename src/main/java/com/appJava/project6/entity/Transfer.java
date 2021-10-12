package com.appJava.project6.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "Money_transfer")
public class Transfer {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long transfertId;

	@Column(name = "Amount_transfer")
	private double amountTransf;

	@Column(name = "Date_transfer")
	private LocalDateTime dateTransfer;

	@ManyToOne
	@JoinColumn(name = "userId", nullable = false)
	private User user;

	@ManyToOne
	@JoinColumn(name = "BankAccount_Id", nullable = false)
	private BankAccount bankAccount;

	public Transfer() {

	}

	public Transfer(double amountTransf, User user, BankAccount bankAccount) {
		super();
		this.amountTransf = amountTransf;
		this.user = user;
		this.bankAccount = bankAccount;
	}

	public Long getTransfertId() {
		return transfertId;
	}

	public void setTransfertId(Long transfertId) {
		this.transfertId = transfertId;
	}

	public double getAmountTransf() {
		return amountTransf;
	}

	public void setAmountTransf(double amountTransf) {
		this.amountTransf = amountTransf;
	}

	public LocalDateTime getDateTransfer() {
		return dateTransfer;
	}

	public void setDateTransfer(LocalDateTime dateTransfer) {
		this.dateTransfer = dateTransfer;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public BankAccount getBankAccount() {
		return bankAccount;
	}

	public void setBankAccount(BankAccount bankAccount) {
		this.bankAccount = bankAccount;
	}

}
