//package com.te.springboot.test;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.Mockito;
//import org.mockito.junit.jupiter.MockitoExtension;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.stereotype.Service;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.setup.MockMvcBuilders;
//
//import com.fasterxml.jackson.core.JsonProcessingException;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import com.te.springboot.dao.BankDaetailReposit;
//import com.te.springboot.dao.CustomerDao;
//import com.te.springboot.dto.Customer;
//import com.te.springboot.dto.Message;
//import com.te.springboot.service.CustomerService;
//import com.te.springboot.service.CustomerServiceImpl;
//
//@ExtendWith(MockitoExtension.class)
//@SpringBootTest
//class CustomerServiceImplTest {
//
//	@InjectMocks
//	private CustomerServiceImpl service;
//	@Mock
//	private BankDaetailReposit details;
//	@Mock
//	private CustomerDao dao;
//	private MockMvc mockmvc;
//	private ObjectMapper mapper;
//
//	@BeforeEach
//	public void setUp() {
//		this.mockmvc = MockMvcBuilders.standaloneSetup(service).build();
//		this.mapper = new ObjectMapper();
//	}
//
////	@Test
////	void testAuthentication() {
////		Customer customer = new Customer("wfgayf", "hjcahg", "hvav", 10, 1000);
////
////		Mockito.when(dao.findByUserId(customer.getUserId())).thenReturn(customer);
////
////		Customer customer2 = (Customer) service.authentication(customer.getUserId(), customer.getPassword()).getData();
////		assertEquals(customer.getName(), customer2.getName());
////
////	}
//
////	@Test
////	void testWithDraw() throws JsonProcessingException {
////		Customer customer = new Customer("wfgayf", "hjcahg", "hvav", 10, 1000);
////
////		System.out.println(customer);
////		Mockito.when(dao.findByUserId(customer.getUserId())).thenReturn(customer);
////		Customer customer2 = (Customer) service.withDraw(1000, customer.getUserId()).getData();
////		System.out.println(customer2);
////		assertEquals(customer.getName(), customer.getName());
////	}
//
////	@Test
////	void testDeposit() {
////		Customer customer = new Customer("wfgayf", "hjcahg", "hvav", 10, 1000);
////
////		System.out.println(customer);
////		Mockito.when(dao.findByUserId(customer.getUserId())).thenReturn(customer);
////		Customer customer2 = (Customer) service.deposit(1000, customer.getUserId()).getData();
////		System.out.println(customer2);
////		assertEquals(customer.getName(), customer2.getName());
////
////	}
//
//	@Test
//	void testShowBalance() {
//		Customer customer = new Customer("wfgayf", "hjcahg", "hvav", 10, 1000);
//
//		Mockito.when(dao.findByUserId(customer.getUserId())).thenReturn(customer);
//		Customer customer2 = (Customer) service.showBalance(customer.getUserId()).getData();
//		System.out.println(customer2.getBalance());
//		assertEquals(customer.getUserId(), customer2.getUserId());
//	}
//
//}
