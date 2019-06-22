package com.example.SSOT.models.rest;

public class DeleteEmergencyContactRequest {
	private String userEmail;
	private String emergencyContactEmail;
	public String getUserEmail() {
		return userEmail;
	}
	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}
	public String getEmergencyContactEmail() {
		return emergencyContactEmail;
	}
	public void setEmergencyContactEmail(String emergencyContactEmail) {
		this.emergencyContactEmail = emergencyContactEmail;
	}
}
