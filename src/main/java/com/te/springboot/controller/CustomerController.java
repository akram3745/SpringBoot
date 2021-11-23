package com.te.springboot.controller;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;
import static org.springframework.http.HttpStatus.FORBIDDEN;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttribute;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.te.springboot.dto.Customer;
import com.te.springboot.dto.Message;
import com.te.springboot.exception.CustumException;
import com.te.springboot.service.CustomerService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.extern.slf4j.Slf4j;


@RestController
@RequestMapping("/secure/customer/")
@Api(value = "/secure/customer", tags = "SpringBoot_Assessment2.0")
public class CustomerController {

	@Autowired
	private CustomerService service;

	private static final Logger LOGGER = LoggerFactory.getLogger(CustomerController.class);

	@PutMapping(path = "/withdraw/{amount}")
	@ApiOperation(value = "WithDraw Money", notes = "Withdraw Money",tags = "SpringBoot_Assessment2.0")
	@ApiResponses(value = {@ApiResponse(code = 200,message = "Withdraw Successfully"),
			@ApiResponse(code = 404, message = "INVALID CUSTOMER ID"),
			@ApiResponse(code = 403, message = "Access Denied")})
	public ResponseEntity<Message> withdraw(@PathVariable("amount") double amount) {
		LOGGER.info(amount + "Amount Successfully Withdraw  ");
		return new ResponseEntity<Message>(service.withDraw(amount), HttpStatus.OK);
	}

	@PutMapping(path = "/deposite/{amount}")
	@ApiOperation(value = "Deposite Money", notes = "Deposite Money",tags = "SpringBoot_Assessment2.0")
	@ApiResponses(value = {@ApiResponse(code = 200,message = "Deposite Successfully"),
			@ApiResponse(code = 404, message = "INVALID CUSTOMER ID"),
			@ApiResponse(code = 403, message = "Access Denied")})
	public ResponseEntity<Message> deposite(@PathVariable("amount") double amount) {
		LOGGER.info(amount + "Amount Successfully Deposited  ");
		return new ResponseEntity<Message>(service.deposite(amount), HttpStatus.OK);
	}

	@GetMapping("/balance")
	@ApiOperation(value = "Total Money", notes = "Total Money",tags = "SpringBoot_Assessment2.0")
	@ApiResponses(value = {@ApiResponse(code = 200,message = "Fetch Successfully"),
			@ApiResponse(code = 404, message = "INVALID CUSTOMER ID"),
			@ApiResponse(code = 403, message = "Access Denied")})
	public ResponseEntity<Message> getBalance() {
		return new ResponseEntity<Message>(service.showBalance(), HttpStatus.OK);
	}

	
	
	@GetMapping("/token/refresh")
	@ApiOperation(value = "Generate new Access token", notes = "Generate new Access token",tags = "SpringBoot_Assessment2.0")
	@ApiResponses(value = {@ApiResponse(code = 200,message = "Generate new 	Access token Successfully"),
			@ApiResponse(code = 404, message = "Refresh token is missing "),
			@ApiResponse(code = 403, message = "Access Denied")})
	public void refreashToken(HttpServletRequest request, HttpServletResponse response) throws JsonGenerationException, JsonMappingException, IOException {
		String header = request.getHeader(AUTHORIZATION);
		if (header != null && header.startsWith("Bearer ")) {
			try {
				String refresh_token = header.substring("Bearer ".length());
				Algorithm algorithm = Algorithm.HMAC256("secret".getBytes());
				JWTVerifier verifier = JWT.require(algorithm).build();
				DecodedJWT decodedJWT = verifier.verify(refresh_token);
				String username = decodedJWT.getSubject();
				Customer customer = service.findByUserName(username);
				String access_token = JWT.create().withSubject(customer.getUsername())
						.withExpiresAt(new Date(System.currentTimeMillis() + 10 * 60 * 1000))
						.withIssuer(request.getRequestURI().toString())
						.withClaim("roles",new ArrayList<>().add(new SimpleGrantedAuthority("USER")))
						.sign(algorithm);
				HashMap<String, String> tokens = new HashMap<>();
				tokens.put("access_token", access_token);
				tokens.put("refresh_token", refresh_token);
				response.setContentType(MediaType.APPLICATION_JSON_VALUE);
				new ObjectMapper().writeValue(response.getOutputStream(), tokens);
			} catch (Exception exception) {
				response.setHeader("error", exception.getMessage());
				response.setStatus(FORBIDDEN.value());
				HashMap<String, String> error = new HashMap<>();
				error.put("error", exception.getMessage());
				response.setContentType(MediaType.APPLICATION_JSON_VALUE);
				new ObjectMapper().writeValue(response.getOutputStream(), error);
			}
		} else
			throw new RuntimeException("Refresh token is missing");
		
		
	}


}
