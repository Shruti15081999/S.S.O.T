package com.example.SSOT.models.rest;

import org.springframework.http.HttpStatus;

public class LoginResponse extends ResponseJSON {
	private String message;
	public LoginResponse(HttpStatus httpStatus, String message) {
		super(httpStatus);
		this.message = message;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
}
