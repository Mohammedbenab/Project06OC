package com.appJava.project6.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "Transaction")
public class Transaction {

	@Id
	@GeneratedValue
	private Long transactionId;

	@Column(name = "Transac_Date")
	private LocalDateTime transacDate;

	@Column(name = "Amount_Tranc")
	private double amountTransac;

	@Column(name = "Cible_Transac")
	private String nameTransacCible;

	@Column(name = "Description_Transac")
	private String description;

	@ManyToOne
	@JoinColumn(name = "User_id", nullable = false)
	private User user;

	@ManyToOne
	@JoinColumn(name = "UserCible_id", nullable = false)
	private User userCible;

	public Transaction() {

	}

	public Transaction(double amountTransac, String nameTransacCible, String description) {
		super();
		this.amountTransac = amountTransac;
		this.nameTransacCible = nameTransacCible;
		this.description = description;
	}

	public Long getTransactionId() {
		return transactionId;
	}

	public LocalDateTime getTransacDate() {
		return transacDate;
	}

	public void setTransacDate(LocalDateTime transacDate) {
		this.transacDate = transacDate;
	}

	public double getAmountTransac() {
		return amountTransac;
	}

	public void setAmountTransac(double amountTransac) {
		this.amountTransac = amountTransac;
	}

	public String getNameTransacCible() {
		return nameTransacCible;
	}

	public void setNameTransacCible(String nameTransacCible) {
		this.nameTransacCible = nameTransacCible;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public User getUserCible() {
		return userCible;
	}

	public void setUserCible(User userCible) {
		this.userCible = userCible;
	}

	public void setTransactionId(Long transactionId) {
		this.transactionId = transactionId;
	}

}
