package com.jsp.tecabooking.dao;

import com.jsp.tecabooking.model.UserBookingInfo;
import com.jsp.tecabooking.model.UserInfo;

public interface UserDao {
	void userRegistration(UserInfo u);
	void booking(UserBookingInfo b);
}
