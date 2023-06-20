package com.customerService.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.customerService.model.User;

//UserRepository which takes care of Database operation
public interface UserRepository extends JpaRepository<User, String> {

	User findByUsername(String username);

}