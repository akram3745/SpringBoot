//package com.te.springboot.test;
//
//import static org.junit.jupiter.api.Assertions.*;
//import static org.mockito.Mockito.when;
//
//import java.util.ArrayList;
//import java.util.List;
//import java.util.stream.Collectors;
//import java.util.stream.Stream;
//
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.Mockito;
//import org.mockito.internal.matchers.Equals;
//import org.mockito.junit.jupiter.MockitoExtension;
//import org.springframework.boot.test.context.SpringBootTest;
//
//import com.te.springboot.dao.AdminDao;
//import com.te.springboot.dao.CustomerDao;
//import com.te.springboot.dto.Admin;
//import com.te.springboot.dto.Customer;
//import com.te.springboot.dto.Message;
//import com.te.springboot.service.AdminServiceImpl;
//
//@ExtendWith(MockitoExtension.class)
//@SpringBootTest
//class AdminServiceImplTest {
//
//	@InjectMocks
//	AdminServiceImpl service;
//	@Mock
//	private CustomerDao customerDao;
//
//	@Mock
//	private AdminDao admindao;
//
//	private Admin admin;
//	Message findByUserId;
//
//	/*
//	 * @Test void testAuthentication() {
//	 * 
//	 * Admin admin = new Admin(); admin.setUserId("akr123");
//	 * admin.setPassword("3745");
//	 * when(admindao.findByUserId(admin.getUserId())).thenReturn(admin); Admin
//	 * admin1 = (Admin) service.authentication(admin.getUserId(),
//	 * admin.getPassword()).getData(); assertNotNull(admin1);
//	 * assertEquals(admin.getUserId(), admin1.getUserId()); }
//	 */
//
//	@SuppressWarnings("unchecked")
//	@Test
//	void testGetCustomers() {
//		admin = new Admin();
//		admin.setUserId("akr123");
//		admin.setPassword("3745");
//		List<Customer> list = new ArrayList<Customer>();
//		Customer customer = new Customer("jhasjhca", "hjbah", "hsfsj", 010, 5000);
//		Customer customer2 = new Customer("hsdv", "hajhasb", "hgcvahca", 10, 500);
//		list.add(customer2);
//		list.add(customer);
//		Mockito.when(customerDao.findAll()).thenReturn(list);
//		Message message = service.getCustomers(admin);
//		ArrayList<Customer> customers = (ArrayList<Customer>) message.getData();
//
//		assertEquals(customer.getUserId(), customers.get(1).getUserId());
//		assertEquals(customer2.getUserId(), customers.get(0).getUserId());
//
//	}
//
//}
