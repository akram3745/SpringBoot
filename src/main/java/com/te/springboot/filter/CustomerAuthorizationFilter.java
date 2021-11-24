package com.te.springboot.filter;

import static java.util.Arrays.stream;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.MediaType;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.te.springboot.exception.CustomAccessDeniedException;
import com.te.springboot.service.CustomerServiceImpl;

import lombok.extern.slf4j.Slf4j;

@Slf4j
//@Component
public class CustomerAuthorizationFilter extends OncePerRequestFilter {
	
	//FOLLOW THE LOG STATEMENT YOU WILL COME TO KNOW THE FLOW OF CODE EXECUTION

	private CustomerServiceImpl serviceImpl;

	private CustomAccessDeniedException exception;

	public CustomerAuthorizationFilter(CustomerServiceImpl serviceImpl, CustomAccessDeniedException exception) {
		this.serviceImpl = serviceImpl;
		this.exception = exception;
	}

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		if (request.getServletPath().equals("/api/v1/login")
				|| request.getServletPath().equals("/api/v1/customer/token/refresh")) {
			filterChain.doFilter(request, response);
		} else {
			String header = request.getHeader("Authorization");
			System.out.println(header);
			if (header != null && header.startsWith("Bearer ")) {
				try {
					String token = header.substring(7);
					Algorithm algorithm = Algorithm.HMAC256("secret".getBytes());
					JWTVerifier verifier = JWT.require(algorithm).build();
					DecodedJWT decodedJWT = verifier.verify(token);
					String username = decodedJWT.getSubject();
					if (!serviceImpl.getCustomer().getUsername().equals(username)) {
						try {
							exception.handle(request, response, new AccessDeniedException("UNAUTHORIZED ACCESS TOKEN"));
						} catch (Exception exception2) {
							System.out.println(exception2.getMessage());
						}
					}
					String[] roles = decodedJWT.getClaim("roles").asArray(String.class);
					Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
					stream(roles).forEach(role -> authorities.add(new SimpleGrantedAuthority(role)));
					UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
							username, null, authorities);
					SecurityContextHolder.getContext().setAuthentication(authenticationToken);
					log.info("AFTER SECURITY CONTEXT");
					filterChain.doFilter(request, response);
				} catch (Exception exception2) {

				 exception.handle(request, response, new AccessDeniedException("YOUR TOKEN IS EXPIRED"));

				}
			} else {
				log.info("AFTER TRY CATCH BLOCK IN AUTHORIZATION CLASS");
				filterChain.doFilter(request, response);
			}
		}
	}

}
