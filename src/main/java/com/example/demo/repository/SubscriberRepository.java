package com.example.demo.repository;


import com.example.demo.model.Subscriber;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;




/**
 * Repository interface.
 * Extending JpaRepository in order to use all the CRUD operations
 * 
 * 
 * @author Greg Maragkoudakis
 * @version 0.1
 */
public interface SubscriberRepository extends JpaRepository<Subscriber, Long> {
	
	/**
	 * JPQL query to filter Subscribers by Surname (keyword 
	 * @param keyword keyword is the surname that the User is going to write in placeholder
	 * 
	 */
	@Query("From Subscriber WHERE lastName LIKE %?1%")
	List<Subscriber> findBySurname(String keyword);
	
	
}
