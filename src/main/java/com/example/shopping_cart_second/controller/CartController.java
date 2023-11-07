package com.example.shopping_cart_second.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.shopping_cart_second.Exception.BaseResponse;
import com.example.shopping_cart_second.dto.CartDto;
import com.example.shopping_cart_second.service.CartService;

@RestController
@RequestMapping("/api/carts")
public class CartController {

	@Autowired
	private CartService cartService;

	@PostMapping()
	@PreAuthorize("hasRole('USER')")
	public BaseResponse<Long> insert() throws Exception {
		BaseResponse<Long> baseResponse = new BaseResponse<>();
		baseResponse.setData(cartService.insert());
		return baseResponse;
	}

	@GetMapping()
	@PreAuthorize("hasRole('ADMIN')")
	public List<CartDto> getCarts() throws Exception {
		return cartService.getCarts();
	}

	@GetMapping("{id}")
//	@PreAuthorize("hasRole('ADMIN')")
	@PreAuthorize("hasRole('USER')")
	public BaseResponse<CartDto> getCartById(@PathVariable Long id) throws Exception {
		return cartService.getCart(id);
	}

	@DeleteMapping("{id}")
	@PreAuthorize("hasRole('USER')")
	public BaseResponse<Void> deleteCart(@PathVariable Long id) throws Exception {
		cartService.deleteCart(id);
		return new BaseResponse<>();
//		throw new Exception();
	}
}
