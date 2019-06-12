package com.products.api.model.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class ProductPurshase implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 3840556800021614915L;
	@Id 
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@JsonIgnore
	private int id;
	private String productName;
	@JsonIgnore
	private String clientName;
	private int purshaseQuantity;
	@JsonIgnore
	private Date purshaseDate;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public String getClientName() {
		return clientName;
	}
	public void setClientName(String clientName) {
		this.clientName = clientName;
	}
	public int getPurshaseQuantity() {
		return purshaseQuantity;
	}
	public void setPurshaseQuantity(int purshaseQuantity) {
		this.purshaseQuantity = purshaseQuantity;
	}
	public Date getPurshaseDate() {
		return purshaseDate;
	}
	public void setPurshaseDate(Date purshaseDate) {
		this.purshaseDate = purshaseDate;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	@Override
	public String toString() {
		return "ProductPurshase [id=" + id + ", productName=" + productName + ", clientName=" + clientName
				+ ", purshaseQuantity=" + purshaseQuantity + ", purshaseDate=" + purshaseDate + "]";
	}
	public ProductPurshase(int id, String productName, String clientName, int purshaseQuantity, Date purshaseDate) {
		super();
		this.id = id;
		this.productName = productName;
		this.clientName = clientName;
		this.purshaseQuantity = purshaseQuantity;
		this.purshaseDate = purshaseDate;
	}
	public ProductPurshase() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	}
