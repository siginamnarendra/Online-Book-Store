package com.customerService.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CartItems {

	private Integer CartItemId;
	private Integer bookId;
	private String bookTitle;
	private Integer quantity;
	private Integer bookPrice;
	private Cart cart;

}
