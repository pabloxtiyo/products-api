package com.products.api.model.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.products.api.model.entity.ProductPurshase;


@Repository
public interface ProductPurshaseRepository extends JpaRepository<ProductPurshase, Integer> {
	
	public ProductPurshase findById(int id);
	
	public List<ProductPurshase> findAllByproductName(String name);
	
}
