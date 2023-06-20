package com.customerService.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.customerService.dto.AuthRequest;
import com.customerService.entity.Customer;
import com.customerService.response.RequestResponse;
import com.customerService.service.CustomersService;

//Customer-service controller which provides the apis's for user registration,login and get profile
@RestController
@RequestMapping("/customerService")
public class CustomerServiceRestController {

	@Autowired
	private CustomersService customerService;

	@PostMapping("/register")
	public ResponseEntity<RequestResponse> registerUser(@RequestBody Customer customer) {
		return customerService.saveUser(customer);
	}

	@PostMapping("/logIn")
	public ResponseEntity<RequestResponse> logIn(@RequestBody AuthRequest authRequest) {
		return customerService.logUser(authRequest);
	}
	
	@GetMapping("/getProfile")
	public ResponseEntity<RequestResponse> getCustomer(@RequestHeader("loggedInUserName") String id) {
		return customerService.getCustomerbyid(id);
	}
}
