package com.example.PMS1;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.stereotype.Component;

@Component

@Entity

@Table(name ="PIZZAINF")
public class PMS {
	
	@Column(name="PRODUCT")
	String product;
	
	@Id
	@Column(name="ID")
	String id;
	
	@Column(name="PRICE")
	String price;

	public String getProduct() {
		return product;
	}

	public void setProduct(String product) {
		this.product = product;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}
	
}
