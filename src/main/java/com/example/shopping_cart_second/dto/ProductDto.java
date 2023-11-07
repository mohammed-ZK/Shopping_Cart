package com.example.shopping_cart_second.dto;

import java.math.BigDecimal;

import com.example.shopping_cart_second.base.BaseDto;

public class ProductDto extends BaseDto<Long> {
	
	private Long id;
	
	private String name;

	private String description;

	private BigDecimal price;

	
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}
	
	
}
