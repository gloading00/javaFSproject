package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Controller class.
 * Index Controller handling the first thymleaf template after Login
 * 
 *  Please see the 
 *  {@link https://docs.spring.io/spring-framework/docs/current/javadoc-api/org/springframework/stereotype/Controller.html} 
 *  for the annotation
 * 
 * @author Greg Maragkoudakis
 * @version 0.1
 */


@Controller
@RequestMapping("/index")
public class IndexController {
	
	@GetMapping
	public String main() {
		return "index";
	}
	
	@GetMapping("/subscriptions")
	public String subscriptions() {
		return "subscriptions";
	}
	
	
	
}
