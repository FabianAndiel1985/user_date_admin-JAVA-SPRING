package com.example.demo;

import java.time.LocalDateTime;
import java.util.Date;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class UserserviceApplication {

	public static void main(String[] args) {
		SpringApplication.run(UserserviceApplication.class, args);
	}
	
	 @Bean
	    public CommandLineRunner mappingDemo(UserRepository userRepository,
	    									 AppointmentRepository appointmentRepository) {
	        return args -> {
	        	
	            User user1 = new User("Fabian Andiel");
	          	            
	            userRepository.save(user1);
	            
	            appointmentRepository.save(new Appointment("Untersuchung 1 User 1",LocalDateTime.parse("2021-10-26T06:30:00"),LocalDateTime.parse("2021-10-26T07:30:00"),user1));
	            appointmentRepository.save(new Appointment("Untersuchung 2 User 1",LocalDateTime.parse("2021-10-27T06:30:00"),LocalDateTime.parse("2021-10-27T07:30:00"),user1));
	        };
	    }

}
