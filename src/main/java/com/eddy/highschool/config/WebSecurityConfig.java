package com.eddy.highschool.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	private UserDetailsService userDetailsService;
	
	public WebSecurityConfig(UserDetailsService userDetailsService) {
		this.userDetailsService = userDetailsService;
	}
	
	/*
	 * Allows restricting access based upon HttpServletRequest
	 * .anyRequest() maps any request
	 * .autheticated() Specify URLS that are allowed by authenticated users only
	 * .formLogin() Specifies to support form based authentication
	 * .logout() will logout the user by invalidating the HTTP Session, cleaning up any rememberMe() authentication that was configured, clearing the SecurityContextHolder, and then redirect to "/login?success"
	 */
	
	@Override
	protected void configure(HttpSecurity http) throws Exception{
		http.
			authorizeRequests()
			//.antMatchers("/css/**","/js/**","/student/registration","/teacher/registration").permitAll()
				.antMatchers("/css/**","/js/**").permitAll()
				.antMatchers("/admin/**").access("hasRole('ADMIN')")
				.antMatchers("/student/**").access("hasRole('STUDENT')")
				.antMatchers("/teacher/**").access("hasRole('TEACHER')")
				.anyRequest().authenticated()
				.and()
			.formLogin()
				.loginPage("/login")
				.permitAll()
				.and()
			.logout()
				.permitAll();
	}
	
	/*
	 * Spring beans are just object instances that are managed by the Spring container, namely, they are created and wired by te framework and put into a "container"(IoC) for later use
	 */
	@Bean
	public BCryptPasswordEncoder bCrypt() {
		return new BCryptPasswordEncoder();
	}
	
	
	@Autowired
	public void configGlobal(AuthenticationManagerBuilder auth) throws Exception {
		//ensure passwords are encoded properly
		auth
			.userDetailsService(userDetailsService)
			.passwordEncoder(bCrypt());
	}
}
