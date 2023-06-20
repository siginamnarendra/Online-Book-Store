package com.customerService.service;

import org.springframework.http.HttpStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.customerService.client.JwtClient;
import com.customerService.dao.CustomerJpaRepository;
import com.customerService.dto.AuthRequest;
import com.customerService.dto.UserCredential;
import com.customerService.dto.UserDetails;
import com.customerService.entity.Customer;
import com.customerService.exceptions.UserAlreadyExistedException;
import com.customerService.exceptions.UserNotFoundException;
import com.customerService.exceptions.WrongCredentialEXception;
import com.customerService.response.RequestResponse;

import feign.FeignException.FeignClientException;

import java.util.NoSuchElementException;

import javax.transaction.Transactional;

@Service
public class CustomersService {

	@Autowired
	private CustomerJpaRepository customerJpaRepository;
	@Autowired
	private JwtClient jwtClient;

	@Transactional
	public ResponseEntity<RequestResponse> saveUser(Customer theCustomer) {
		String email = theCustomer.getCustomerEmail();
		try {
			Customer customer = customerJpaRepository.findById(email).get();
			throw new UserAlreadyExistedException("email :" + email + " is existed");

		} catch (NoSuchElementException es) {
			UserCredential user = new UserCredential();
			user.setEmail(theCustomer.getCustomerName());
			user.setName(theCustomer.getCustomerEmail());
			user.setPassword(theCustomer.getPassword());
			jwtClient.addNewUser(user);
			Customer savedCustomer = customerJpaRepository.save(theCustomer);
			savedCustomer.setPassword("xxxxxxxx");
			RequestResponse resp = RequestResponse.builder()
					.message(savedCustomer.getCustomerName() + " is registered as a user with " + " username : ")
					.success(true).status(HttpStatus.CREATED).result(savedCustomer).build();
			return new ResponseEntity<>(resp, HttpStatus.CREATED);
		}
	}

	public ResponseEntity<RequestResponse> logUser(AuthRequest authRequest) {

		try {
			RequestResponse resp = RequestResponse.builder().message("User Credentials Verified").success(true)
					.status(HttpStatus.ACCEPTED).result(jwtClient.getToken(authRequest)).build();
			return new ResponseEntity<>(resp, HttpStatus.ACCEPTED);

		} catch (FeignClientException ex) {
			throw new WrongCredentialEXception("BAD CREDENTIALS");
		}

	}

	public ResponseEntity<RequestResponse> getCustomerbyid(String id) {
		Customer customer = customerJpaRepository.findById(id)
				.orElseThrow(() -> new UserNotFoundException("No customer found with the phn: " + id));
		UserDetails userDetails = new UserDetails();
		userDetails.setCustomerAddress(customer.getCustomerAddress());
		userDetails.setCustomerContactNumber(customer.getCustomerContactNumber());
		userDetails.setCustomerEmail(customer.getCustomerEmail());
		userDetails.setCustomerName(customer.getCustomerName());
		userDetails.setPassword("xxxxxxxx");

		RequestResponse resp = RequestResponse.builder().message("Got the Profile").success(true)
				.status(HttpStatus.FOUND).result(userDetails).build();
		return new ResponseEntity<>(resp, HttpStatus.FOUND);
	}
}
