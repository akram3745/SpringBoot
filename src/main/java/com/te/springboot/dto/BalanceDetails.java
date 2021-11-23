package com.te.springboot.dto;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@JsonIgnoreProperties({ "bid", "customer" })
public class BalanceDetails implements Serializable{
	
	@Id
	@SequenceGenerator(name = "mySeq_BalanceDetails",initialValue = 100,allocationSize = 100)
	@GeneratedValue(generator = "mySeq_BalanceDetails")
	private int bid;
	private double debit;
	private double credit;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
	private Date date;
	private double balance;
	@ManyToOne
	@JoinColumn(name = "userId")
	private Customer customer;
	
	
	public BalanceDetails(double debit, double credit, Date date, double balance, Customer customer) {
		this.debit = debit;
		this.credit = credit;
		this.date = date;
		this.balance = balance;
		this.customer = customer;
	}
	
	

}
