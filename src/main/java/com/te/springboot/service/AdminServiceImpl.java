package com.te.springboot.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.te.springboot.dao.AdminDao;
import com.te.springboot.dao.CustomerDao;
import com.te.springboot.dto.Admin;
import com.te.springboot.dto.Customer;
import com.te.springboot.dto.CustomerDto;
import com.te.springboot.dto.Message;
import com.te.springboot.exception.CustumException;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class AdminServiceImpl implements AdminService {

	@Autowired
	private CustomerDao customerDao;

	private static final Logger LOGGER = LoggerFactory.getLogger(AdminServiceImpl.class);

	@Override
	public Message getCustomers() {
		int index = 0;
		List<CustomerDto> customerDtos = new ArrayList<>();
		List<Customer> customers = customerDao.findAll();
		for (int i = 0; i < customers.size() - 1; i++) {

			customerDtos.add(new CustomerDto(customers.get(i).getUsername(), customers.get(i).getAccontNo()));
		}
		LOGGER.info("SUCCESSFULLY FETCH");
		return new Message(HttpStatus.OK.value(), new Date(), false, "Successfully Fatched  ", customerDtos);

	}

}
