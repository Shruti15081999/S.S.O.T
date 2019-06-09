package com.example.SSOT.services;

import org.springframework.http.HttpStatus;

import com.example.SSOT.exceptions.NullValueException;
import com.example.SSOT.models.User;
import com.example.SSOT.models.Users;
import com.example.SSOT.models.rest.NewUserRequest;
import com.example.SSOT.models.rest.NewUserResponse;
import com.example.SSOT.models.rest.ResponseJSON;

public class UserService {
	public static ResponseJSON addUser(NewUserRequest newUserRequest) {
		try {
			if (newUserRequest.getFirstName() == null) {
				throw new NullValueException("No First Name provided");
			} else if (newUserRequest.getLastName() == null) {
				throw new NullValueException("No Last Name provided");
			}
			User user = new User(newUserRequest.getFirstName(), newUserRequest.getLastName());
			Users.addUser(user);
			System.out.println(user);
			return new NewUserResponse(HttpStatus.OK, "new user created", user.getUserId());
		} catch(Exception e) {
			System.out.println(e);
			if (e instanceof NullValueException) {
				return new NewUserResponse(HttpStatus.BAD_REQUEST, e.getMessage(), null);
			} else {
				return new NewUserResponse(HttpStatus.INTERNAL_SERVER_ERROR, "Some error occured", null);
			}
		}
	}
}
