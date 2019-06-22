package com.example.SSOT.models.rest;

import org.springframework.http.HttpStatus;

public abstract class ResponseJSON {
	public HttpStatus httpStatus;
	public ResponseJSON(HttpStatus httpStatus) {
		super();
		this.httpStatus = httpStatus;
	}
	public HttpStatus getHttpStatus() {
		return httpStatus;
	}
}
