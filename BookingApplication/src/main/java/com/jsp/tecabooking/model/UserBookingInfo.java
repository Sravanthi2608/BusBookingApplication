package com.jsp.tecabooking.model;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;

@Component("userBookingInfo")
//@ComponentScan(basePackages = "com.jsp.tecabooking.model")
public class UserBookingInfo {
	private int busId;
	private String bookingFrom;
	private String bookingTo;
	private String timings;
	private int price;
	private int userId;
	
	public UserBookingInfo() {
		
	}
	public UserBookingInfo(String bookingFrom, String bookingTo, int userId) {
		this.setBookingFrom(bookingFrom);
		this.setBookingTo(bookingTo);
		this.setUserId(userId);
	}
	public UserBookingInfo(int busId, String bookingFrom, String bookingTo, String timings, int price) {
		this.setBusId(busId);
		this.setBookingFrom(bookingFrom);
		this.setBookingTo(bookingTo);
		this.setTimings(timings);
		this.setPrice(price);
	}
	
	public int getBusId() {
		return busId;
	}
	public void setBusId(int busId) {
		this.busId = busId;
	}
	public String getBookingFrom() {
		return bookingFrom;
	}
	public void setBookingFrom(String bookingFrom) {
		this.bookingFrom = bookingFrom;
	}
	public String getBookingTo() {
		return bookingTo;
	}
	public void setBookingTo(String bookingTo) {
		this.bookingTo = bookingTo;
	}
	public String getTimings() {
		return timings;
	}
	public void setTimings(String timings) {
		this.timings = timings;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	
	@Override
	public String toString() {
		return "UserBookingInfo [busId=" + getBusId() + ", bookingFrom=" + getBookingFrom() + ", bookingTo=" + getBookingTo()
				+ ", timings=" + getTimings() + ", price=" + getPrice() + ", userId=" + getUserId() + "]";
	}
	
}
