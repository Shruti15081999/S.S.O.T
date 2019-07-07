package com.example.SSOT.models;

import java.io.IOException;
import java.util.StringTokenizer;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;

public class Location {
	private double latitude;
	private double longitude;
	private String locationName;
	public int woeid;
	
	public Location(String locationName) {
		super();
		this.setLocationName(locationName);
	}
	
	public double getLatitude() {
		return latitude;
	}
	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}
	public double getLongitude() {
		return longitude;
	}
	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}
	public String getLocationName() {
		return locationName;
	}
	
	public void setLocationName(String locationName) {
		this.locationName = locationName;
		try {
			this.updateWoeid();
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public int getWoeid() {
		return woeid;
	}
	public void setWoeid(int woeid) {
		this.woeid = woeid;
	}
	private void updateWoeid() throws ClientProtocolException, IOException {
		HttpClient client = HttpClientBuilder.create().build();
		HttpGet request = new HttpGet("https://www.metaweather.com/api/location/search/?query=" + this.getLocationName());
		HttpResponse response = client.execute(request);
		String responseBody = EntityUtils.toString(response.getEntity());
		StringTokenizer responseStringTokens = new StringTokenizer(responseBody, ",");
		StringTokenizer woeidToken;
		StringTokenizer latitudeToken;
		StringTokenizer longitudeToken;
		String tempToken;
		String latitude;
		String longitude;
		int woeid = -1;
		while (responseStringTokens.hasMoreTokens()) {
			tempToken = responseStringTokens.nextToken();
			if (tempToken.contains("woeid")) {
				woeidToken = new StringTokenizer(tempToken, ":");
				woeidToken.nextToken();
				woeid = Integer.parseInt(woeidToken.nextToken());
				this.setWoeid(woeid);
			} else if (tempToken.contains("latt_long")) {
				latitudeToken = new StringTokenizer(tempToken, ":");
				latitudeToken.nextToken();
				latitude = latitudeToken.nextToken();
				this.setLatitude(Double.parseDouble(latitude.replace("\"", "")));
			} else if (tempToken.contains("}")) {
				longitudeToken = new StringTokenizer(tempToken, "\"");
				longitude = longitudeToken.nextToken();
				this.setLongitude(Double.parseDouble(longitude.replace("\"", "")));
			}
		}
	}
	@Override
	public String toString() {
		return "Location [latitude=" + latitude + ", longitude=" + longitude + ", locationName=" + locationName
				+ ", woeid=" + woeid + "]";
	}
}
