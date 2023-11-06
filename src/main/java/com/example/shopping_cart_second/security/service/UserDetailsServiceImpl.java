package com.example.shopping_cart_second.security.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.shopping_cart_second.Exception.EmployeeServiceException;
import com.example.shopping_cart_second.entity.User;
import com.example.shopping_cart_second.repository.UserRepository;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
	@Autowired
	UserRepository userRepository;

	@Override
	@Transactional
	public UserDetails loadUserByUsername(String username) {
		User user = userRepository.findByUsername(username).orElseThrow(
				() -> new RuntimeException(new EmployeeServiceException("UserDto Not Found with username: " + username)));

		return UserDetailsImpl.build(user);
	}

}
