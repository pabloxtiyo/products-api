package com.products.api.controller;

import java.security.Principal;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.products.api.exceptions.NotEnoughProductStockException;
import com.products.api.exceptions.ProductAlreadyExistException;
import com.products.api.exceptions.ProductNotFoundException;
import com.products.api.exceptions.ProductsPaginationSortingException;
import com.products.api.model.entity.Product;
import com.products.api.model.entity.ProductPurshase;
import com.products.api.service.ProductPurshaseService;
import com.products.api.service.ProductService;


@RestController
@RequestMapping("/api")
public class ProductController {
	
	public static final Logger logger = LoggerFactory.getLogger(ProductController.class);
	@Autowired
	private ProductService productService;
	@Autowired
	private ProductPurshaseService productPurshaseService;
	
	@PostMapping("/product")
	public ResponseEntity<?> createProduct(@RequestBody Product product) throws ProductAlreadyExistException
	{
		logger.info("Creating a new product : {}", product);
		
		if(productService.isProductExist(product.getProductName()))
		{
			logger.error("Unable to create a product with name {} already exist", product.getProductName());
			
			throw new ProductAlreadyExistException("Unable to create a product with name " + 
		            product.getProductName() + " already exist.");
			
		}
		else
		{
			productService.save(product);
			HttpHeaders headers = new HttpHeaders();
	        return new ResponseEntity<String>(headers, HttpStatus.CREATED);
		}	
	}
	
	@PatchMapping("/product/{name}")
	public ResponseEntity<?> updateProduct(@PathVariable("name") String name,@RequestBody Product product) throws ProductNotFoundException
	{
		logger.info("Updating product price: {}", product);
		
		if(!productService.isProductExist(product.getProductName()))
		{
			logger.error("Unable to update the product price with name {}, product doesn't exist", product.getProductName());
			
			return new ResponseEntity(new Exception("Unable to update the product price with name " + 
		            product.getProductName() + " product doesn't exist"),HttpStatus.NOT_FOUND);
		}
		else
		{
			
			
			Product currentProduct = productService.getProductByName(name);
			
			logger.info("Old product price: {} , new product price {}, product name: {}", 
					currentProduct.getProductPrice(),
					product.getProductPrice(),
					product.getProductName());
			
			currentProduct.setProductPrice(product.getProductPrice());
			currentProduct.setProductQuantity(product.getProductQuantity());
			productService.update(currentProduct);
			
	        return new ResponseEntity<Product>(currentProduct, HttpStatus.OK);
		}	
	}

	@GetMapping("/product")
    public ResponseEntity<List<Product>> listAllProductsPaginable(
    		@RequestParam(name="orderBy",defaultValue="productName") String orderBy,
    		@RequestParam(name="direction",defaultValue="asc") String direction, 
    		@RequestParam("page") int page,
    		@RequestParam("size") int size) throws ProductsPaginationSortingException 
	{
		if (!(direction.equals("asc") || direction.equals("desc"))) 
		{
		  throw new ProductsPaginationSortingException("Invalid sort direction");
		}
		
		if (!(orderBy.equals("productName") || orderBy.equals("productLikes"))) 
		{
		  throw new ProductsPaginationSortingException("Invalid orderBy condition");
		}
		
		logger.info("Getting the products available");
		
        List<Product> productList = productService.findAll(orderBy, direction, page, size);
        
        if (productList.isEmpty()) 
        {
        	logger.error("We dont have any product on stock");
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<List<Product>>(productList, HttpStatus.OK);
    }
	
	/*@GetMapping("/product")
    public ResponseEntity<List<Product>> listAllProducts() 
	{	
		logger.info("Getting the products available");
		
        List<Product> productList = productService.findAll();
        
        if (productList.isEmpty()) 
        {
        	logger.error("We dont have any product on stock");
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<List<Product>>(productList, HttpStatus.OK);
    }*/
	
	@GetMapping("/product/{name}")
    public ResponseEntity<Product> GetProductByName(@PathVariable("name") String name) throws ProductNotFoundException 
	{
		logger.info("Getting product by name {}", name);
		
        Product product = productService.getProductByName(name);

        return new ResponseEntity<Product>(product, HttpStatus.OK);
    }
	
	@DeleteMapping("/product/{name}")
    public ResponseEntity<?> deleteProduct(@PathVariable("name") String name) throws ProductNotFoundException {
        
		logger.info("Getting and trying to delete the Product with name {}", name);
		
        Product product = productService.getProductByName(name);
        
        productService.delete(product.getId());
        
        return new ResponseEntity<Product>(HttpStatus.NO_CONTENT);
    }
	
	@PostMapping("/product/purshase")
	public ResponseEntity<?> purshaseAProduct(@RequestBody ProductPurshase purshase,
			Principal principal) throws ProductNotFoundException, NotEnoughProductStockException
	{
		logger.info("A new purshase request was made {}", purshase);
		
		logger.info("Getting the product info {}", purshase.getProductName());
		
		Product product = productService.getProductByName(purshase.getProductName());
		
		logger.info("Validating product current stock {}", purshase.getProductName());
		
		// Validating if we have the quantity of products requested
		if(product.getProductQuantity() < purshase.getPurshaseQuantity())
		{
			throw new NotEnoughProductStockException();
		}
		else
		{
			int currentQ = product.getProductQuantity();
			product.setProductQuantity(currentQ-purshase.getPurshaseQuantity());
			productService.update(product);
			purshase.setPurshaseDate(new Date());
			purshase.setClientName((principal!=null) ? principal.getName() : "anonymous.user");
			
			logger.info("Saving the purshase {}", purshase);
			
			productPurshaseService.save(purshase);
			return new ResponseEntity<ProductPurshase>(purshase,HttpStatus.OK);
		}
		
	}

	@PostMapping("/product/like/{name}")
	public ResponseEntity<?> likingAProduct(@PathVariable("name") String name) throws ProductNotFoundException
	{
		logger.info("Getting and trying to like the Product with name {}", name);
		
        Product product = productService.getProductByName(name);
        Product newProduct = product;
        int likes = product.getProductLikes();
        likes++;
        newProduct.setProductLikes(likes);
        productService.update(newProduct);
        return new ResponseEntity<Product>(newProduct, HttpStatus.OK);
        
	}
}
	

