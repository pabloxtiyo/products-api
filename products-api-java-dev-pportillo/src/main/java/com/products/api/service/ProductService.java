package com.products.api.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import com.products.api.exceptions.ProductNotFoundException;
import com.products.api.model.entity.Product;
import com.products.api.model.repository.ProductRepository;


@Service
public class ProductService implements GenericService<Product> {

	@Autowired
	private ProductRepository productRepo;
	
	@Override
	public Product save(Product entity) {
		entity.setId(0);
		return productRepo.save(entity);
	}
	
	@Override
	public List<Product> findAll() {
		return productRepo.findAll();
	}

	@Override
	public boolean delete(int id) {
		if (productRepo.exists(id)){
			productRepo.delete(id);
			return true;
		} else {
			return false;
		}
	}
	
	public List<Product> findAll(String orderBy, String direction, int page, int size)
	{
		Sort sort = null;
		
		if (direction.equals("asc")) 
		{
			sort = new Sort(new Sort.Order(Direction.ASC, orderBy));
		}
		if (direction.equals("desc")) 
		{
			sort = new Sort(new Sort.Order(Direction.DESC, orderBy));
		}
		
		Pageable pageable = new PageRequest(page, size, sort);
		
		Page<Product> data = productRepo.findAll(pageable);
		
		return data.getContent();
		
	}

	@Override
	public Product update(Product entity) {
		return productRepo.save(entity);
	}

	public Product getProduct(int id) {
		return productRepo.findById(id);
	}
	
	public Product getProductByName(String name) throws ProductNotFoundException {
		if(productRepo.findAllByproductName(name).size()>0)
		{
			return productRepo.findOneByproductName(name);
		}
		else
		{
			throw new ProductNotFoundException();
		}
	}
	
	public boolean isProductExist(String name)
	{
		if(productRepo.findAllByproductName(name).size()>0)
		{
			return true;
		}
		else
		{
			return false;
		}
		
	}
}
