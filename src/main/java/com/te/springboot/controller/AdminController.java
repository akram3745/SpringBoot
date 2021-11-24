package com.te.springboot.controller;

import java.util.Date;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttribute;

import com.te.springboot.dto.Admin;
import com.te.springboot.dto.Message;
import com.te.springboot.exception.CustumException;
import com.te.springboot.service.AdminService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping(path = "/api/v1/admin/")
public class AdminController {

	@Autowired
	private AdminService service;


	@GetMapping(path = "/customers")
	@ApiOperation(value = "All customers", notes = "All customers", tags = "SpringBoot_Assessment2.0")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Fetch Successfully"),
			@ApiResponse(code = 404, message = "No Customer Found"),
			@ApiResponse(code = 403, message = "Access Denied") })
	public ResponseEntity<Message> getAllCustomer() {
		return new ResponseEntity<Message>(service.getCustomers(), HttpStatus.OK);
	}

}
