package com.jsp.tecabooking;



import java.util.Scanner;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@ComponentScan(basePackages = "com.jsp.tecabooking")
@PropertySource("com/jsp/file/BookingData.properties")
public class BookingConfig {
	
	@Bean
	public Scanner scannerClassBean() {
		Scanner scanner = new Scanner(System.in);
		return scanner;
	}
}
