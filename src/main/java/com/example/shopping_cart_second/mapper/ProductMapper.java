package com.example.shopping_cart_second.mapper;

import org.mapstruct.Mapper;

import com.example.shopping_cart_second.dto.ProductDto;
import com.example.shopping_cart_second.entity.Product;

@Mapper
public interface ProductMapper {

	ProductDto mapToDto(Product product);

	Product mapToEntity(ProductDto productDto);

}
