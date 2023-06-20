package com.cartService.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cartService.entity.Cart;

@Repository
public interface CartRepository extends JpaRepository<Cart, String> {

	Cart findByCustomerId(String customerId);
}
