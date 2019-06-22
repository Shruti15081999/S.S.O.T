package com.example.SSOT.services;

import org.springframework.http.HttpStatus;

import com.example.SSOT.exceptions.NullValueException;
import com.example.SSOT.exceptions.UserNotFoundException;
import com.example.SSOT.models.User;
import com.example.SSOT.models.Users;
import com.example.SSOT.models.rest.AddEmergencyContactRequest;
import com.example.SSOT.models.rest.AddEmergencyContactResponse;
import com.example.SSOT.models.rest.DeleteEmergencyContactRequest;
import com.example.SSOT.models.rest.DeleteEmergencyContactResponse;

public class EmergencyContactService {
	public static AddEmergencyContactResponse addEmergencyContact(AddEmergencyContactRequest addEmergencyContactRequest) {
		try {
			if (addEmergencyContactRequest.getUserEmail() == null) {
				throw new NullValueException("No User Email provided");
			} else if (addEmergencyContactRequest.getEmergencyContactEmail() == null) {
				throw new NullValueException("No Emergency Contact email provided");
			} else if(!Users.getUserList().containsKey(addEmergencyContactRequest.getUserEmail())) {
				throw new UserNotFoundException("No User with email " + addEmergencyContactRequest.getUserEmail() + "exists");
			} else if(!Users.getUserList().containsKey(addEmergencyContactRequest.getEmergencyContactEmail())) {
				throw new UserNotFoundException("No User with email " + addEmergencyContactRequest.getEmergencyContactEmail() + "exists");
			}
			User user = Users.getUser(addEmergencyContactRequest.getUserEmail());
			User emergencyContact = Users.getUser(addEmergencyContactRequest.getEmergencyContactEmail());
			user.getEmergencyContacts().addEmergencyContact(emergencyContact);
			return new AddEmergencyContactResponse(HttpStatus.OK, "Emergency contact with email " + addEmergencyContactRequest.getEmergencyContactEmail()
																+ " added to User with email " + addEmergencyContactRequest.getUserEmail(), user.getUserId());
		} catch(Exception e) {
			System.out.println(e);
			if (e instanceof NullValueException) {
				return new AddEmergencyContactResponse(HttpStatus.BAD_REQUEST, e.getMessage(), null);
			} else if (e instanceof UserNotFoundException) {
				return new AddEmergencyContactResponse(HttpStatus.NOT_FOUND, e.getMessage(), null);
			} else {
				return new AddEmergencyContactResponse(HttpStatus.INTERNAL_SERVER_ERROR, "Some error occured", null);
			}
		}
	}
	public static DeleteEmergencyContactResponse deleteEmergencyContact(DeleteEmergencyContactRequest deleteEmergencyContactRequest) {
		try {
			if (deleteEmergencyContactRequest.getUserEmail() == null) {
				throw new NullValueException("No User Email provided");
			} else if (deleteEmergencyContactRequest.getEmergencyContactEmail() == null) {
				throw new NullValueException("No Emergency Contact email provided");
			} else if(!Users.getUserList().containsKey(deleteEmergencyContactRequest.getUserEmail())) {
				throw new UserNotFoundException("No User with email " + deleteEmergencyContactRequest.getUserEmail() + "exists");
			} else if(!Users.getUserList().containsKey(deleteEmergencyContactRequest.getEmergencyContactEmail())) {
				throw new UserNotFoundException("No User with email " + deleteEmergencyContactRequest.getEmergencyContactEmail() + "exists");
			}
			User user = Users.getUser(deleteEmergencyContactRequest.getUserEmail());
			User emergencyContact = Users.getUser(deleteEmergencyContactRequest.getEmergencyContactEmail());
			user.getEmergencyContacts().deleteEmergencyContact(emergencyContact);
			return new DeleteEmergencyContactResponse(HttpStatus.OK, "Emergency contact with email " + deleteEmergencyContactRequest.getEmergencyContactEmail()
																+ " added to User with email " + deleteEmergencyContactRequest.getUserEmail(), user.getUserId());
		} catch(Exception e) {
			System.out.println(e);
			if (e instanceof NullValueException) {
				return new DeleteEmergencyContactResponse(HttpStatus.BAD_REQUEST, e.getMessage(), null);
			} else if (e instanceof UserNotFoundException) {
				return new DeleteEmergencyContactResponse(HttpStatus.NOT_FOUND, e.getMessage(), null);
			} else {
				return new DeleteEmergencyContactResponse(HttpStatus.INTERNAL_SERVER_ERROR, "Some error occured", null);
			}
		}
	}
}
