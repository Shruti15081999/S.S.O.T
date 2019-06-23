package com.example.SSOT.models;

public class Notification {
	private String message;
	private Location location;
	public Notification(String message, Location location) {
		super();
		this.message = message;
		this.location = location;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public Location getLocation() {
		return location;
	}
	public void setLocation(Location location) {
		this.location = location;
	}
}
