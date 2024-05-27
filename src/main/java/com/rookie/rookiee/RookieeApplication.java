package com.rookie.rookiee;

import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.CrossOrigin;

import com.rookie.rookiee.dto.SignUpDto;
import com.rookie.rookiee.entity.Role;
import com.rookie.rookiee.repository.RoleRepository;

@SpringBootApplication()
@CrossOrigin(origins = { "http://localhost:3000" })
public class RookieeApplication {

	public static void main(String[] args) {
		SpringApplication.run(RookieeApplication.class, args);
	}

}
