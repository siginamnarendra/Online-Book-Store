package com.customerService.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.customerService.entity.Customer;

//CustomerJpaRepository which takes care of Database operation
public interface CustomerJpaRepository extends JpaRepository<Customer, String> {

	// non need to write any code
}
