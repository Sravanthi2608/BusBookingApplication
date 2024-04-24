package com.jsp.tecabooking;

import java.util.Scanner;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.jsp.tecabooking.dao.UserDao;
import com.jsp.tecabooking.dao.UserDaoImp;
import com.jsp.tecabooking.model.UserBookingInfo;
import com.jsp.tecabooking.model.UserInfo;

/**
 * Hello world!
 *
 */
public class App 
{
	public static int userid=0;
    public static void main( String[] args )
    {
    	Scanner sc = new Scanner(System.in);
		System.out.println("Enter \n 1.For registration \n 2.For Booking");
		int response = sc.nextInt();
		switch (response) 
		{
		case 1:
			System.out.println("Enter User Id: ");
			userid = sc.nextInt();
			sc.nextLine();
			System.out.println("Enter first name: ");
			String fname = sc.next();
			System.out.println("Enter email id: ");
			String email = sc.next();
			System.out.println("Enter password: ");
			String pass = sc.next();
			while(pass.length()<4 || pass.length()>4) {
				System.out.println("Enter 4 digit password: ");
				pass=sc.next();
			}
			System.out.println("Enter address: ");
			String add = sc.next();
			UserInfo u = new UserInfo(userid,fname,email,pass,add);
			ApplicationContext ac = new ClassPathXmlApplicationContext("booking.xml");
			UserDao userDao=(UserDao)ac.getBean("userdaoimp",UserDaoImp.class);
			userDao.userRegistration(u);
			System.out.println("If U want to continue Enter \n 2.For Booking");
			response = sc.nextInt();
		case 2:
			System.out.println("From.... ");
			String from = sc.next();
			System.out.println("To.... ");
			String to = sc.next();
			UserBookingInfo b = new UserBookingInfo(from,to,userid);
			ApplicationContext ac1 = new ClassPathXmlApplicationContext("booking.xml");
			UserDao userDao1=ac1.getBean("userDaoImp",UserDao.class);
			userDao1.booking(b);
			
			ApplicationContext ac2 = new ClassPathXmlApplicationContext("booking.xml");
			UserInfo userInfo=ac2.getBean("userinfo",UserInfo.class);
			System.out.println(userInfo);
			break;
		}
    }
}
