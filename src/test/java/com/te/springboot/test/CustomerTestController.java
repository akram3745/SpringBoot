package com.te.springboot.test;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;

import java.io.UnsupportedEncodingException;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.te.springboot.controller.CustomerController;
import com.te.springboot.dto.Customer;
import com.te.springboot.dto.Message;
import com.te.springboot.service.CustomerService;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
class CustomerTestController {

	@InjectMocks
	private CustomerController controller;
	@Mock
	private CustomerService service;
	private MockMvc mockmvc;
	private ObjectMapper mapper;

	@BeforeEach
	public void setUp() {
		this.mockmvc = MockMvcBuilders.standaloneSetup(controller).build();
		this.mapper = new ObjectMapper();
	}

//	@SuppressWarnings("unchecked")
//	@Test
//	void testAuthentication() throws UnsupportedEncodingException, Exception {
//		Customer customer = new Customer();
//		customer.setUserId("Akra123");
//		customer.setName("jani");
//		customer.setPassword("3745");
//		Message message = new Message();
//		message.setData(customer);
//		Mockito.when(service.authentication(Mockito.any(), Mockito.any())).thenReturn(message);
//		String jasonString = mapper.writeValueAsString(customer);
//		String resultString = mockmvc
//				.perform(
//						get("/customer/Akr123/3745").contentType(MediaType.APPLICATION_JSON_VALUE).content(jasonString))
//				.andExpect(MockMvcResultMatchers.status().isOk()).andReturn().getResponse().getContentAsString();
//
//		Message message2 = mapper.readValue(resultString, Message.class);
//		System.out.println(message2);
//		Map<String, String> map = (Map<String, String>) message2.getData();
//		System.out.println(map);
//		for (Map.Entry<String, String> mEntry : map.entrySet()) {
//			assertEquals(customer.getName(), mEntry.getValue());
//			break;
//		}
//	}
//
//	@Test
//	void testWithdraw() throws UnsupportedEncodingException, Exception {
//		Customer customer = new Customer();
//		customer.setUserId("Akr123");
//		customer.setName("Akram");
//		customer.setPassword("3745");
//
//		Message message = new Message();
//		message.setData(customer);
//		Mockito.when(service.withDraw(Mockito.anyDouble(), Mockito.any())).thenReturn(message);
//		String jasonString = mapper.writeValueAsString(customer);
//
//		String resultString = mockmvc
//				.perform(put("/withdraw/" + 50000).sessionAttr("start", customer)
//						.contentType(MediaType.APPLICATION_JSON_VALUE).content(jasonString))
//				.andExpect(MockMvcResultMatchers.status().isOk()).andReturn().getResponse().getContentAsString();
//
//		Message message2 = mapper.readValue(resultString, Message.class);
//		Map<String, String> map = (Map<String, String>) message2.getData();
//		for (Map.Entry<String, String> m : map.entrySet()) {
//			assertEquals(customer.getName(), m.getValue());
//			break;
//		}
//
//	}
//
//	@SuppressWarnings("unchecked")
//	@Test
//	void testDeposite() throws UnsupportedEncodingException, Exception {
//		Customer customer = new Customer();
//		customer.setUserId("Akr123");
//		customer.setPassword("3745");
//
//		Message message = new Message();
//		message.setData(customer);
//		Mockito.when(service.deposit(Mockito.anyDouble(), Mockito.any())).thenReturn(message);
//		String jasonString = mapper.writeValueAsString(customer);
//		String resultString = mockmvc
//				.perform(put("/deposite/" + 5000).sessionAttr("start", customer)
//						.contentType(MediaType.APPLICATION_JSON_VALUE).content(jasonString))
//				.andExpect(MockMvcResultMatchers.status().isOk()).andReturn().getResponse().getContentAsString();
//
//		Message message2 = mapper.readValue(resultString, Message.class);
//		Map<String, String> map = (Map<String, String>) message2.getData();
//		for (Map.Entry<String, String> mm : map.entrySet()) {
//			assertEquals(customer.getName(), mm.getValue());
//			break;
//		}
//	}
//
//	@Test
//	void testShowBalance() throws UnsupportedEncodingException, Exception {
//		Customer customer = new Customer();
//		customer.setUserId("Akr123");
//		customer.setPassword("3745");
//		Message message = new Message();
//		message.setData(customer);
//
//		Mockito.when(service.showBalance(Mockito.any())).thenReturn(message);
//		String jasonString = mapper.writeValueAsString(customer);
//		String resultString = mockmvc
//				.perform(get("/show-balance").sessionAttr("start", customer)
//						.contentType(MediaType.APPLICATION_JSON_VALUE).content(jasonString))
//				.andExpect(MockMvcResultMatchers.status().isOk()).andReturn().getResponse().getContentAsString();
//		Message message2 = mapper.readValue(resultString, Message.class);
//		Map<String, String> map = (Map<String, String>) message2.getData();
//		for (Map.Entry<String, String> mm : map.entrySet()) {
//			assertEquals(customer.getName(), mm.getValue());
//			break;
//		}
//	}
//
//	@Test
//	void testLogout() throws UnsupportedEncodingException, Exception {
//		Customer customer = new Customer();
//		customer.setUserId("Akr123");
//		customer.setPassword("3745");
//		Message message = new Message();
//		message.setData(customer);
//
//		String jasonString = mapper.writeValueAsString(customer);
//		String resultString = mockmvc
//				.perform(get("/customer-logout").sessionAttr("start", customer)
//						.contentType(MediaType.APPLICATION_JSON_VALUE).content(jasonString))
//				.andExpect(MockMvcResultMatchers.status().isOk()).andReturn().getResponse().getContentAsString();
//		Message message2 = mapper.readValue(resultString, Message.class);
//		Map<String, String> map = (Map<String, String>) message2.getData();
//		for (Map.Entry<String, String> mm : map.entrySet()) {
//			assertEquals(customer.getName(), mm.getValue());
//			break;
//		}
//	}

}
