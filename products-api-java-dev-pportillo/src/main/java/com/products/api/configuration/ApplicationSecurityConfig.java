package com.products.api.configuration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;


@Configuration
public class ApplicationSecurityConfig extends WebSecurityConfigurerAdapter {

	private Logger logger = LoggerFactory.getLogger(ApplicationSecurityConfig.class);
	

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder authentication)
            throws Exception
    {
        
        authentication.inMemoryAuthentication()
        .withUser("pablo.portillo")
        .password("admin")
        .roles("ADMIN");
        authentication.inMemoryAuthentication()
        .withUser("admin")
        .password("admin")
        .roles("ADMIN");
        authentication.inMemoryAuthentication()
        .withUser("user")
        .password("user")
        .roles("USER");
    }
    
    @Override
    protected void configure(HttpSecurity http) throws Exception {
    	
    	this.logger.info("Aplying security configuration for custom routes...");
    	
    	http.csrf().disable().authorizeRequests()
    	.antMatchers(HttpMethod.GET).permitAll()
    	.antMatchers(HttpMethod.POST,"/api/product").hasRole("ADMIN")
    	.antMatchers(HttpMethod.DELETE,"/api/product/**").hasRole("ADMIN")
    	.antMatchers(HttpMethod.PATCH,"/api/product/**").hasRole("ADMIN")
    	.antMatchers(HttpMethod.POST,"/api/product/like/**").hasAnyRole("ADMIN","USER")
    	.antMatchers(HttpMethod.POST,"/api/product/purshase").hasAnyRole("ADMIN","USER")
    	.and().httpBasic();
    	this.logger.info("Security configuration for custom routes successfully applied.");
    	
    }
}
