package com.example.demo.service;

import java.util.List;

import com.example.demo.model.Subscriber;


/**
 * Service interface.
 * To be implemented by Service class
 * 
 * 
 * @author Greg Maragkoudakis
 * @version 0.1
 */
public interface SubscriberService {
	
	// methods to be implemented
	List<Subscriber> getAllSubscribers(String keyword);
	void saveSubscriber(Subscriber subscriber);
	Subscriber getSubscriberById(long id);
	void deleteSubscriberById(long id);
}
