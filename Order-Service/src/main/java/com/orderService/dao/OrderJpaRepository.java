package com.orderService.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.orderService.entity.Orders;

public interface OrderJpaRepository extends JpaRepository<Orders, String> {

}
