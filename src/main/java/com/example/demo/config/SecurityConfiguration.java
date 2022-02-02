package com.example.demo.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.example.demo.service.UserService;


/**
 * Security and Authentication class.
 * Giving entrance to authenticated users, only with the right credentials.
 * 
 *
 * 
 * @author Greg Maragkoudakis
 * @version 0.1
 */


@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

	@Autowired
	private UserService userService;
	
	
	// Creating object for password encoding
	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	   }
	
	// Data Access Object method , setting the password using the passwordEncoder() method above
	@Bean
	  public DaoAuthenticationProvider authenticationProvider() {
	       DaoAuthenticationProvider auth = new DaoAuthenticationProvider();
	       auth.setUserDetailsService((UserDetailsService) userService);
	       auth.setPasswordEncoder(passwordEncoder());
	       return auth;
	   }
		
	@Override
	   protected void configure(AuthenticationManagerBuilder auth) throws Exception {
	       auth.authenticationProvider(authenticationProvider());
	   }
		
	
	
	/*
	 * Method that configures the requests and setting the Login page as the default one
	 * It is also configures the permits that an authenticated user has
	 */
	@Override
	protected void configure(HttpSecurity http) throws Exception {
			http.authorizeRequests().antMatchers(
					"/registration**",
		               "/js/**",
		               "/css/**",
		               "/img/**").permitAll()
		.anyRequest().authenticated()
		.and()
		.formLogin()
		.loginPage("/login")
		.permitAll()
		.and()
		.logout()
		.invalidateHttpSession(true)
		.clearAuthentication(true)
		.logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
		.logoutSuccessUrl("/login?logout")
		.permitAll();
	}

}
