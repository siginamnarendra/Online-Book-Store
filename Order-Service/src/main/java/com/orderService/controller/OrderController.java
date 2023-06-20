package com.orderService.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.orderService.response.RequestResponse;
import com.orderService.service.OrderService;

@RestController
@RequestMapping("/order")
public class OrderController {

	@Autowired
	private OrderService orderService;

	@GetMapping("/listOrders")
	public ResponseEntity<RequestResponse> listOrders(@RequestHeader("loggedInUserName") String customerId) {

		return orderService.getOrdersByCustomer(customerId);
	}

	@GetMapping("/getOrderById/{orderId}")
	public ResponseEntity<RequestResponse> getOrderById(@PathVariable("orderId") String orderId) {
		return orderService.getOrderById(orderId);

	}

	@PostMapping("/add-cart")
	public ResponseEntity<RequestResponse> addFromCart(@RequestHeader("loggedInUserName") String customerId) {
		return orderService.addFromCart(customerId);
	}

}