package com.example.SSOT.services;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.StringTokenizer;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.springframework.http.HttpStatus;

import com.example.SSOT.exceptions.NullValueException;
import com.example.SSOT.exceptions.UserNotFoundException;
import com.example.SSOT.models.Notification;
import com.example.SSOT.models.User;
import com.example.SSOT.models.Users;
import com.example.SSOT.models.rest.UpdateStatusResponse;
import com.example.SSOT.models.rest.UserNotificationResponse;

public class NotificationService {
	public static UserNotificationResponse getUserNotifications(String email) {
		try {
			if (email == null) {
				throw new NullValueException("No Email provided");
			} else if(!Users.getUserList().containsKey(email)) {
				throw new UserNotFoundException("No User with email " + email + "exists");
			}
			User user = Users.getUser(email);
			return new UserNotificationResponse(HttpStatus.OK, "User Notifications with email " + email, user);
		} catch(Exception e) {
			System.out.println(e);
			if (e instanceof NullValueException) {
				return new UserNotificationResponse(HttpStatus.BAD_REQUEST, e.getMessage(), null);
			} else if (e instanceof UserNotFoundException) {
				return new UserNotificationResponse(HttpStatus.NOT_FOUND, e.getMessage(), null);
			} else {
				return new UserNotificationResponse(HttpStatus.INTERNAL_SERVER_ERROR, "Some error occured", null);
			}
		}
	}

	private static boolean getWeatherUpdate(User user) throws ClientProtocolException, IOException {
		boolean status = false;
		HttpClient client = HttpClientBuilder.create().build();
		DateFormat df = new SimpleDateFormat("yyyy/M/d");
		Calendar calobj = Calendar.getInstance();
		String currentDate = df.format(calobj.getTime());
		HttpGet request = new HttpGet("https://www.metaweather.com/api/location/" + user.getLocation().getWoeid() + "/" + currentDate + "/");
		HttpResponse response = client.execute(request);
		String responseBody = EntityUtils.toString(response.getEntity());
		StringTokenizer responseStringTokens = new StringTokenizer(responseBody, ",");
		String tempToken = "";
		while (responseStringTokens.hasMoreTokens()) {
			tempToken = responseStringTokens.nextToken();
			if (tempToken.contains("weather_state_abbr")) {
				break;
			}
		}
		if (tempToken.endsWith("\"sn\"")) {
			user.setWeather("Snow");
			status = false;
		} else if (tempToken.endsWith("\"sl\"")) {
			user.setWeather("Sleet");
			status = false;
		} else if (tempToken.endsWith("\"h\"")) {
			user.setWeather("Hail");
			status = true;
		} else if (tempToken.endsWith("\"t\"")) {
			user.setWeather("Thunderstorm");
			status = true;
		} else if (tempToken.endsWith("\"hr\"")) {
			user.setWeather("Heavy Rain");
			status = true;
		} else if (tempToken.endsWith("\"lr\"")) {
			user.setWeather("Light Rain");
			status = false;
		} else if (tempToken.endsWith("\"s\"")) {
			user.setWeather("Showers");
			status = false;
		} else if (tempToken.endsWith("\"hc\"")) {
			user.setWeather("Heavy Cloud");
			status = false;
		} else if (tempToken.endsWith("\"lc\"")) {
			user.setWeather("Light Cloud");
			status = false;
		} else if (tempToken.endsWith("\"c\"")) {
			user.setWeather("Clear");
			status = false;
		}
		return status;
	}
	
	public static UpdateStatusResponse updateStatus(String email) {
		try {
			if (email == null) {
				throw new NullValueException("No Email provided");
			} else if(!Users.getUserList().containsKey(email)) {
				throw new UserNotFoundException("No User with email " + email + "exists");
			}
			User user = Users.getUser(email);
			boolean notify = getWeatherUpdate(user);
			Notification notification = new Notification(user.getFirstName() + " "
														+ user.getLastName() + " might be stuck in "
														+ user.getWeather() + " at "
														+ user.getLocation().getLocationName(), user.getLocation());
			if (notify == true) {
				for (User emergencyContact: user.getEmergencyContacts().getEmergencyContacts()) {
					emergencyContact.getNotifications().addNotification(notification);
				}
				return new UpdateStatusResponse(HttpStatus.OK, "User status updated with warning", user.getUserId());
			}
			return new UpdateStatusResponse(HttpStatus.OK, "User status updated without warning", user.getUserId());
		} catch(Exception e) {
			System.out.println(e);
			if (e instanceof NullValueException) {
				return new UpdateStatusResponse(HttpStatus.BAD_REQUEST, e.getMessage(), null);
			} else if (e instanceof UserNotFoundException) {
				return new UpdateStatusResponse(HttpStatus.NOT_FOUND, e.getMessage(), null);
			} else {
				return new UpdateStatusResponse(HttpStatus.INTERNAL_SERVER_ERROR, "Some error occured", null);
			}
		}
	}
}
