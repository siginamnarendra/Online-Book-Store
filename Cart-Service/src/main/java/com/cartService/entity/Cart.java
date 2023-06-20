package com.cartService.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class Cart {

	@Id
	private String customerId;
	@OneToMany(mappedBy = "cart", orphanRemoval = true, cascade = CascadeType.ALL)
	@JsonManagedReference
	private List<CartItems> cartItems = new ArrayList<CartItems>();

}
