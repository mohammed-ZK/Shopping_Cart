package com.example.shopping_cart_second.dto;

import com.example.shopping_cart_second.base.BaseDto;

public class UserDto extends BaseDto<Long> {

	private String username;

	private String email;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

}
