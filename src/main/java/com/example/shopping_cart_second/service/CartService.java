package com.example.shopping_cart_second.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.example.shopping_cart_second.Exception.BaseResponse;
import com.example.shopping_cart_second.Exception.EmployeeServiceException;
import com.example.shopping_cart_second.Exception.UserNotFoundException;
import com.example.shopping_cart_second.dto.CartDto;
import com.example.shopping_cart_second.entity.Cart;
import com.example.shopping_cart_second.entity.ERole;
import com.example.shopping_cart_second.entity.Order;
import com.example.shopping_cart_second.entity.Role;
import com.example.shopping_cart_second.entity.User;
import com.example.shopping_cart_second.mapper.CartMapper;
import com.example.shopping_cart_second.mapper.CartMapperImpl;
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
	RabbitMQProducer mqProducer;

	@Autowired
	private JwtUtils jwtUtils;

	private CartMapper cartMapper = new CartMapperImpl();

	public Long insert() throws UserNotFoundException {
		Cart cart = new Cart();

		User user = new User();

		Order order = new Order();

		log.info("before Auth get");

		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

		log.info("after Auth get" + authentication.isAuthenticated());
		String string = authentication.getName();

		user = userRepository.findByUsername(string).get();

		cart.setUser(user);
		cart.setTotalprice(BigDecimal.ZERO);

		if (cartRepository.existsByUser_Id(user.getId())) {
			throw new UserNotFoundException("The User has a cart");
		} else {

			log.info("=======>1.0");
			Long cartId= cartRepository.save(cart).getId();

			log.info("=======>"+cartId);
			order.setCardId(cartId);
			log.info("=======>"+order.getCardId());
			order.setUserId(user.getId());
			log.info("=======>"+order.getUserId());
			order.setToken(string);
			log.info("=======>"+order.getUserId());
			mqProducer.sendMessage(order);

			log.info("=======>4");
			return cartId;
		}

	}

	public List<CartDto> getCarts() throws Exception {

		List<Cart> carts = cartRepository.findAll();
		List<CartDto> cartDtos = new ArrayList<>();
		for (Cart cart : carts) {
			log.info("=======>" + cart.getUser().getEmail());
			CartDto cartDto = cartMapper.mapToDto(cart);
			log.info("=======>" + cartDto.getTotalprice());
			cartDtos.add(cartDto);
		}
		return cartDtos;
	}

	public BaseResponse<Void> deleteCart(Long id) throws Exception {

		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		User user = userRepository.findByUsername(authentication.getName()).get();
		Long idUser = user.getId();
		log.info("====>" + user.getRoles().toString());

		Cart cart = cartRepository.findById(id).get();
		log.info("=======>" + cart.getId());
		if (cart.getUser().getId() != idUser) {
			throw new EmployeeServiceException("The User is Access Dinay");
		} else {
			cartRepository.deleteById(id);
			return new BaseResponse<>();
		}
	}

	public BaseResponse<CartDto> getCart(Long id) throws Exception {

		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		User user = userRepository.findByUsername(authentication.getName()).get();
		Long idUser = user.getId();
		log.info("====>" + user.getId());

		Cart cart = cartRepository.findById(id).get();
		log.info("=======>" + cart.getId().toString());
		Set<Role> role = new HashSet<>();
		Role role2 = new Role(ERole.ROLE_ADMIN);
		role.add(role2);
		log.info("=====>" + role.toString());
		log.info("=====>" + cart.getUser().getRoles().contains(role2));
		if (cart.getUser().getId() == idUser || cart.getUser().getRoles().equals(role)) {

			CartDto cartDto = cartMapper.mapToDto(cart);
			BaseResponse<CartDto> baseResponse = new BaseResponse<>();
			baseResponse.setData(cartDto);
			return baseResponse;

		} else {
			throw new EmployeeServiceException("The User isn`t Access");
		}

	}

	public Order getCartIdAndUserId(Long id) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		User user = userRepository.findByUsername(authentication.getName()).get();
		Long idUser = user.getId();

		Order order = new Order(idUser, id);

		log.debug("user Id is {} and cart Id is {}", idUser, id);

		return order;
	}

}
