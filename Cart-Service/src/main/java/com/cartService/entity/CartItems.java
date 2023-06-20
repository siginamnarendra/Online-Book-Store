package com.cartService.entity;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonBackReference;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CartItems {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Integer CartItemId;
	private Integer bookId;
	private String bookTitle;
	private Integer quantity;
	private Integer bookPrice;

	@ManyToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name = "customer_id")
	@JsonBackReference
	private Cart cart;

}
