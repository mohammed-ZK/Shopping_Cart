package com.example.shopping_cart_second.mapper;

import org.mapstruct.Mapper;

import com.example.shopping_cart_second.dto.CartDto;
import com.example.shopping_cart_second.entity.Cart;

@Mapper
public interface CartMapper {

	CartDto mapToDto(Cart cart);

	Cart mapToEntity(CartDto cartDto);

}
