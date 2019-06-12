package com.products.api.model.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.products.api.model.entity.Product;



@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {
	
	public Product findById(int id);
	
	public List<Product> findAllByproductName(String name);
	
	public Product findOneByproductName(String name);
	
}
