package com.jsp.tecabooking.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.stereotype.Component;

import com.jsp.tecabooking.model.UserBookingInfo;
import com.jsp.tecabooking.model.UserInfo;

@Component
public class UserDaoImp_SpringJDBC implements UserDao_SpringJDBC {
	@Value("${jdbc.drver.class.name}")
	private String driverclassame;
	@Value("${jdbc.url}")
	private String url;
	@Value("${jdbc.username}")
	private String username;
	@Value("${jdbc.password}")
	private String password;
	Connection connection;
	public static int userid = 0;

	@PostConstruct
	public void establishmentOfConnection() throws SQLException {
		connection = DriverManager.getConnection(url, username, password);
		System.out.println("Connection Established");
	}

	@PreDestroy
	public void closeTheConnection() throws SQLException {
		connection.close();
		System.out.println("Connection Closed");
	}

	@Override
	public void userRegistration(String fname, String email, String pass, String add) {
		try {
			connection = DriverManager
					.getConnection("jdbc:mysql://localhost:3306/booking_app?user=root&password=12345");
			PreparedStatement ps = connection.prepareStatement(
					"insert into booking_app.user_info(User_Name,User_Email_ID,User_Password,User_Address) values(?,?,?,?)");
			ps.setString(1, fname);
			ps.setString(2, email);
			ps.setString(3, pass);
			ps.setString(4, add);
			int result = ps.executeUpdate();
			if (result != 0) {
				System.out.println("Registration Successful");
			} else {
				System.out.println("Registration Unsuccessful");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public boolean userLogin(String email, String pass) {
		try {
			PreparedStatement statement = connection
					.prepareStatement("select * from booking_app.user_info where User_Email_Id=? and User_Password=?");
			statement.setString(1, email);
			statement.setString(2, pass);
			ResultSet resultSet = statement.executeQuery();
			if (resultSet.next()) {
				System.out.println("Login Successfull");
				userid = resultSet.getInt("User_Id");
				return true;
			} else {
				System.out.println("Invalid Details");
				return false;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;

	}

	@Override
	public void userBooking(String from, String to) {

		try {
			PreparedStatement ps = connection
					.prepareStatement("select * from booking_app.apsrtc where Bus_From=? and Bus_To=?");
			ps.setString(1, from);
			ps.setString(2, to);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				System.out.println("BookingInfo [BusId=" + rs.getInt("Bus_Id") + ", From=" + rs.getString("Bus_From")
						+ ", To=" + rs.getString("Bus_To") + ", Timings=" + rs.getString("Timings") + ", Price="
						+ rs.getInt("Price") + "]");
			}
			// Bus_Id
			Scanner sc = new Scanner(System.in);
			System.out.println("Select any Bus by Id That you wish to travel:");
			int busId = sc.nextInt();
			PreparedStatement ps1 = connection.prepareStatement("select * from booking_app.apsrtc where Bus_Id=?");
			ps1.setInt(1, busId);
			ResultSet rs1 = ps1.executeQuery();
			if (rs1.next()) {
				PreparedStatement ps2 = connection.prepareStatement(
						"insert into booking_app.user_booking_info(Booking_Id,Booking_From,Booking_TO,Booking_Date,Booking_Price,User_Id) values(?,?,?,?,?,?)");
				ps2.setInt(1, rs1.getInt("Bus_Id"));
				ps2.setString(2, rs1.getString("Bus_From"));
				ps2.setString(3, rs1.getString("Bus_To"));
				ps2.setString(4, rs1.getString("Timings"));
				ps2.setInt(5, rs1.getInt("Price"));
				ps2.setInt(6, userid);
				int rs2 = ps2.executeUpdate();
				if (rs2 != 0) {
					System.out.println("Booking Successful");
				} else {
					System.out.println("Booking Unsuccessful");
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	@Override
	public void bookingStatus() {
		try {
			System.out.println("---------User Details--------");
			PreparedStatement ps1 = connection.prepareStatement("select * from booking_app.user_info where User_Id=?");
			ps1.setInt(1, userid);
			ResultSet rs1 = ps1.executeQuery();
			while (rs1.next()) {
				System.out.println("UserInfo [UserId=" + rs1.getInt("User_Id") + ", UserName="
						+ rs1.getString("User_Name") + ", UserEmailId=" + rs1.getString("User_Email_Id")
						+ ", User_Password=" + rs1.getString("User_Password") + ", User_Address="
						+ rs1.getString("User_Address") + "]");
			}
			System.out.println("---------Booking Details-----------");
			PreparedStatement ps2 = connection
					.prepareStatement("select * from booking_app.user_booking_info where User_Id=?");
			ps2.setInt(1, userid);
			ResultSet rs2 = ps2.executeQuery();
			if (rs2.next()) {
				while (rs2.next()) {
					System.out.println("BookingInfo [BookingId=" + rs2.getInt("Booking_Id") + ", From="
							+ rs2.getString("Booking_From") + ", To=" + rs2.getString("Booking_To") + ", Timings="
							+ rs2.getString("Booking_Date") + ", Price=" + rs2.getInt("Booking_Price") + ", UserId="
							+ rs2.getInt("User_Id") + "]");
				}
			} else {
				System.out.println("No Tickets Booked");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void ticketCancel() {
		try {
			PreparedStatement ps1 = connection
					.prepareStatement("delete from booking_app.user_booking_info where User_Id=?");
			ps1.setInt(1, userid);
			int update = ps1.executeUpdate();
			if (update != 0) {
				System.out.println("Ticket Cancellation Successful");
			} else {
				System.out.println("Ticket Cancellation Unsuccessful");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
