package com.grupo2.backend;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties.User;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableMethodSecurity(prePostEnabled = true)
public class SecurityConfig {

	@Autowired
	  private AppBasicAuthenticationEntryPoint authenticationEntryPoint;

	  
	  @Bean
	  public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		  
	    http.csrf(Customizer.withDefaults()).disable().authorizeRequests()
	        .antMatchers(HttpMethod.GET		, "/producto/**").permitAll()
	        .antMatchers(HttpMethod.POST	, "/producto/**").permitAll()
	        .antMatchers(HttpMethod.PUT		, "/producto/**").permitAll()
	        .antMatchers(HttpMethod.DELETE	, "/producto/**").permitAll()
	        .anyRequest().authenticated()
	        
	        .and()
	        .formLogin()
	        .loginPage("/login")
	        .permitAll()
	        .defaultSuccessUrl("/menu/REST")
	        .usernameParameter("admin")
	        .passwordParameter("password")
	        .and()
	        .logout()
	        .permitAll()
	        .logoutSuccessUrl("/home/REST")
	        
	        .and()
	        .httpBasic()
	        .authenticationEntryPoint(authenticationEntryPoint);
	    return http.build();
	  }

	  
	  
	  @Bean
	  public InMemoryUserDetailsManager userDetailsService() {

		  UserDetails admin = User
			        .withUsername("admin")
			        .password(passwordEncoder().encode("password"))
			        .roles("ADMIN")
			        .build();
		  
		  UserDetails user = User
	        .withUsername("user")
	        .password(passwordEncoder().encode("password"))
	        .roles("USER")
	        .build();

		  
	    InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
	    manager.createUser(admin);
	    manager.createUser(user);
	    
	    return manager;
	  }

	  
	  
	  @Bean
	  public PasswordEncoder passwordEncoder() {
	    return new BCryptPasswordEncoder(8);