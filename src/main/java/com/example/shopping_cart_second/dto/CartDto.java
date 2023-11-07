package com.example.shopping_cart_second.dto;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import com.example.shopping_cart_second.base.BaseDto;
import com.example.shopping_cart_second.entity.Product;

public class CartDto extends BaseDto<Long> {

	private BigDecimal totalprice;
	private List<Product> products = new ArrayList<>();

	public BigDecimal getTotalprice() {
		return totalprice;
	}

	public void setTotalprice(BigDecimal totalprice) {
		this.totalprice = totalprice;
	}

	public List<Product> getProducts() {
		return products;
	}

	public void setProducts(List<Product> products) {
		this.products = products;
	}

}
