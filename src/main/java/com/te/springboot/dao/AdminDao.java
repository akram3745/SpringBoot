package com.te.springboot.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.te.springboot.dto.Admin;

@Repository
public interface AdminDao extends JpaRepository<Admin, String> {

	Admin findByUsername(String username);

}
