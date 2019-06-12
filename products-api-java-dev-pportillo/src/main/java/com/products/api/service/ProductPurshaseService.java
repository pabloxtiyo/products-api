package com.products.api.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.products.api.model.entity.ProductPurshase;
import com.products.api.model.repository.ProductPurshaseRepository;


@Service
public class ProductPurshaseService implements GenericService<ProductPurshase> {

	@Autowired
	private ProductPurshaseRepository productPurshaseRepo;

	@Override
	public ProductPurshase save(ProductPurshase entity) {
		// TODO Auto-generated method stub
		return productPurshaseRepo.save(entity);
	}

	@Override
	public List<ProductPurshase> findAll() {
		// TODO Auto-generated method stub
		return productPurshaseRepo.findAll();
	}

	@Override
	public boolean delete(int id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public ProductPurshase update(ProductPurshase entity) {
		// TODO Auto-generated method stub
		return null;
	}
	
	
}
