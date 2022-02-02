package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;



/**
 * Controller class.
 * Subscriptions Controller handling Subscriptions choices template
 * 
 *  Please see the 
 *  {@link https://docs.spring.io/spring-framework/docs/current/javadoc-api/org/springframework/stereotype/Controller.html} 
 *  for the annotation
 * 
 * @author Greg Maragkoudakis
 * @version 0.1
 */
@Controller
public class SubscriptionsController {
	
	@GetMapping("/subscriptions")
	public String subs() {
		return "subscriptions";
	}
}
