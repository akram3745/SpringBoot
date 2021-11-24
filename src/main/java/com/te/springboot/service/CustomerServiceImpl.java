package com.te.springboot.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.te.springboot.dao.AdminDao;
import com.te.springboot.dao.BankDaetailReposit;
import com.te.springboot.dao.CustomerDao;
import com.te.springboot.dto.Admin;
import com.te.springboot.dto.BalanceDetails;
import com.te.springboot.dto.Customer;
import com.te.springboot.dto.Message;
import com.te.springboot.exception.CustumException;

@Service
public class CustomerServiceImpl implements CustomerService, UserDetailsService {

	@Autowired
	private CustomerDao customerDao;
	@Autowired
	private BankDaetailReposit reposit;
	@Autowired
	private AdminDao adminDao;
	private static Customer customer;
	private int count;

	private static final Logger LOGGER = LoggerFactory.getLogger(CustomerServiceImpl.class);

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		LOGGER.info("EIGTH STEP LOAD METHOD EXCUTE ACCORDING TO THE  USERNAME YOU PROVIDE RETURN IT BACK TO THE INBUILT USER");
		Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
		if (!username.equals("Bharath")) {
			customer = customerDao.findByUsername(username);
			if (customer == null) {
				LOGGER.error("User Not in Data Base");
				throw new CustumException("Please Enter your Correct User Name");
			}
			authorities.add(new SimpleGrantedAuthority("USER"));
			LOGGER.info("return Load User Method Serive");
			return new User(customer.getUsername(), customer.getPassword(), authorities);
		} else {
			LOGGER.info("in else Load User Method Serive");
			Admin admin = adminDao.findByUsername(username);
			if (admin == null) {
				LOGGER.error("User Not in Data Base");
				throw new CustumException("Please Enter your Correct User Name");
			}
			LOGGER.info("before authoriti User Method Serive");
			authorities.add(new SimpleGrantedAuthority("ADMIN"));
			LOGGER.info("After authorities ALoad User Method Serive");
			User user = new User(admin.getUsername(), admin.getPassword(), authorities);
			System.out.println(user);
			LOGGER.info("return user Load User Method Serive");
			return user;
		}
	}

	@Override
	public Customer findByUserName(String userName) {
		Customer customer = (Customer) customerDao.findByUsername(userName);
		LOGGER.info("Successfully Logged in " + userName);
		return customer;
	}

	@Override
	public Message withDraw(double amount) {
		LOGGER.info("WITHDRAW METHOD IS RUNNING");

		if (count < 3) {
			if (amount % 100 == 0) {
				if (customer.getBalance() - amount * (1 + 0.0045) > 1000) {
					customer.setBalance(customer.getBalance() - amount * (1 + 0.0045));
				} else if (customer.getBalance() - amount * (1 + 0.0045) < 1000) {
					LOGGER.error("YOUR EXCEDDING THE MINIMUM BALANCE");
					throw new CustumException("YOUR EXCEDDING THE MINIMUM BALANCE");
				}
				customerDao.save(customer);
				reposit.save(new BalanceDetails(amount, 0, new Date(), customer.getBalance(), customer));
				count++;
				LOGGER.info("SUCCESSFULL WITHDRAW OF AMOUNT :" + amount + "  AND CURRENT BALANCE IS : "
						+ customer.getBalance());
				Customer customer2 = (Customer) customerDao.findByUsername(customer.getUsername());
				return new Message(HttpStatus.OK.value(), new Date(), false, amount + " AMOUNT SUCCESSFULLY WITHDRAW  ",
						customer2);

			}
			LOGGER.error("ONLY MULTIPLE's OF 100 ALLOWED TO WITHDRAW");
			throw new CustumException("ONLY MULTIPLE's OF 100 ALLOWED TO WITHDRAW");

		}
		LOGGER.error("YOUR CANNOT WITHDRAW MORE THAN THREE TIMES IN MONTH");
		throw new CustumException("YOUR CANNOT WITHDRAW MORE THAN THREE TIMES IN MONTH");

	}

	@Override
	public Message deposite(double amount) {

		LOGGER.info("DEPOSITE METHOD IS RUNNING");
		if (amount <= 100000) {
			if (amount % 100 == 0) {
				customer.setBalance(customer.getBalance() + amount * (1 - 0.00026));
				customerDao.save(customer);
				reposit.save(new BalanceDetails(0, amount, new Date(), customer.getBalance(), customer));
				LOGGER.info("DEPOSITE IS SUCCESFULL OF AMOUNT : " + amount + " AND CURRENT BALANCE IS : "
						+ customer.getBalance());
				Customer customer2 = customerDao.findByUsername(customer.getUsername());
				return new Message(HttpStatus.OK.value(), new Date(), false, amount + " AMOUNT SUCCESSFULLY DEPOSITE ",
						customer2);
			}
			LOGGER.error("ONLY MULTIPLE's OF 100 ALLOWED TO DEPOSITE");
			throw new CustumException("ONLY MULTIPLE's OF 100 ALLOWED TO DEPOSITE");

		}
		LOGGER.error("MAXIMUM LIMIT TO DEPOSITE IS 1 LAKH IN A DAY");
		throw new CustumException("MAXIMUM LIMIT TO DEPOSITE IS 1 LAKH IN A DAY");

	}

	@Override
	public Message showBalance() {
		LOGGER.info("SHOW-BALANCE METHOD IS RUNNING");
		if (customer == null || customer.getUsername() == null) {
			throw new CustumException("Please Login First!!!");
		}
		System.out.println("Service");
		Customer customer2 = (Customer) customerDao.findByUsername(customer.getUsername());
		return new Message(HttpStatus.OK.value(), new Date(), false, "Your Balance is : " + customer2.getBalance(),
				customer2);
	}
     
	public Customer getCustomer() {
		return customer;
	}

}
