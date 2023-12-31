package com.example.shopping_cart_second.security.service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.shopping_cart_second.Exception.BaseResponse;
import com.example.shopping_cart_second.Exception.EmployeeServiceException;
import com.example.shopping_cart_second.Exception.NotGenerateTokenException;
import com.example.shopping_cart_second.Exception.UserNotExpiredException;
import com.example.shopping_cart_second.Exception.UserNotFoundException;
import com.example.shopping_cart_second.dto.AuthDto;
import com.example.shopping_cart_second.entity.ERole;
import com.example.shopping_cart_second.entity.Role;
import com.example.shopping_cart_second.entity.User;
import com.example.shopping_cart_second.mapper.AuthMapper;
import com.example.shopping_cart_second.mapper.AuthMapperImpl;
import com.example.shopping_cart_second.payload.request.LoginRequest;
import com.example.shopping_cart_second.payload.request.SignupRequest;
import com.example.shopping_cart_second.payload.response.JwtResponse;
import com.example.shopping_cart_second.repository.RoleRepository;
import com.example.shopping_cart_second.repository.UserRepository;
import com.example.shopping_cart_second.security.jwt.JwtUtils;

//@Configuration
@Service
public class AuthService {

	private static final Logger log = LoggerFactory.getLogger(AuthService.class);

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private RoleRepository roleRepository;

	@Autowired
	private PasswordEncoder encoder;

	@Autowired
	private JwtUtils jwtUtils;
	
	private AuthMapper authMapper=new AuthMapperImpl();

	public AuthDto authenticateUser(LoginRequest loginRequest)
			throws UserNotExpiredException, NotGenerateTokenException, EmployeeServiceException, UserNotFoundException {

		log.info("hello now well be found user");
		String username = loginRequest.getUsername();
		log.info("hello found user1 :" + username);
		String password = loginRequest.getPassword();
		log.info("hello found user" + password);
		UsernamePasswordAuthenticationToken itemToken = new UsernamePasswordAuthenticationToken(username, password);
		log.info("hello found user" + itemToken);
		try {
			Authentication authentication = authenticationManager.authenticate(itemToken);	

			log.info("hello found user");

			SecurityContextHolder.getContext().setAuthentication(authentication);
			String jwt = jwtUtils.generateJwtToken(authentication);

			UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
			List<String> roles = userDetails.getAuthorities().stream().map(item -> item.getAuthority())
					.collect(Collectors.toList());
			JwtResponse jwtResponse = new JwtResponse(jwt, userDetails.getId(), userDetails.getUsername(),
					userDetails.getEmail(), roles);
			BaseResponse<JwtResponse> baseResponse = new BaseResponse<>();
			baseResponse.setData(jwtResponse);
			AuthDto authDto =new AuthDto();
			authDto=authMapper.mapToDto(jwtResponse);
			return authDto;
		} catch (Exception e) {
			throw new UserNotFoundException("Username or Password is faild");
		}
		
	}

//	@Bean
	public BaseResponse<Void> registerUser(SignupRequest signUpRequest) throws EmployeeServiceException {
		if (userRepository.existsByUsername(signUpRequest.getUsername())) {
			throw new EmployeeServiceException("Error: Username is already taken!");
		}

		if (userRepository.existsByEmail(signUpRequest.getEmail())) {
			throw new EmployeeServiceException("Error: Email is already in use!");
		}

		// Create new user's account
		User user = new User(signUpRequest.getUsername(), signUpRequest.getEmail(),
				encoder.encode(signUpRequest.getPassword()));

		Set<String> strRoles = signUpRequest.getRole();
		Set<Role> roles = new HashSet<>();

		if (strRoles == null) {
			Role userRole = roleRepository.findByName(ERole.ROLE_USER)
					.orElseThrow(() -> new EmployeeServiceException("Error: Role is not found."));
			roles.add(userRole);
		} else {
			strRoles.forEach(role -> {
				switch (role) {
				case "admin":
					Role adminRole = roleRepository.findByName(ERole.ROLE_ADMIN).orElseThrow(
							() -> new RuntimeException(new EmployeeServiceException("Error: Role is not found.")));
					roles.add(adminRole);

					break;
				default:
					Role userRole = roleRepository.findByName(ERole.ROLE_USER).orElseThrow(
							() -> new RuntimeException(new EmployeeServiceException("Error: Role is not found.")));
					roles.add(userRole);
				}
			});
		}

		try {
			user.setRoles(roles);
			userRepository.save(user);
		} catch (Exception e) {
			new EmployeeServiceException(e.getMessage());
		}

		return new BaseResponse<>();
	}
}
