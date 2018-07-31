package com.techprimers.security.jwtsecurity.service;

public interface CrudService<E> {
	public Iterable<E> findAll();
	public <T> Object findOne(Long id);
	public <T> Object save(E entity);
}
