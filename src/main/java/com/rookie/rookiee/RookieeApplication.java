package com.rookie.rookiee;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@SpringBootApplication()
@CrossOrigin(origins = { "http://localhost:3000" })
@EnableWebMvc
public class RookieeApplication {

	public static void main(String[] args) {
		SpringApplication.run(RookieeApplication.class, args);
	}

}
