package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.model.Subscriber;
import com.example.demo.service.SubscriberService;

/**
 * Controller class.
 * Subscribers Table Controller handling the actions about the Subscribers List template
 * 
 *  Please see the 
 *  {@link https://docs.spring.io/spring-framework/docs/current/javadoc-api/org/springframework/stereotype/Controller.html} 
 *  for the Controller annotation
 *  
 *  Please see the
 *  {@link https://docs.spring.io/spring-framework/docs/current/javadoc-api/org/springframework/web/bind/annotation/PostMapping.html}
 *  for the PostMapping annotation
 *  
 *  Please see the
 *  {@link https://docs.spring.io/spring-framework/docs/current/javadoc-api/org/springframework/web/bind/annotation/GetMapping.html}
 *  for the GetMapping annotation
 *  
 * 
 * CRUD operations and methods are used from the Repository
 * 
 * @author Greg Maragkoudakis
 * @version 0.1
 */


@Controller
public class SubscribersTableController {
	
	@Autowired
	private SubscriberService subscriberService;
	
	
	/**
	 *  Handler method for operating the action of showing all the Subscribers from the db
	 *  @param keyword the String that the user gives in the placeholder to filter the Subscribers table
	 */
	@GetMapping("/subscribersTable")
	public String usersTable(Model model, @Param("keyword") String keyword) {
		model.addAttribute("listSubscribers", subscriberService.getAllSubscribers(keyword));
		model.addAttribute("keyword", keyword);
		return "subscribers_table";
	}
	
	
	/*
	 * Handler method for the form that has to do with adding a new Subscriber
	 * Returning the thymeleaf template for adding a new Subscriber
	 */
	@GetMapping("/showNewSubscriberForm")
	public String showNewSubscriberForm(Model model) {
		Subscriber subscriber = new Subscriber();
		model.addAttribute("subscriber", subscriber);
		return "new_subscriber"; 
	}
	
	/*
	 * Handler method for the form that has to do with saving a new Subscriber
	 * Returning the thymeleaf template showing the Main Table after the save action
	 */
	@PostMapping("/saveSubscriber")
	public String saveSubscriber(@ModelAttribute("subscriber") Subscriber subscriber) {
		subscriberService.saveSubscriber(subscriber);
		return "redirect:/subscribersTable"; 
	}
	
	/*
	 * Handler method for the form that has to do with updating an already existing Subscriber
	 * Returning the thymeleaf template showing the Main Table after the update action
	 */
	@GetMapping("/showFormForUpdate/{id}")
	public String showFormForUpdate(@PathVariable ( value = "id") long id, Model model) {
		
		Subscriber subscriber = subscriberService.getSubscriberById(id);
		model.addAttribute("subscriber", subscriber);
		return "update_subscriber";
	}
	
	/*
	 * Handler method for the form that has to do with deleting an already existing Subscriber
	 * Returning the thymeleaf template showing the Main Table after the delete action
	 */
	@GetMapping("/deleteSubscriber/{id}")
	public String deleteSubscriber(@PathVariable (value = "id") long id ) {
		this.subscriberService.deleteSubscriberById(id);
		return "redirect:/subscriberTable";
	}
}
