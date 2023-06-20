package com.customerService.model;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Cart {

	private String customerId;
	private Integer totalPrice;
	private List<CartItems> cartItems = new ArrayList<CartItems>();

}
