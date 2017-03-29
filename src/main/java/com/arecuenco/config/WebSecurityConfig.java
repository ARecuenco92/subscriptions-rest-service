package com.arecuenco.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Bean
	public UserDetailsService userDetailsService() {
		InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
		manager.createUser(User.withUsername("admin").password("admin").roles("ADMIN").build());
		return manager;
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable();
		http.authorizeRequests()
				.antMatchers("/api/subscription").permitAll()
				.antMatchers(HttpMethod.GET, "/api/event").permitAll()
				.antMatchers(HttpMethod.GET, "/api/event/**").permitAll()
			.and()
				.antMatcher("/api/**")
				.authorizeRequests()
				.anyRequest()
				.hasRole("ADMIN")
				.and()
				.httpBasic();
	}
	
}
