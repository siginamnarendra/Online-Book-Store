package com.cartService.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cartService.entity.CartItems;

public interface CartItemsRepository extends JpaRepository<CartItems, Integer> {

}
