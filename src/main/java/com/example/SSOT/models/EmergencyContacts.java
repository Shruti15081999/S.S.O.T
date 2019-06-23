package com.example.SSOT.models;

import java.util.ArrayList;
import java.util.List;

import com.example.SSOT.exceptions.ListOverFlowException;
import com.example.SSOT.exceptions.ListUnderFlowException;
import com.example.SSOT.exceptions.UserNotFoundException;

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
	public void addEmergencyContact(User user) throws ListOverFlowException {
		if (this.emergencyContacts.size() < listSize) {
			this.emergencyContacts.add(user);
		} else {
			throw new ListOverFlowException("Cannot add more than " + listSize + "contacts");
		}
	}
	public void deleteEmergencyContact(User user) throws ListUnderFlowException, UserNotFoundException {
		if (this.emergencyContacts.size() > 0) {
			if (this.emergencyContacts.contains(user)) {
				this.emergencyContacts.remove(user);
			} else {
				throw new UserNotFoundException("User not found");
			}
		} else {
				throw new ListUnderFlowException("Cannot remove any more contact");
		}	
	}
	@Override
	public String toString() {
		return "EmergencyContacts [emergencyContacts=" + emergencyContacts + "]";
	}
}
