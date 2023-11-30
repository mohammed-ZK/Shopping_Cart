package com.example.shopping_cart_second.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.shopping_cart_second.Exception.BaseResponse;
import com.example.shopping_cart_second.Exception.ProductException;
import com.example.shopping_cart_second.dto.ProductDto;
import com.example.shopping_cart_second.entity.Product;
import com.example.shopping_cart_second.service.ProductService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/cart")
public class ProductController {

	private static final Logger log = LoggerFactory.getLogger(ProductController.class);

	@Autowired
	private ProductService productService;


	@PostMapping("/product")
//	@PreAuthorize("hasRole('ADMIN')")
	public BaseResponse<ProductDto> insert(@RequestBody @Valid Product products) throws ProductException{
		log.info("=====>1");
		BaseResponse<ProductDto> baseResponse = productService.insert(products);
		return baseResponse;
	}

	@GetMapping("/products")
//	@Cacheable(key = "#root.method" ,value = "findAllProduct")
//	@PreAuthorize("hasRole('USER')")
	public BaseResponse<List<ProductDto>> getProducts() {
		BaseResponse<List<ProductDto>> baseResponse = new BaseResponse<>();
		baseResponse.setData(productService.getProducts());
		return baseResponse;
	}

	@PutMapping("/products/{id}")
	@PreAuthorize("hasRole('ADMIN')")
	public BaseResponse<Product> updateProduct(@RequestBody @Valid Product products, @PathVariable Long id) {
		productService.updateProduct(products, id);
		return new BaseResponse<Product>();
	}

	@DeleteMapping("/products/{id}")
//	@PreAuthorize("hasRole('ADMIN')")
	public BaseResponse<Void> deleteProduct(@PathVariable Long id) throws ProductException {
		return productService.deleteProduct(id);
	}

	@PostMapping("/{id1}/products/{id2}")
	@PreAuthorize("hasRole('USER')")
	public BaseResponse<Void> addProductToCart(@PathVariable Long id1, @PathVariable Long id2) throws ProductException {
		log.info("=====>");
		productService.addProductToCart(id1, id2);
		return new BaseResponse<Void>();
	}

	@DeleteMapping("/{id1}/products/{id2}")
	@PreAuthorize("hasRole('USER')")
	public BaseResponse<Void> deleteProductFromCart(@PathVariable Long id1, @PathVariable Long id2) throws ProductException {
		log.info("=====>");
		productService.deleteProductFromCart(id1, id2);
		return new BaseResponse<Void>();
	}

//
//	@GetMapping("products")
////	@PreAuthorize("hasRole('ADMIN')")
//	public ResponseEntity<Cart> getCart(@PathVariable Long id) {
//		return ResponseEntity.ok(productService.getCart(id));
//	}
//
//	@DeleteMapping("products")
////	@PreAuthorize("hasRole('USER')")
//	public void deleteCart(@PathVariable Long id) throws Exception {
//		productService.deleteCart(id);
//		throw new Exception();
//	}

}
