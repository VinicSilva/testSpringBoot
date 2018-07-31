package com.techprimers.security.jwtsecurity.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.techprimers.security.jwtsecurity.model.JwtUser;

@Repository
public interface UserRepository extends JpaRepository<JwtUser, Long> {
	
}
