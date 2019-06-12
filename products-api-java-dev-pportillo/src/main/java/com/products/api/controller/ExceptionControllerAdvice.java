package com.products.api.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import com.products.api.exceptions.*;


@ControllerAdvice
public class ExceptionControllerAdvice
{
	private Logger logger = LoggerFactory.getLogger(ExceptionControllerAdvice.class);
	
	@ExceptionHandler(value = Exception.class)
	public ResponseEntity<String> UnknowException(Exception ex) throws Exception
	{
		this.logger.error("It had been an error with your request, please contact the administrators");
		
		return new ResponseEntity<String>("It had been an error with your request,"
				+ " please contact the administrators, description: "+ex.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@ExceptionHandler(value = ProductNotFoundException.class)
	public ResponseEntity<String> ProductNotFoundException() throws Exception
	{
		this.logger.error("Product not found exception");
		
	    return new ResponseEntity<String>("Product not found",HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(value = NotEnoughProductStockException.class)
	public ResponseEntity<String> NotEnoughProductStockException() throws Exception
	{
		this.logger.error("Not enough products exception");
		
	    return new ResponseEntity<String>("Unfortunately we dont have quantity of products in our stock",HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(value = ProductsPaginationSortingException.class)
	public ResponseEntity<String> ProductsPaginationSortingException(Exception ex) throws Exception
	{
		this.logger.error("Products pagination sorting exception");
		
	    return new ResponseEntity<String>(ex.getMessage(),HttpStatus.PRECONDITION_FAILED);
	}
	
	@ExceptionHandler(value = ProductAlreadyExistException.class)
	public ResponseEntity<String> ProductAlreadyExistException(Exception ex) throws Exception
	{
		this.logger.error("Product already exist exception");
		
		return new ResponseEntity<String>(ex.getMessage(),HttpStatus.CONFLICT);
	}
	
	
	
	
}