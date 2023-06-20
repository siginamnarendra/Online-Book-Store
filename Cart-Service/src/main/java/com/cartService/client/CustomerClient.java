package com.cartService.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;

import com.cartService.dto.Customer;

@Service
@FeignClient(url = "http://localhost:8080/customerService", name = "Customer-Client")
@ComponentScan
public interface CustomerClient {

	@GetMapping("/getProfile")
	public Customer getCustomer(@RequestHeader("loggedInUserName") String id);

}
