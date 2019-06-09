package com.example.SSOT.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.example.SSOT.models.rest.NewUserRequest;
import com.example.SSOT.models.rest.NewUserResponse;
import com.example.SSOT.models.rest.ResponseJSON;
import com.example.SSOT.services.UserService;

@Controller
public class controllers {
	@GetMapping(value = "/")
	public ResponseEntity<String> helloWorldNew() {
		return new ResponseEntity<String>("Hello World New", HttpStatus.OK);
	}
	@PostMapping(value = "/api/user/adduser")
	public ResponseEntity<ResponseJSON> addUser(@RequestBody NewUserRequest newUserRequest) {
		NewUserResponse newUserResponse = (NewUserResponse) UserService.addUser(newUserRequest);
		return new ResponseEntity<ResponseJSON>(newUserResponse, newUserResponse.getHttpStatus());
	}
}
