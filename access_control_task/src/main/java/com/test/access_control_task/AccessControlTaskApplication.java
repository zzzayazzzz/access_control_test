package com.test.access_control_task;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class AccessControlTaskApplication {

	public static void main(String[] args) {
		SpringApplication.run(AccessControlTaskApplication.class, args);
		BCryptPasswordEncoder bcryptPasswordEncoder = new BCryptPasswordEncoder();
	    String pwd = bcryptPasswordEncoder.encode("password");
	    System.out.println(pwd);
	}

}