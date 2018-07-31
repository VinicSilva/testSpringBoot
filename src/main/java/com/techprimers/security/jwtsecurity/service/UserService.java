package com.techprimers.security.jwtsecurity.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.techprimers.security.jwtsecurity.model.JwtUser;
import com.techprimers.security.jwtsecurity.repository.UserRepository;

@Service
public class UserService implements CrudService<JwtUser> {
	@Autowired
	private UserRepository userRepository;

	@Override
	public <T> Object save(JwtUser entity) {
		return userRepository.save(entity);
	}
	
	@Override
	public <T> Object findOne(Long id) {
		return userRepository.findOne(id);
	}

	@Override
	public Iterable<JwtUser> findAll() {
		return userRepository.findAll();
	}
}
