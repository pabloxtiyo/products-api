package com.products.api.exceptions;

public class ProductAlreadyExistException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ProductAlreadyExistException() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ProductAlreadyExistException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		// TODO Auto-generated constructor stub
	}

	public ProductAlreadyExistException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	public ProductAlreadyExistException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	public ProductAlreadyExistException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}

}
