package com.example.SSOT.services;

import java.util.UUID;

import org.springframework.http.HttpStatus;

import com.example.SSOT.exceptions.NullValueException;
import com.example.SSOT.exceptions.UserAlreadyExists;
import com.example.SSOT.exceptions.UserNotFoundException;
import com.example.SSOT.models.User;
import com.example.SSOT.models.UserCredentials;
import com.example.SSOT.models.Users;
import com.example.SSOT.models.rest.DeleteUserRequest;
import com.example.SSOT.models.rest.DeleteUserResponse;
import com.example.SSOT.models.rest.EditUserRequest;
import com.example.SSOT.models.rest.EditUserResponse;
import com.example.SSOT.models.rest.LoginRequest;
import com.example.SSOT.models.rest.LoginResponse;
import com.example.SSOT.models.rest.NewUserRequest;
import com.example.SSOT.models.rest.NewUserResponse;
import com.example.SSOT.models.rest.UserDetailsResponse;

public class UserService {
	public static UserDetailsResponse getUserDetails(String email) {
		try {
			if (email == null) {
				throw new NullValueException("No Email provided");
			} else if(!Users.getUserList().containsKey(email)) {
				throw new UserNotFoundException("No User with email " + email + "exists");
			}
			User user = Users.getUser(email);
			return new UserDetailsResponse(HttpStatus.OK, "User Details with email " + email, user);
		} catch(Exception e) {
			System.out.println(e);
			if (e instanceof NullValueException) {
				return new UserDetailsResponse(HttpStatus.BAD_REQUEST, e.getMessage(), null);
			} else if (e instanceof UserNotFoundException) {
				return new UserDetailsResponse(HttpStatus.NOT_FOUND, e.getMessage(), null);
			} else {
				return new UserDetailsResponse(HttpStatus.INTERNAL_SERVER_ERROR, "Some error occured", null);
			}
		}
	}

	public static NewUserResponse addUser(NewUserRequest newUserRequest) {
		try {
			if (newUserRequest.getFirstName() == null) {
				throw new NullValueException("No First Name provided");
			} else if (newUserRequest.getLastName() == null) {
				throw new NullValueException("No Last Name provided");
			} else if (newUserRequest.getEmail() == null) {
				throw new NullValueException("No Email provided");
			} else if (newUserRequest.getLocationName() == null) {
				throw new NullValueException("No location provided");
			} else if (Users.getUserList().containsKey((newUserRequest.getEmail()))) {
				throw new UserAlreadyExists("User with email " + newUserRequest.getEmail() + " already exists");
			}
			User user = new User(newUserRequest.getFirstName(), newUserRequest.getLastName(), newUserRequest.getEmail(), newUserRequest.getLocationName());
			Users.addUser(user);
			UserCredentials.addCredentials(newUserRequest.getEmail(), newUserRequest.getPassword());
			System.out.println(user);
			return new NewUserResponse(HttpStatus.OK, "User created with email " + newUserRequest.getEmail(), user.getUserId());
		} catch(Exception e) {
			System.out.println(e);
			if (e instanceof NullValueException || e instanceof UserAlreadyExists) {
				return new NewUserResponse(HttpStatus.BAD_REQUEST, e.getMessage(), null);
			} else {
				return new NewUserResponse(HttpStatus.INTERNAL_SERVER_ERROR, "Some error occured", null);
			}
		}
	}

	public static DeleteUserResponse deleteUser(DeleteUserRequest deleteUserRequest) {
		try {
			if (deleteUserRequest.getEmail() == null) {
				throw new NullValueException("No Email provided");
			} else if(!Users.getUserList().containsKey(deleteUserRequest.getEmail())) {
				throw new UserNotFoundException("No User with email " + deleteUserRequest.getEmail() + " exists");
			}
			User user = Users.getUser(deleteUserRequest.getEmail());
			System.out.println(user);
			String email = user.getEmail();
			UUID userId = user.getUserId();
			UserCredentials.removeCredentials(email);
			Users.deleteUser(email);
			user = null;
			return new DeleteUserResponse(HttpStatus.OK, "User deleted associated to email " + email, userId);
		} catch(Exception e) {
			System.out.println(e);
			if (e instanceof NullValueException) {
				return new DeleteUserResponse(HttpStatus.BAD_REQUEST, e.getMessage(), null);
			} else if (e instanceof UserNotFoundException) {
				return new DeleteUserResponse(HttpStatus.NOT_FOUND, e.getMessage(), null);
			} else {
				return new DeleteUserResponse(HttpStatus.INTERNAL_SERVER_ERROR, "Some error occured", null);
			}
		}
	}

	public static EditUserResponse editUser(EditUserRequest editUserRequest) {
		try {
			if (editUserRequest.getEmail() == null) {
				throw new NullValueException("No Email provided");
			} else if(!Users.getUserList().containsKey(editUserRequest.getEmail())) {
				throw new UserNotFoundException("No User with email " + editUserRequest.getEmail() + " exists");
			}
			User user = Users.getUser(editUserRequest.getEmail());
			System.out.println(user);
			if (editUserRequest.getEmail() != null) {
				user.setEmail(editUserRequest.getEmail());
			}
			if (editUserRequest.getFirstName() != null) {
				user.setFirstName(editUserRequest.getFirstName());
			}
			if (editUserRequest.getLastName() != null) {
				user.setLastName(editUserRequest.getLastName());
			}
			if (editUserRequest.getAddress() != null) {
				user.setAddress(editUserRequest.getAddress());
			}
			return new EditUserResponse(HttpStatus.OK, "User edited associated to email " + user.getEmail(), user.getUserId());
		} catch(Exception e) {
			System.out.println(e);
			if (e instanceof NullValueException) {
				return new EditUserResponse(HttpStatus.BAD_REQUEST, e.getMessage(), null);
			} else if (e instanceof UserNotFoundException) {
				return new EditUserResponse(HttpStatus.NOT_FOUND, e.getMessage(), null);
			} else {
				return new EditUserResponse(HttpStatus.INTERNAL_SERVER_ERROR, "Some error occured", null);
			}
		}
	}

	public static LoginResponse login(LoginRequest loginRequest) {
		try {
			if (loginRequest.getEmail() == null) {
				throw new NullValueException("No Email provided");
			} else if(!Users.getUserList().containsKey(loginRequest.getEmail())) {
				throw new UserNotFoundException("No User with email " + loginRequest.getEmail() + " exists");
			}
			boolean valid = UserCredentials.login(loginRequest.getEmail(), loginRequest.getPassword());
			if (valid) {
				return new LoginResponse(HttpStatus.OK, "Welcome");
			} else {
				return new LoginResponse(HttpStatus.UNAUTHORIZED, "Incorrect credentials");
			}
		} catch(Exception e) {
			System.out.println(e);
			if (e instanceof NullValueException) {
				return new LoginResponse(HttpStatus.BAD_REQUEST, e.getMessage());
			} else if (e instanceof UserNotFoundException) {
				return new LoginResponse(HttpStatus.NOT_FOUND, e.getMessage());
			} else {
				return new LoginResponse(HttpStatus.INTERNAL_SERVER_ERROR, "Some error occured");
			}
		}
	}
}
