package com.example.shopping_cart_second.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.example.shopping_cart_second.Exception.BaseResponse;
import com.example.shopping_cart_second.Exception.EmployeeServiceException;
import com.example.shopping_cart_second.dto.CartDto;
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

	public Long insert() throws Exception {
		Cart cart = new Cart();

		User user = new User();

		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String string = authentication.getName();
		user = userRepository.findByUsername(string).get();
		cart.setUser(user);
		cart.setTotalprice(BigDecimal.ZERO);

		Cart cart2 = cartRepository.save(cart);

		return cart2.getId();
	}

	public List<CartDto> getCarts() throws Exception {
		
		List<Cart> carts=cartRepository.findAll();
		List<CartDto> cartDtos=new ArrayList<>();
		for (Cart cart : carts) {
			CartDto cartDto=new CartDto();
			cartDto.setId(cart.getId());
			cartDto.setProducts(cart.getProducts());
			cartDto.setTotalprice(cart.getTotalprice());
			cartDtos.add(cartDto);
		}
		
		
		return cartDtos;
	}

	public BaseResponse<Void> deleteCart(Long id) throws Exception {
		cartRepository.deleteById(id);
		return new BaseResponse<>();
	}

	public BaseResponse<CartDto> getCart(Long id) throws Exception {

		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		User user = userRepository.findByUsername(authentication.getName()).get();
		Long idUser = user.getId();
		log.info("====>" + user.getId());

		Cart cart = cartRepository.findById(id).get();
		log.info("=======>" + cart.getId().toString());
		if (cart.getUser().getId() != idUser) {
			throw new EmployeeServiceException();
		} else {
			CartDto cartDto =new CartDto();
			cartDto.setId(cart.getId());
			cartDto.setProducts(cart.getProducts());
			cartDto.setTotalprice(cart.getTotalprice());
			BaseResponse<CartDto> baseResponse = new BaseResponse<>();
			baseResponse.setData(cartDto);
			return baseResponse;
		}
		
	}

}
