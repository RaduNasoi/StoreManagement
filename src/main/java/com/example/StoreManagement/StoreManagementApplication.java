package com.example.StoreManagement;

import com.example.StoreManagement.model.User;
import com.example.StoreManagement.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class StoreManagementApplication {

	public static void main(String[] args) {
		SpringApplication.run(StoreManagementApplication.class, args);
	}

	//Add some mock users on startup
	@Bean
	public CommandLineRunner seedUsers(UserRepository userRepository) {
		return args -> {
			if (userRepository.count() == 0) {
				userRepository.save(new User(null, "admin", "admin123", "ADMIN"));
				userRepository.save(new User(null, "user", "user123", "USER"));
			}
		};
	}

}
