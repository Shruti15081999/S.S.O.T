package com.example.SSOT.models;

import java.util.UUID;

public class User {
	private UUID userId;
	private String email;
	private String firstName;
	private String lastName;
	private int contactNo;
	private Address address;
	private Notifications notifications;
	private Location location;
	private EmergencyContacts emergencyContacts;
	private String weather;
	public User(String firstName, String lastName, String email, String locationName) {
		super();
		this.userId = UUID.randomUUID();
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.address = new Address();
		this.notifications = new Notifications();
		this.location = new Location(locationName);
		this.emergencyContacts = new EmergencyContacts();
		this.weather = "Unknown";
	}
	public String getWeather() {
		return weather;
	}
	public void setWeather(String weather) {
		this.weather = weather;
	}
	public Location getLocation() {
		return this.location;
	}
	public void setLocation(Location location) {
		this.location = location;
	}
	public Notifications getNotifications() {
		return notifications;
	}
	public void setNotifications(Notifications notifications) {
		this.notifications = notifications;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public UUID getUserId() {
		return userId;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public int getContactNo() {
		return contactNo;
	}
	public void setContactNo(int contactNo) {
		this.contactNo = contactNo;
	}
	public Address getAddress() {
		return address;
	}
	public void setAddress(Address address) {
		this.address = address;
	}
	public EmergencyContacts getEmergencyContacts() {
		return emergencyContacts;
	}
	public void setEmergencyContacts(EmergencyContacts emergencyContacts) {
		this.emergencyContacts = emergencyContacts;
	}
	@Override
	public String toString() {
		return "User [userId=" + userId + ", email=" + email + ", firstName=" + firstName + ", lastName=" + lastName
				+ ", contactNo=" + contactNo + ", address=" + address + ", notifications=" + notifications
				+ ", location=" + location + ", emergencyContacts=" + emergencyContacts + ", weather=" + weather + "]";
	}
}
