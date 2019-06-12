package com.products.api;

import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;

/**
 * Application initializer. Please, do not modify it.
 */
public class ServletInitializer extends SpringBootServletInitializer
{
	/**
	 * Application configuration builder. This class initializes the Spring Boot application. Pelase, do not modify this method.
	 */
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application)
	{
		return application.sources(ClientApplication.class);
	}
}