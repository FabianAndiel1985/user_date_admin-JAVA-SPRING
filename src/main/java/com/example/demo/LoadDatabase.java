package com.example.demo;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class LoadDatabase {
  private static final Logger log = LoggerFactory.getLogger(LoadDatabase.class);

  @Bean
  CommandLineRunner initDatabase(UserRepository userRepository, AppointmentRepository appointmentRepository) {
	  
	  User user1 = new User("Fabian Andiel");
	  User user2 = new User("User2");
	  
	  Appointment appointment1 = new Appointment("1.Untersuchung", LocalDateTime.parse("2021-10-21T06:30:00"), LocalDateTime.parse("2021-10-21T07:30:00"), user1);
	  Appointment appointment2 = new Appointment("2.Untersuchung", LocalDateTime.parse("2021-10-22T06:30:00"), LocalDateTime.parse("2021-10-22T07:30:00"), user1);
	  
	  List<Appointment> list = new ArrayList<>();
	  list.add(appointment1);
	  list.add(appointment2);
	  
//	  user1.setAppointments(list);
	  
	  
    return args -> {
    	 log.info("Preloading " + userRepository.save(user1));
    	 log.info("Preloading " + userRepository.save(user2));
    	log.info("Preloading " + appointmentRepository.save(appointment1));
        log.info("Preloading " + appointmentRepository.save(appointment2));
       
    };
  }
}