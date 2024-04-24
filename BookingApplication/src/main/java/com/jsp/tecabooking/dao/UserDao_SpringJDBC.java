package com.jsp.tecabooking.dao;

import org.springframework.stereotype.Component;

import com.jsp.tecabooking.model.UserInfo;

@Component
public interface UserDao_SpringJDBC {
	void userRegistration(String fname,String email,String pass,String add);
	boolean userLogin(String email, String pass);
	void userBooking(String from,String to);
	void bookingStatus();
	void ticketCancel();
}
