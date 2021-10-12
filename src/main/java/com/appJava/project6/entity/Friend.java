package com.appJava.project6.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "Friends")
public class Friend {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long friendId;

	@Column(name = "FrirstName")
	private String firstName;

	@Column(name = "Email")
	private String email;

	@ManyToOne
	@JoinColumn(name = "User_id", nullable = false)
	private User user;

	public Friend() {
	}

	public Friend(String firstName, String email) {
		super();
		this.firstName = firstName;
		this.email = email;
	}

	public Long getFriendId() {
		return friendId;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String fistName) {
		this.firstName = fistName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}
