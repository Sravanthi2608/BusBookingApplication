package com.jsp.tecabooking.model;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;
@Component("userinfo")
public class UserInfo {
	private int userId;
	private String userName;
	private String userEmailId;
	private String userPassword;
	private String userAddress;
	@Autowired
	//private List<UserBookingInfo> userBookingInfo;
	
	public UserInfo() {
		
	}

	public UserInfo(int userId,String userName, String userEmailId, String userPassword, String userAddress) {
		this.setUserId(userId);
		this.setUserName(userName);
		this.setUserEmailId(userEmailId);
		this.setUserPassword(userPassword);
		this.setUserAddress(userAddress);
	}


	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserEmailId() {
		return userEmailId;
	}

	public void setUserEmailId(String userEmailId) {
		this.userEmailId = userEmailId;
	}

	public String getUserPassword() {
		return userPassword;
	}

	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}

	public String getUserAddress() {
		return userAddress;
	}

	public void setUserAddress(String userAddress) {
		this.userAddress = userAddress;
	}

//	@Override
//	public String toString() {
//		return "UserInfo [userId=" + getUserId() + ", userName=" + getUserName() + ", userEmailId=" + getUserEmailId()
//				+ ", userPassword=" + getUserPassword() + ", userAddress=" + getUserAddress() + ", uBI=" + userBookingInfo + "]";
//	}
	
}
