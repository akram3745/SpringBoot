package com.te.springboot.filter;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.stream.Collectors;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CustumAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

	public CustumAuthenticationFilter(AuthenticationManager authenticationManager) {
		this.authenticationManager = authenticationManager;
	}

	@Autowired
	private final AuthenticationManager authenticationManager;

	/*
	 * public CustomAuthenticattionFilter(AuthenticationManager
	 * authenticationManager) { this.authenticationManager = authenticationManager;
	 * }
	 */
	private String username;

	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
			throws AuthenticationException {
		username = request.getParameter("username");
		String password = request.getParameter("password");
		log.info("Username is " + username + " And Password Is " + password);
		UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(username,
				password);
		System.out.println(authenticationToken);
		Authentication authenticate = authenticationManager.authenticate(authenticationToken);
		System.out.println(authenticate);
		return authenticate;
	}

	@Override
	protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
			Authentication authentication) throws IOException, ServletException {
		System.out.println("hello");
		User user = (User) authentication.getPrincipal();
		System.out.println(user);
		Algorithm algorithm = Algorithm.HMAC256("secret".getBytes());
		String access_token = JWT.create().withSubject(user.getUsername())
				.withExpiresAt(new Date(System.currentTimeMillis() + 10 * 60 * 1000))
				.withIssuer(request.getRequestURI().toString())
				.withClaim("roles",
						user.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList()))
				.sign(algorithm);

		String refresh_token = JWT.create().withSubject(user.getUsername())
				.withExpiresAt(new Date(System.currentTimeMillis() + 30 * 60 * 1000))
				.withIssuer(request.getRequestURI().toString()).sign(algorithm);
		LinkedHashMap<String, Object> messsage = new LinkedHashMap();
		LinkedHashMap<String, Object> tokens = new LinkedHashMap();
		messsage.put("error", false);
		messsage.put("message", username + " LOGGED IN SUCCESFULL");
		tokens.put("access_token", access_token);
		tokens.put("refresh_token", refresh_token);
		messsage.put("data", tokens);
		response.setContentType(MediaType.APPLICATION_JSON_VALUE);
		new ObjectMapper().writeValue(response.getOutputStream(), messsage);
	}

}
