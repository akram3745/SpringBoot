package com.te.springboot.service;

import com.te.springboot.dto.Admin;
import com.te.springboot.dto.Message;

public interface AdminService {
	
	//Message authentication(String user_id, String password);

	Message getCustomers();

}
