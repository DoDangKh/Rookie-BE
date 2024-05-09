package com.rookie.rookiee;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = { "com.rookie.rookiee" })
public class RookieeApplication {

	public static void main(String[] args) {
		SpringApplication.run(RookieeApplication.class, args);
	}

}
