package com.example.shopping_cart_second.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.shopping_cart_second.entity.Product;
import com.example.shopping_cart_second.repository.ProductRepository;

@SpringBootTest
public class ProductServiceTest {

	
	private static final Logger log = LoggerFactory.getLogger(ProductServiceTest.class);

	
	@Autowired
	ProductRepository productRepository;
	
	@Test
	public void Insert() {
		Product product=new Product();
		product.setDescription("aaa");
		product.setName("number1");
		product.setPrice(BigDecimal.valueOf(200));
		Product product2= productRepository.save(product);
		Optional<Product> optional=Optional.of(product2);
		assertEquals(true, optional.isPresent());
	}
	@Test
	public void NotInsert() {
		Product product=new Product();
		product.setDescription("aaa");
		product.setName("number1");
		product.setPrice(BigDecimal.valueOf(200));
		Product product2= productRepository.save(product);
		Optional<Product> optional=Optional.of(product2);
		assertEquals(false, optional.isEmpty());
	}
	@Test
	public void getProducts() {
		Product product=new Product();
		product.setDescription("aaa44");
		product.setName("number1");
		product.setPrice(BigDecimal.valueOf(200));
		Product product2= productRepository.save(product);
		log.info("==========>1"+product2.getDescription());
		List<Product> products=new ArrayList<>();
		products.add(product);
		log.info("==========>2"+products.get(0).getDescription());
		
		List<Product> products2=productRepository.findAll();
		for (Product product3 : products2) {
			log.info("==========>3"+product3.getDescription());	
		}
		assertEquals(products, products2);
	}
	
	@Test
	public void updateProduct() {
		Product product=productRepository.findById(1L).get();
		Product product2=new Product();
		
		product2.setDescription("aaa44");
		product2.setName("number1");
		product2.setPrice(BigDecimal.valueOf(200));
		
		product.setDescription(product2.getDescription());
		product.setName(product2.getName());
		product.setPrice(product2.getPrice());
		
		Product product3= productRepository.save(product);
		
		Optional<Product> optional=Optional.of(product3);
		
		assertEquals(true, optional.isPresent());
	}
	
	@Test
	public void UnUpdateProduct() {
		Product product=productRepository.findById(1L).get();
		Product product2=new Product();
		
		product2.setDescription("aaa44");
		product2.setName("number1");
		product2.setPrice(BigDecimal.valueOf(200));
		
		product.setDescription(product2.getDescription());
		product.setName(product2.getName());
		product.setPrice(product2.getPrice());
		
		Product product3= productRepository.save(product);
		
		Optional<Product> optional=Optional.of(product3);
		
		assertEquals(false, optional.isEmpty());
	}
	
	@Test
	public void deleteProduct() {
		productRepository.deleteById(1L);
		Optional<Product > optional=productRepository.findById(1L);
		assertEquals(true, optional.isEmpty());
	}
	@Test
	public void NonDeleteProduct() {
		productRepository.deleteById(1L);
		Optional<Product > optional=productRepository.findById(1L);
		assertEquals(false, optional.isPresent());
	}
	
}
