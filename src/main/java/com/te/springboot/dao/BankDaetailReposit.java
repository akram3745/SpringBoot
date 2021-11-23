package com.te.springboot.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.te.springboot.dto.BalanceDetails;
@Repository
public interface BankDaetailReposit extends JpaRepository<BalanceDetails, Integer> {

}
