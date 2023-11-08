package com.example.shopping_cart_second.mapper;

import org.mapstruct.Mapper;

import com.example.shopping_cart_second.dto.UserDto;
import com.example.shopping_cart_second.entity.User;

@Mapper
public interface UserMapper {

	UserDto mapToDto(User user);

	User mapToEntity(UserDto userDto);
}
