package com.example.SSOT.models;

import java.util.ArrayList;
import java.util.List;

public class EmergencyContacts {
	private static final int listSize = 4;
	private List<User> emergencyContacts;
	public EmergencyContacts() {
		super();
		this.emergencyContacts = new ArrayList<>();
	}
	public List<User> getEmergencyContacts() {
		return emergencyContacts;
	}
	public void setEmergencyContacts(List<User> emergencyContacts) {
		this.emergencyContacts = emergencyContacts;
	}
	public void addEmergencyContact(User user) {
		if (this.emergencyContacts.size() < listSize) {
			this.emergencyContacts.add(user);
		} else {
			System.out.println("OverFLow!!");
		}
	}
	public void deleteEmergencyContact(User user) {
		if (this.emergencyContacts.size() > 0) {
			if (this.emergencyContacts.contains(user)) {
				this.emergencyContacts.remove(user);
			} else {
				System.out.println("ContactNotFound!!");
			}
		} else {
			System.out.println("UnderFlow!!");
		}
		
	}
	@Override
	public String toString() {
		return "EmergencyContacts [emergencyContacts=" + emergencyContacts + "]";
	}
}
