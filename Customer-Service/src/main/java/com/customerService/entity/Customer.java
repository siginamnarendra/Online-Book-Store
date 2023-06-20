package com.customerService.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "customers")
public class Customer {

	private String customerName;
	@Id
	private String customerEmail;
	private String customerContactNumber;
	private String customerAddress;
	private String password;

}
