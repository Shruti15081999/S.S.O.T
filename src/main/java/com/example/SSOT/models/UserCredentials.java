package com.example.SSOT.models;

import java.util.HashMap;
import java.util.Map;

public class UserCredentials {
	private static Map<String, String> credentials;
	static {
		credentials = new HashMap<>();
	}
	public static void addCredentials(String email, String password) {
		credentials.put(email, password);
	}
	public static void removeCredentials(String email) {
		credentials.remove(email);
	}
	public static boolean login(String email, String password) {
		boolean valid;
		if (credentials.get(email).equals(password)) {
			valid = true;
		} else {
			valid = false;
		}
		return valid;
	}
}