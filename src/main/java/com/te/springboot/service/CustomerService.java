package com.te.springboot.service;

import com.te.springboot.dto.Customer;
import com.te.springboot.dto.Message;

public interface CustomerService {

//	Message authentication(String user_id, String password);

	Message withDraw(double amount);

	Message deposite(double amount);

	Message showBalance();
	
	Customer findByUserName(String userName);
	
//	Message add(Customer customer);
	/*
	 * Message getAll();
	 */
}
