package com.example.SSOT.models;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class Users {
	private static Map<UUID, User> userList;
	static {
		userList = new HashMap<>();
	}
	public static Map<UUID, User> getUserList() {
		return userList;
	}
	public static void setUserList(Map<UUID, User> userList) {
		Users.userList = userList;
	}
	public static User getUser(UUID userId) {
		return userList.get(userId);
	}
	public static void addUser(User user) {
		userList.put(user.getUserId(), user);
	}
}
