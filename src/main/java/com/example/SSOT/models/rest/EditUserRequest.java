package com.example.SSOT.models.rest;

import com.example.SSOT.models.Address;

public class EditUserRequest {
	private String email;
	private String firstName;
	private String lastName;
	private Address address;
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
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
	public Address getAddress() {
		return address;
	}
	public void setAddress(Address address) {
		this.address = address;
	}
	@Override
	public String toString() {
		return "EditUserRequest [email=" + email + ", firstName=" + firstName + ", lastName=" + lastName + ", address="
				+ address + "]";
	}
}
