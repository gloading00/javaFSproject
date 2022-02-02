package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Subscriber;
import com.example.demo.repository.SubscriberRepository;



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
public class SubscriberServiceImpl implements SubscriberService{
	
	
	//injecting repo object to use the methods from Jpa Repo
	@Autowired
	SubscriberRepository subscriberRepository;
	


	// Returning all the Subscribers using keyword parameter (filtering Surnames)
	@Override
	public List<Subscriber> getAllSubscribers(String keyword) {
		if (keyword != null) {
			return subscriberRepository.findBySurname(keyword);
		}
		return subscriberRepository.findAll();
	}
	
	
	//Saving method for every subscriber being added
	@Override
	public void saveSubscriber(Subscriber subscriber) {
		this.subscriberRepository.save(subscriber);
		
	}
	
	
	// Returning Subscriber that has the specific Id
	@Override
	public Subscriber getSubscriberById(long id) {
		Optional<Subscriber> optional = subscriberRepository.findById(id);
		Subscriber subscriber = null;
		if (optional.isPresent()) {
			subscriber = optional.get();
		} else {
			throw new RuntimeException(" Subscriber not found for id :: " + id);
		}
		return subscriber;
	}
	
	
	//deleting an existed Subscriber
	@Override
	public void deleteSubscriberById(long id) {
		this.subscriberRepository.deleteById(id);
		
	}

	

}


