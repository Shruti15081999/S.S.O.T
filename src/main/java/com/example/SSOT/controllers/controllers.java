package com.example.SSOT.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class controllers {

	@RequestMapping(value = "/helloworld")
	public ResponseEntity<String> helloWorld() {
		System.out.println("hello");
		return new ResponseEntity<String>("Hello World", HttpStatus.OK);
	}

	@RequestMapping(value = "/")
	public ResponseEntity<String> helloWorldNew() {
		System.out.println("blank");
		return new ResponseEntity<String>("Hello World New", HttpStatus.OK);
	}
}
