package com.appJava.project6.dto;

public class FriendDto {

	private String firstname;
	private String email;

	public FriendDto() {

	}

	public FriendDto(String firstname, String email) {
		super();
		this.firstname = firstname;
		this.email = email;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

}
