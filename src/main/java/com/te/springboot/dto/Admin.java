package com.te.springboot.dto;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@SuppressWarnings("serial")
@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties({ "userId" })
@Table(name = "admin_table")
public class Admin implements Serializable {

	@Id
	@Column(name = "Admin_User_Id")
	private String userId;
	@Column(name = "Admin_Name")
	private String username;
	@Column(name = "Admin_Password")
	private String password;

	public Admin(String username, String password) {

		this.username = username;
		this.password = password;
	}

}
