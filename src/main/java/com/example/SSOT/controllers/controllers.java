package com.example.SSOT.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.SSOT.models.rest.AddEmergencyContactRequest;
import com.example.SSOT.models.rest.AddEmergencyContactResponse;
import com.example.SSOT.models.rest.DeleteEmergencyContactRequest;
import com.example.SSOT.models.rest.DeleteEmergencyContactResponse;
import com.example.SSOT.models.rest.DeleteUserRequest;
import com.example.SSOT.models.rest.DeleteUserResponse;
import com.example.SSOT.models.rest.EditUserRequest;
import com.example.SSOT.models.rest.EditUserResponse;
import com.example.SSOT.models.rest.NewUserRequest;
import com.example.SSOT.models.rest.NewUserResponse;
import com.example.SSOT.models.rest.ResponseJSON;
import com.example.SSOT.models.rest.UpdateStatusResponse;
import com.example.SSOT.models.rest.UserDetailsResponse;
import com.example.SSOT.models.rest.UserNotificationResponse;
import com.example.SSOT.services.EmergencyContactService;
import com.example.SSOT.services.NotificationService;
import com.example.SSOT.services.UserService;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@Controller
public class controllers {
	@GetMapping(value = "/api/user/userDetails")
	public ResponseEntity<ResponseJSON> userDetails(@RequestParam String email) {
		System.out.println(email);
		UserDetailsResponse userDetailsResponse = UserService.getUserDetails(email);
		return new ResponseEntity<ResponseJSON>(userDetailsResponse, userDetailsResponse.getHttpStatus());
	}
	@PostMapping(value = "/api/user/addUser")
	public ResponseEntity<ResponseJSON> addUser(@RequestBody NewUserRequest newUserRequest) {
		NewUserResponse newUserResponse = UserService.addUser(newUserRequest);
		return new ResponseEntity<ResponseJSON>(newUserResponse, newUserResponse.getHttpStatus());
	}
	@DeleteMapping(value = "/api/user/deleteUser")
	public ResponseEntity<ResponseJSON> deleteUser(@RequestBody DeleteUserRequest deleteUserRequest) {
		DeleteUserResponse deleteUserResponse = UserService.deleteUser(deleteUserRequest);
		return new ResponseEntity<ResponseJSON>(deleteUserResponse, deleteUserResponse.getHttpStatus());
	}
	@PostMapping(value = "/api/user/editUser")
	public ResponseEntity<ResponseJSON> editUser(@RequestBody EditUserRequest editUserRequest) {
		EditUserResponse editUserResponse = UserService.editUser(editUserRequest);
		return new ResponseEntity<ResponseJSON>(editUserResponse, editUserResponse.getHttpStatus());
	}
	@PostMapping(value = "/api/emergencycontact/addEmergencyContact")
	public ResponseEntity<ResponseJSON> addEmergencyContact(@RequestBody AddEmergencyContactRequest addEmergencyContactRequest) {
		AddEmergencyContactResponse addEmergencyContactResponse = EmergencyContactService.addEmergencyContact(addEmergencyContactRequest);
		return new ResponseEntity<ResponseJSON>(addEmergencyContactResponse, addEmergencyContactResponse.getHttpStatus());
	}
	@DeleteMapping(value = "/api/emergencycontact/deleteEmergencyContact")
	public ResponseEntity<ResponseJSON> deleteEmergencyContact(@RequestBody DeleteEmergencyContactRequest deleteEmergencyContactRequest) {
		DeleteEmergencyContactResponse deleteEmergencyContactResponse = EmergencyContactService.deleteEmergencyContact(deleteEmergencyContactRequest);
		return new ResponseEntity<ResponseJSON>(deleteEmergencyContactResponse, deleteEmergencyContactResponse.getHttpStatus());
	}
	@GetMapping(value = "/api/notification/userNotifications")
	public ResponseEntity<ResponseJSON> userNotifications(@RequestParam(required = true) String email) {
		UserNotificationResponse userNotificationResponse = NotificationService.getUserNotifications(email);
		return new ResponseEntity<ResponseJSON>(userNotificationResponse, userNotificationResponse.getHttpStatus());
	}
	@GetMapping(value = "/api/notification/updateStatus")
	public ResponseEntity<ResponseJSON> updateStatus(@RequestParam(required = true) String email) {
		UpdateStatusResponse updateStatusResponse = NotificationService.updateStatus(email);
		return new ResponseEntity<ResponseJSON>(updateStatusResponse, updateStatusResponse.getHttpStatus());
	}
}