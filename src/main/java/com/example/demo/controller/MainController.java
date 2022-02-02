package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;



/**
 * Controller class.
 * Main Controller handling Login template
 * 
 *  Please see the 
 *  {@link https://docs.spring.io/spring-framework/docs/current/javadoc-api/org/springframework/stereotype/Controller.html} 
 *  for the annotation
 * 
 * @author Greg Maragkoudakis
 * @version 0.1
 */

@Controller
public class MainController {
	
	@GetMapping("/login")
	public String login() {
		return "login";
	}
	
	@GetMapping("/")
	public String home() {
		return "index";
	}
}
