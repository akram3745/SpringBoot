package com.te.springboot.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.google.common.util.concurrent.Service;
import com.te.springboot.exception.CuntomAuthenticationEntryPoint;
import com.te.springboot.exception.CustomAccessDeniedException;
import com.te.springboot.filter.CustomerAuthorizationFilter;
import com.te.springboot.filter.CustumAuthenticationFilter;
import com.te.springboot.service.CustomerServiceImpl;

import lombok.extern.java.Log;
import lombok.extern.slf4j.Slf4j;
@Slf4j
@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

	@Autowired
	private UserDetailsService userDetailsService;
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	
	@Autowired
	private CustomerServiceImpl serviceImpl;

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder);
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		CustumAuthenticationFilter authenticattionFilter = new CustumAuthenticationFilter(authenticationManagerBean()
				,new CustomAccessDeniedException());
		CustomerAuthorizationFilter authorizationFilter = new CustomerAuthorizationFilter(serviceImpl,new CustomAccessDeniedException());
		authenticattionFilter.setFilterProcessesUrl("/api/v1/login");
		http.csrf().disable();
		http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
		http.authorizeRequests().antMatchers("/api/v1/login/**", "/api/v1/customer/token/refresh/**",
				"/swagger-resources/configuration/ui", "/swagger-resources",
				"/swagger-resources/configuration/security", "/swagger-ui.html", "/webjars/**");
		http.authorizeRequests().antMatchers("/api/v1/customer/**").hasAnyAuthority("USER");
		http.authorizeRequests().antMatchers("/api/v1/admin/**").hasAnyAuthority("ADMIN");
		http.authorizeRequests().anyRequest().authenticated();
		http.exceptionHandling().authenticationEntryPoint(new CuntomAuthenticationEntryPoint());
		http.addFilter(authenticattionFilter);
		http.addFilterBefore(authorizationFilter, CustumAuthenticationFilter.class);
	}

	@Override
	@Bean
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}

	@Bean
	public JWTConfig config() {
		return new JWTConfig();
	}

	@Override
	public void configure(WebSecurity web) throws Exception {
		log.info("THIRD STEP TO MAPPED THE GIVEN MATTCHERS");
		web.ignoring().antMatchers("/v2/api-docs", "/swagger-resources/configuration/ui", "/swagger-resources",
				"/swagger-resources/configuration/security", "/swagger-ui.html", "/webjars/**");
	}

}
