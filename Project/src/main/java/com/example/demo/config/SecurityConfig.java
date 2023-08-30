package com.example.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
//		http.authorizeRequests().requestMatchers("/restaurant/admin/**").hasRole("ADMIN")
//				.requestMatchers("/protected/**").hasRole("USER");
//		http.formLogin().permitAll();
//		http.formLogin().defaultSuccessUrl("/restaurant/menu");
//		return http.build();
		
		http.authorizeRequests()
        .requestMatchers("/books/**").hasRole("ADMIN")
        .requestMatchers("/members/**").hasRole("ADMIN")
        .requestMatchers("/26/**").hasRole("USER");
        
        http.formLogin()
        .permitAll()
        .defaultSuccessUrl("/books");
       
        http.logout()
        .logoutSuccessUrl("/home")
        .permitAll();
    return http.build();
	}

	@Bean
	public InMemoryUserDetailsManager userDetailsService() {
		UserDetails user = User.withUsername("user").password(passwordEncoder().encode("password")).roles("USER")
				.build();
		UserDetails admin = User.withUsername("admin").password(passwordEncoder().encode("password")).roles("ADMIN")
				.build();
		return new InMemoryUserDetailsManager(user, admin);
	}

	@Bean
	public WebSecurityCustomizer ignoreResources() {
		//return (webSecurity) -> webSecurity.ignoring().requestMatchers("/permitted/*");
		return (webSecurity) -> webSecurity.ignoring().requestMatchers("/restaurant/menu");
	}
	
	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
}