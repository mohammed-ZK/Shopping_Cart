package com.example.shopping_cart_second.mapper;

import org.mapstruct.Mapper;

import com.example.shopping_cart_second.dto.AuthDto;
import com.example.shopping_cart_second.payload.response.JwtResponse;

@Mapper
public interface AuthMapper {

	AuthDto mapToDto(JwtResponse cart);

	JwtResponse mapToEntity(AuthDto cartDto);

}
