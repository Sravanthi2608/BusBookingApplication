
package com.jsp.tecabooking.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;

import com.jsp.tecabooking.model.UserBookingInfo;
import com.jsp.tecabooking.model.UserInfo;

@Component()
public class UserDaoImp implements UserDao {
	Connection con;
	@Override
	public void userRegistration(UserInfo u) {
		try {
			con=DriverManager.getConnection("jdbc:mysql://localhost:3306/booking_app?user=root&password=12345");
			PreparedStatement ps = con.prepareStatement("insert into booking_app.user_info(User_Id,User_Name,User_Email_ID,User_Password,User_Address) values(?,?,?,?,?)");
			ps.setInt(1, u.getUserId());
			ps.setString(2, u.getUserName());
			ps.setString(3, u.getUserEmailId());
			ps.setString(4, u.getUserPassword());
			ps.setString(5, u.getUserAddress());
			int result=ps.executeUpdate();
			if(result!=0) {
				System.out.println("Registration Successful");
			}
			else {
				System.out.println("Registration Unsuccessful");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}			
	}
	@Override
	public void booking(UserBookingInfo b) {
		try {
			con=DriverManager.getConnection("jdbc:mysql://localhost:3306/booking_app?user=root&password=12345");
			PreparedStatement ps = con.prepareStatement("select * from booking_app.apsrtc where Bus_From=? and Bus_To=?");
			ps.setString(1,b.getBookingFrom());
			ps.setString(2,b.getBookingTo());
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				System.out.println("BookingInfo [BusId=" + rs.getInt("Bus_Id") + ", From=" +rs.getString("Bus_From") + ", To=" + rs.getString("Bus_To")
						+ ", Timings=" + rs.getString("Timings") + ", Price="+ rs.getInt("Price")+"]");
			}
			//Bus_Id
			Scanner sc = new Scanner(System.in);
			System.out.println("Select any Bus by Id That you wish to travel:");
			int response = sc.nextInt();
			PreparedStatement ps1 = con.prepareStatement("select * from booking_app.apsrtc where Bus_Id=?");
			ps1.setInt(1,response);
			ResultSet rs1 = ps1.executeQuery();
			if(rs1.next()) {
				int bus_id=rs1.getInt("Bus_Id");
				String from=rs1.getString("Bus_From");
				String to=rs1.getString("Bus_To");
				String timings=rs1.getString("Timings");
				int price=rs1.getInt("Price");
				UserBookingInfo uBI=new UserBookingInfo(bus_id, from, to, timings, price);
					PreparedStatement ps2 = con.prepareStatement("insert into booking_app.user_booking_info(Booking_Id,Booking_From,Booking_TO,Booking_Date,Booking_Price,User_Id) values(?,?,?,?,?,?)");
					ps2.setInt(1,bus_id);
					ps2.setString(2,from);
					ps2.setString(3,to);
					ps2.setString(4,timings);
					ps2.setInt(5,price);
					ps2.setInt(6,b.getUserId());
					int rs2=ps2.executeUpdate();
					if(rs2!=0) {
						System.out.println("---------User Details--------");
						PreparedStatement ps3 = con.prepareStatement("select * from booking_app.user_info where User_Id=?");
						ps3.setInt(1,b.getUserId());
						ResultSet rs3 = ps3.executeQuery();
						while(rs3.next()) {
							System.out.println("UserInfo [UserId=" + rs3.getInt("User_Id") + ", UserName=" +rs3.getString("User_Name") + ", UserEmailId=" + rs3.getString("User_Email_Id")
									+ ", User_Password=" + rs3.getString("User_Password") + ", User_Address="+ rs3.getString("User_Address")+"]");
						}
						System.out.println("---------Booking Details-----------");
						PreparedStatement ps4 = con.prepareStatement("select * from booking_app.user_booking_info where User_Id=?");
						ps4.setInt(1,b.getUserId());
						ResultSet rs4 = ps4.executeQuery();
						while(rs4.next()) {
							System.out.println("BookingInfo [BookingId=" + rs4.getInt("Booking_Id") + ", From=" +rs4.getString("Booking_From") + ", To=" + rs4.getString("Booking_To")
									+ ", Timings=" + rs4.getString("Booking_Date") + ", Price="+ rs4.getInt("Booking_Price")+ ", UserId="+ rs4.getInt("User_Id")+"]");
						}
					}
					else {
						System.out.println("Booking Unsuccessful");
					}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

}
