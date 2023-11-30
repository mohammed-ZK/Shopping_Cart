package com.example.shopping_cart_second.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.shopping_cart_second.Exception.BaseResponse;
import com.example.shopping_cart_second.Exception.EmployeeServiceException;
import com.example.shopping_cart_second.dto.AuthDto;
import com.example.shopping_cart_second.payload.request.LoginRequest;
import com.example.shopping_cart_second.payload.request.SignupRequest;
import com.example.shopping_cart_second.repository.RoleRepository;
import com.example.shopping_cart_second.repository.UserRepository;
import com.example.shopping_cart_second.security.jwt.JwtUtils;
import com.example.shopping_cart_second.security.service.AuthService;

import jakarta.validation.Valid;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
@Validated
public class AuthController extends Exception{
	
	private static final long serialVersionUID = 1L;

	@Autowired
	AuthenticationManager authenticationManager;

	@Autowired
	UserRepository userRepository;

	@Autowired
	RoleRepository roleRepository;

	@Autowired
	PasswordEncoder encoder;

	@Autowired
	JwtUtils jwtUtils;

	@Autowired
	AuthService authService;
	
	@PostMapping("/signin")
	public BaseResponse<AuthDto> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) throws EmployeeServiceException, Exception {

		BaseResponse<AuthDto> baseResponse=new BaseResponse<>();
		baseResponse.setData(authService.authenticateUser(loginRequest));
		
		return baseResponse;

	}

	@PostMapping("/signup")
	public  BaseResponse<Void> registerUser(@Valid @RequestBody SignupRequest signUpRequest) throws EmployeeServiceException {
		
		return authService.registerUser(signUpRequest);
		
	}
}
