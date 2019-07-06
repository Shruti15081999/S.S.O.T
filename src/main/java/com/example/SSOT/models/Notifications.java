package com.example.SSOT.models;

import java.util.ArrayList;
import java.util.List;

import com.example.SSOT.exceptions.ListUnderFlowException;
import com.example.SSOT.exceptions.NotificationNotFoundException;

public class Notifications {
	private static int listSize = 10;
	private List<Notification> notifictionsList;
	public Notifications() {
		this.notifictionsList = new ArrayList<>();
	}
	public List<Notification> getNotifictionsList() {
		return notifictionsList;
	}
	public void setNotifictionsList(List<Notification> notifictionsList) {
		this.notifictionsList = notifictionsList;
	}
	public static int getListSize() {
		return listSize;
	}
	public void addNotification(Notification notification) {
		if (this.notifictionsList.size() == listSize) {
			this.notifictionsList.remove(0);
		}
		this.notifictionsList.add(notification);
	}
	public void deleteNotification(Notification notification) throws NotificationNotFoundException, ListUnderFlowException {
		if (this.notifictionsList.size() > 0) {
			if (this.notifictionsList.contains(notification)) {
				this.notifictionsList.remove(notification);
			} else {
				throw new NotificationNotFoundException("Notification not found");
			}
		} else {
				throw new ListUnderFlowException("Cannot remove any more Notifications");
		}
	}
	public void clearNotifications() {
		this.notifictionsList.clear();
	}
	@Override
	public String toString() {
		return "Notifications [notifictionsList=" + notifictionsList + "]";
	}
}
