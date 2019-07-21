package com.example.SSOT.models;

import java.util.ArrayList;
import java.util.List;

import com.example.SSOT.exceptions.ListUnderFlowException;
import com.example.SSOT.exceptions.NotificationNotFoundException;

public class Notifications {
	private static int listSize = 10;
	private List<Notification> notificationsList;
	public Notifications() {
		this.notificationsList = new ArrayList<>();
	}
	public List<Notification> getNotificationsList() {
		return notificationsList;
	}
	public void setNotificationsList(List<Notification> notificationsList) {
		this.notificationsList = notificationsList;
	}
	public static int getListSize() {
		return listSize;
	}
	public void addNotification(Notification notification) {
		if (this.notificationsList.size() == listSize) {
			this.notificationsList.remove(0);
		}
		this.notificationsList.add(notification);
	}
	public void deleteNotification(Notification notification) throws NotificationNotFoundException, ListUnderFlowException {
		if (this.notificationsList.size() > 0) {
			if (this.notificationsList.contains(notification)) {
				this.notificationsList.remove(notification);
			} else {
				throw new NotificationNotFoundException("Notification not found");
			}
		} else {
				throw new ListUnderFlowException("Cannot remove any more Notifications");
		}
	}
	public void clearNotifications() {
		this.notificationsList.clear();
	}
	@Override
	public String toString() {
		return "Notifications [notificationsList=" + notificationsList + "]";
	}
}
