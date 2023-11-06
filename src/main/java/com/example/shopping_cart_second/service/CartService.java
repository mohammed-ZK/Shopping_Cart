package com.example.shopping_cart_second.service;

import java.math.BigDecimal;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.example.shopping_cart_second.Exception.BaseResponse;
import com.example.shopping_cart_second.entity.Cart;
import com.example.shopping_cart_second.entity.User;
import com.example.shopping_cart_second.repository.CartRepository;
import com.example.shopping_cart_second.repository.UserRepository;
import com.example.shopping_cart_second.security.jwt.JwtUtils;

@Service
public class CartService {

	private static final Logger log = LoggerFactory.getLogger(CartService.class);

	@Autowired
	private CartRepository cartRepository;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	JwtUtils jwtUtils;

//	final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

	public BaseResponse<Cart> insert() throws Exception {
		Cart cart = new Cart();

		User user = new User();

		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String string = authentication.getName();
		user = userRepository.findByUsername(string).get();
		cart.setUser(user);
		cart.setTotalprice(BigDecimal.ZERO);

		Cart cart2 = cartRepository.save(cart);
		BaseResponse<Cart> baseResponse = new BaseResponse<>();
		baseResponse.setData(cart2);
		return baseResponse;

//		return new BaseResponse<>();
	}

	public ResponseEntity<List<Cart>> getMyCart() throws Exception {
//		Long id_cart = ;
		return ResponseEntity.ok(cartRepository.findAll());
	}

	public BaseResponse<Void> deleteCart(Long id) throws Exception {
		cartRepository.deleteById(id);
		return new BaseResponse<>();
	}

	public BaseResponse<Cart> getCart(Long id) throws Exception {
		Cart cart = cartRepository.findById(id).get();
		log.info("=======>" + cart.getId().toString());

		BaseResponse<Cart> baseResponse = new BaseResponse<>();
		baseResponse.setData(cart);
		return baseResponse;

	}

}
