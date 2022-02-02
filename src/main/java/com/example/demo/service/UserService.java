package com.example.demo.service;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.example.demo.model.User;
import com.example.demo.model.UserRegistrationDto;



/**
 * Service interface.
 * To be implemented by Service class
 * 
 * 
 * @author Greg Maragkoudakis
 * @version 0.1
 */
public interface UserService extends UserDetailsService{
	
	//method to be implemented by Service class
	User save(UserRegistrationDto registrationDto);
}
