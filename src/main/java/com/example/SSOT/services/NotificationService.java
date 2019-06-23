package com.example.SSOT.services;

import org.springframework.http.HttpStatus;

import com.example.SSOT.exceptions.NullValueException;
import com.example.SSOT.exceptions.UserNotFoundException;
import com.example.SSOT.models.Notification;
import com.example.SSOT.models.User;
import com.example.SSOT.models.Users;
import com.example.SSOT.models.rest.UpdateStatusResponse;
import com.example.SSOT.models.rest.UserNotificationResponse;

public class NotificationService {
	public static UserNotificationResponse getUserNotifications(String email) {
		try {
			if (email == null) {
				throw new NullValueException("No Email provided");
			} else if(!Users.getUserList().containsKey(email)) {
				throw new UserNotFoundException("No User with email " + email + "exists");
			}
			User user = Users.getUser(email);
			return new UserNotificationResponse(HttpStatus.OK, "User Notifications with email " + email, user);
		} catch(Exception e) {
			System.out.println(e);
			if (e instanceof NullValueException) {
				return new UserNotificationResponse(HttpStatus.BAD_REQUEST, e.getMessage(), null);
			} else if (e instanceof UserNotFoundException) {
				return new UserNotificationResponse(HttpStatus.NOT_FOUND, e.getMessage(), null);
			} else {
				return new UserNotificationResponse(HttpStatus.INTERNAL_SERVER_ERROR, "Some error occured", null);
			}
		}
	}
	public static UpdateStatusResponse updateStatus(String email) {
		try {
			if (email == null) {
				throw new NullValueException("No Email provided");
			} else if(!Users.getUserList().containsKey(email)) {
				throw new UserNotFoundException("No User with email " + email + "exists");
			}
			User user = Users.getUser(email);
			boolean notify = false;
			/*
			 * TODO: add http client to get data and update notify accordingly
			 */
			Notification notification = new Notification(user.getFirstName() + " "
														+ user.getLastName() + " is in problem at"
														+ user.getLocation().getLocationName(), user.getLocation());
			if (notify == true) {
				for (User emergencyContact: user.getEmergencyContacts().getEmergencyContacts()) {
					emergencyContact.getNotifications().addNotification(notification);
				}
			}
			if (notify)
				return new UpdateStatusResponse(HttpStatus.OK, "User status updated with warning", user.getUserId());
			return new UpdateStatusResponse(HttpStatus.OK, "User status updated without warning", user.getUserId());
		} catch(Exception e) {
			System.out.println(e);
			if (e instanceof NullValueException) {
				return new UpdateStatusResponse(HttpStatus.BAD_REQUEST, e.getMessage(), null);
			} else if (e instanceof UserNotFoundException) {
				return new UpdateStatusResponse(HttpStatus.NOT_FOUND, e.getMessage(), null);
			} else {
				return new UpdateStatusResponse(HttpStatus.INTERNAL_SERVER_ERROR, "Some error occured", null);
			}
		}
	}
}
