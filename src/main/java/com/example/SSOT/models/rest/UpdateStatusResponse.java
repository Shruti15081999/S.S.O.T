package com.example.SSOT.models.rest;

import java.util.UUID;

import org.springframework.http.HttpStatus;

public class UpdateStatusResponse extends ResponseJSON{
	private String message;
	private UUID userID;
	public UpdateStatusResponse(HttpStatus httpStatus, String message, UUID userID) {
		super(httpStatus);
		this.message = message;
		this.userID = userID;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public UUID getUserID() {
		return userID;
	}
	public void setUserID(UUID userID) {
		this.userID = userID;
	}
}
