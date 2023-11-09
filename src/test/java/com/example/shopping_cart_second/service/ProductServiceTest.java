package com.example.shopping_cart_second.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.example.shopping_cart_second.entity.Product;
import com.example.shopping_cart_second.repository.ProductRepository;

@SpringBootTest
public class ProductServiceTest {

	
	private static final Logger log = LoggerFactory.getLogger(ProductServiceTest.class);

	
//	@Autowired
//	ProductRepository productRepository;

	@MockBean
	ProductRepository productRepository;
	
	@Test
	public void Insert() {

		Product product=getInsert();
		
		Mockito.when(productRepository.save(Mockito.any())).thenReturn(product);
		
		Product product2= productRepository.save(product);
		Optional<Product> optional=Optional.of(product2);
		assertEquals(true, optional.isPresent());
	}
	@Test
	public void NotInsert() {
		Product product=getInsert();
		
		Mockito.when(productRepository.save(Mockito.any())).thenReturn(product);
		
		Product product2= productRepository.save(product);
		Optional<Product> optional=Optional.of(product2);
		assertEquals(false, optional.isEmpty());
	}
	@Test
	public void getProducts() {

		Product product=getInsert();
		
		List< Product> products =new ArrayList<>();
		products.add(product);
		
		Mockito.when(productRepository.save(Mockito.any())).thenReturn(product);
		
		Mockito.when(productRepository.findAll()).thenReturn(products);
		
		Product product2= productRepository.save(product);
		
		log.info("==========>1"+product2.getDescription());
		
		List<Product> products1=new ArrayList<>();
		products1.add(product2);
		
		log.info("==========>2"+products.get(0).getDescription());	
		
		List<Product> products2=productRepository.findAll();
		
		assertEquals(products1, products2);
	}
	
	@Test
	public void updateProduct() {
		
		Product temp=new Product();
		temp.setDescription("any description");
		temp.setName("PC");
		temp.setPrice(BigDecimal.valueOf(2000));
		
		Optional<Product> optional=Optional.of(temp);
		
		Product product2=getInsert();
		
		Mockito.when(productRepository.save(Mockito.any())).thenReturn(product2);

		log.info("====>1"+product2.getDescription());
		Mockito.when(productRepository.findById(Mockito.any())).thenReturn(optional);

		log.info("====>2"+product2.getDescription());
		Product product=productRepository.findById(1L).get();
		
		Product product3= productRepository.save(product);
		log.info("====>3"+product3.getDescription());
		log.info("===>4"+product2.getDescription());
		
		assertEquals(product3,product2);
	}
	
	@Test
	public void UnUpdateProduct() {
		Product temp=new Product();
		temp.setDescription("any description");
		temp.setName("PC");
		temp.setPrice(BigDecimal.valueOf(2000));
		
		Optional<Product> optional=Optional.of(temp);
		
		Product product2=getInsert();
		
		Mockito.when(productRepository.save(Mockito.any())).thenReturn(product2);

		log.info("====>1"+product2.getDescription());
		Mockito.when(productRepository.findById(Mockito.any())).thenReturn(optional);

		log.info("====>2"+product2.getDescription());
		Product product=productRepository.findById(1L).get();
		
		Product product3= productRepository.save(product);
		log.info("====>3"+product3.getDescription());
		log.info("===>4"+product2.getDescription());
		
		assertNotEquals(product3,temp);
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
	
	
	public Product getInsert() {

		Product product=new Product();
		product.setDescription("aaa");
		product.setName("number1");
		product.setPrice(BigDecimal.valueOf(200));
		return product;
	}
	
}
