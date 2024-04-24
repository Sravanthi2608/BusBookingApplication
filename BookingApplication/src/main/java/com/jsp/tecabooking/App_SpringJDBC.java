package com.jsp.tecabooking;

import java.util.Scanner;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;

import com.jsp.tecabooking.dao.UserDaoImp_SpringJDBC;
import com.jsp.tecabooking.dao.UserDao_SpringJDBC;

public class App_SpringJDBC {

	public static void main(String[] args) {
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(BookingConfig.class);
		UserDao_SpringJDBC bean = context.getBean("userDaoImp_SpringJDBC", UserDao_SpringJDBC.class);
		Scanner scanner = context.getBean("scannerClassBean", Scanner.class);
		boolean val = false;
		do {
			System.out.println("Enter \n 1.New User?? get SignUp \n 2.Exixting user Get SignIn \n 3.Exit");
			int response = scanner.nextInt();
			switch (response) {
			case 1:
				System.out.println("Enter first name: ");
				String fname = scanner.next();
				System.out.println("Enter email id: ");
				String email = scanner.next();
				System.out.println("Enter password: ");
				String pass = scanner.next();
				while (pass.length() < 4 || pass.length() > 4) {
					System.out.println("Enter 4 digit password: ");
					pass = scanner.next();
				}
				System.out.println("Enter address: ");
				String add = scanner.next();
				bean.userRegistration(fname, email, pass, add);
				System.out.println("If U want to continue press 'True' else press 'False'");
				val = scanner.nextBoolean();
				break;

			case 2:
				System.out.println("Enter your emailid");
				String emailid = scanner.next();
				System.out.println("Enter your password");
				String password = scanner.next();

				if (bean.userLogin(emailid, password)) {
					boolean res = false;
					do {
						System.out.println(
								"Enter \n 1.Bus Booking  \n 2.Booking Status \n 3.Ticket Cancellitation \n 4.Exit");
						int response1 = scanner.nextInt();
						switch (response1) {
						case 1:
							System.out.println("Enter From:");
							String from = scanner.next();
							System.out.println("Enter To:");
							String to = scanner.next();

							bean.userBooking(from, to);
							System.out.println("If U want to continue press 'True' else press 'False'");
							res = scanner.nextBoolean();
							break;

						case 2:
							bean.bookingStatus();
							System.out.println("If U want to continue press 'True' else press 'False'");
							res = scanner.nextBoolean();
							break;
						case 3:
							System.out.println("Press 'Confirm' to cancle Your Tickets else press 'No'");
							String confirm = scanner.next();
							if (confirm.equalsIgnoreCase("confirm")) {
								bean.ticketCancel();
							} else if (confirm.equalsIgnoreCase("No")) {
								System.out.println("If U want to continue press 'True' else press 'False'");
								res = scanner.nextBoolean();
							}
							System.out.println("If U want to continue press 'True' else press 'False'");
							res = scanner.nextBoolean();
							break;

						case 4:
							System.out.println("Ru sure u want to EXIT press 'False' else 'True'");
							res = scanner.nextBoolean();

						}
					} while (res == true);
				} else {
					System.out.println("No User Found");
				}
				System.out.println("If U want to SignUp again/SignIn again press 'True' else press 'False'");
				val = scanner.nextBoolean();
				break;

			case 3:
				System.out.println("Ru sure u want to EXIT press 'False' else 'True'");
				val = scanner.nextBoolean();
			}
		} while (val == true);
		context.close();
	}

}
