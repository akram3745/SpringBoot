package com.te.springboot.dto;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@SuppressWarnings("serial")
@Data
@Entity
@NoArgsConstructor
@JsonIgnoreProperties({"user_Id", "password"})
@Table(name = "Customer_Table")
public class Customer implements Serializable {

	@Id
	@Column(name = "Customer_UserId")
	private String userId;
	@Column(name = "Customer_Name")
	private String username;
	@Column(name = "Customer_Password")
	private String password;
	@Column(name = "Customer_AccountNumber")
	private long accontNo;
	@Column(name = "Customer_Balance")
	private double balance;
	@OneToMany(mappedBy = "customer")
	private List<BalanceDetails> details;
	
	
	public Customer(String username, String password) {
		super();
		this.username = username;
		this.password = password;
	}


	public Customer( String userId, String name, String password, long accontNo, double balance) {
		super();
		this.userId = userId;
		this.username = name;
		this.password = password;
		this.accontNo = accontNo;
		this.balance = balance;

	}


	@Override
	public String toString() {
		return "Customer [userId=" + userId + ", username=" + username + ", password=" + password + ", accontNo="
				+ accontNo + ", balance=" + balance +  "]";
	}




	
}
