package com.example.shopping_cart_second.base;


public abstract class BaseDto<T> {
	private T id;

	public T getId() {
		return id;
	}

	public void setId(T id) {
		this.id = id;
	}
	
	
}
