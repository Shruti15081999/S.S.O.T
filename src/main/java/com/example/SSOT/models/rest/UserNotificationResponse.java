package com.example.SSOT.models.rest;

import java.util.UUID;

import org.springframework.http.HttpStatus;

import com.example.SSOT.models.Notifications;
import com.example.SSOT.models.User;

public class UserNotificationResponse extends ResponseJSON{
	private String message;
	public Notifications notifications;
	private UUID userID;
	public UserNotificationResponse(HttpStatus httpStatus, String message, User user) {
		super(httpStatus);
		this.message = message;
		this.notifications = user.getNotifications();
		this.userID = user.getUserId();
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public Notifications getNotifications() {
		return notifications;
	}
	public void setNotifications(Notifications notifications) {
		this.notifications = notifications;
	}
	public UUID getUserID() {
		return userID;
	}
	public void setUserID(UUID userID) {
		this.userID = userID;
	}
}
