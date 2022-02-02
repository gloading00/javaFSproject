package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.User;





/**
 * Repository interface.
 * Extending JpaRepository in order to use all the CRUD operations
 * 
 * Please see the link
 * {@link https://docs.spring.io/spring-framework/docs/current/javadoc-api/org/springframework/stereotype/Repository.html}
 * about Repository annotation
 * 
 * 
 * @author Greg Maragkoudakis
 * @version 0.1
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long>{
	
	//Users that are responsible for the administration are distinguished through their email
	User findByEmail(String email);
}
