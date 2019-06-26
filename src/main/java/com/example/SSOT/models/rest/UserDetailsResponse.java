package com.example.SSOT.models.rest;

import java.util.UUID;

import org.springframework.http.HttpStatus;

import com.example.SSOT.models.User;

public class UserDetailsResponse extends ResponseJSON{
	private String message;
	public User user;
	private UUID userID;
	public UserDetailsResponse(HttpStatus httpStatus, String message, User user) {
		super(httpStatus);
		this.message = message;
		if (user != null) {
			this.user = user;
			this.userID = user.getUserId();
		}
		else {
			this.user = null;
			this.userID = null;
		}
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public User getUser() {
		return this.user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public UUID getUserID() {
		return userID;
	}
	public void setUserID(UUID userID) {
		this.userID = userID;
	}
}