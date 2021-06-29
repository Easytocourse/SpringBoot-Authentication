package com.easytocourse;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.config.annotation.web.servlet.configuration.EnableWebMvcSecurity;

@SpringBootApplication
public class RestServicewithAuthenticationApplication {

	public static void main(String[] args) {
		SpringApplication.run(RestServicewithAuthenticationApplication.class, args);
	}

}
