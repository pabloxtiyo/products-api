package com.products.api.service;

import java.util.List;


public interface GenericService <T> {
	public T save(T entity);
	public List<T> findAll();
	public boolean delete(int id);
	public T update(T entity);
}
