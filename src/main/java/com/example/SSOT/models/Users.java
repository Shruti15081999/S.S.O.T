package com.example.SSOT.models;

import java.util.HashMap;
import java.util.Map;

public class Users {
	private static Map<String, User> userList;
	static {
		userList = new HashMap<>();
	}
	public static Map<String, User> getUserList() {
		return userList;
	}
	public static void setUserList(Map<String, User> userList) {
		Users.userList = userList;
	}
	public static User getUser(String email) {
		return userList.get(email);
	}
	public static void addUser(User user) {
		userList.put(user.getEmail(), user);
	}
	public static void deleteUser(String email) {
		userList.remove(email);
	}
}
