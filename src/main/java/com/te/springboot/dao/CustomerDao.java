package com.te.springboot.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.te.springboot.dto.Customer;
@Repository
public interface CustomerDao extends JpaRepository<Customer, String> {
	
	Customer findByUsername(String username);

}
