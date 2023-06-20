package com.identity.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import com.identity.entity.UserCredential;

public interface UserCredentialRepository extends JpaRepository<UserCredential, Integer> {
	Optional<UserCredential> findByName(String username);
}