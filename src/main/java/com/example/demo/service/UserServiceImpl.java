package com.example.demo.service;

import java.util.Arrays;
import java.util.Collection;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.demo.model.Role;
import com.example.demo.model.User;
import com.example.demo.model.UserRegistrationDto;
import com.example.demo.repository.UserRepository;





/**
 * Implementation Service class.
 * Class uses CRUD operations from Jpa Repository to complete the actions
 * 
 *  Please see the 
 *  {@link https://docs.spring.io/spring-framework/docs/current/javadoc-api/org/springframework/stereotype/Service.html} 
 *  for the Service annotation
 *  
 *  Please see the 
 *  {@link https://docs.spring.io/spring-framework/docs/current/javadoc-api/org/springframework/beans/factory/annotation/Autowired.html} 
 *  for the Autowired annotation
 * 
 * @author Greg Maragkoudakis
 * @version 0.1
 */


@Service
public class UserServiceImpl implements UserService{
	
	private UserRepository userRepository;
	
	
	//injecting object for the password encryption
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	
	

	public UserServiceImpl(UserRepository userRepository) {
		super();
		this.userRepository = userRepository;
	}
	
	/*
	 * Method for saving new Users through the DTO object
	 * 
	 * Parsing the password through the encode method
	 * 
	 */
	@Override
	public User save(UserRegistrationDto registrationDto) {
		User user = new User(registrationDto.getFirstName(), 
				registrationDto.getLastName(), registrationDto.getEmail(),
				passwordEncoder.encode(registrationDto.getPassword()), Arrays.asList(new Role("ROLE_USER")));
		
		return userRepository.save(user);
	}
	
	
	//Method for finding and showing which user has loaded to the app
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
	
		User user = userRepository.findByEmail(username);
		if(user == null) {
			throw new UsernameNotFoundException("Invalid username or password.");
		}
		return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), mapRolesToAuthorities(user.getRoles()));		
	}
	
	
	//Method to give the loaded User the Authorities through a stream and a lambda function
	private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Collection<Role> roles){
		return roles.stream().map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());
	}

	
}
