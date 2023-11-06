package com.example.shopping_cart_second.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.shopping_cart_second.Exception.BaseResponse;
import com.example.shopping_cart_second.Exception.EmployeeServiceException;
import com.example.shopping_cart_second.entity.Cart;
import com.example.shopping_cart_second.entity.Product;
import com.example.shopping_cart_second.entity.User;
import com.example.shopping_cart_second.repository.CartRepository;
import com.example.shopping_cart_second.repository.ProductRepository;

@Service
public class ProductService {

	private static final Logger log = LoggerFactory.getLogger(ProductService.class);

	@Autowired
	private ProductRepository productRepository;

	@Autowired
	private CartRepository cartRepository;

	public BaseResponse<Product> insert(Product products) throws EmployeeServiceException {

		try {

			Product pro2 = productRepository.save(products);
			log.info("======>" + products.getName());
			BaseResponse<Product> baseResponse = new BaseResponse<>();
			baseResponse.setData(pro2);
			return baseResponse;
		} catch (Exception e) {
			throw new EmployeeServiceException("the name is exited");
		}

	}

	public List<Product> getMyProduct() throws Exception {
		return productRepository.findAll();
	}

	public BaseResponse<Product> updateProduct(Product product, Long id) {
		Product product2 = productRepository.findById(id).get();
		log.info("========>id" + id);
		product2.setDescription(product.getDescription());
		log.info("========>id" + product.getDescription());
		product2.setName(product.getName());
		log.info("========>id" + product.getName());
		product2.setPrice(product.getPrice());
		log.info("========>id" + product.getPrice());
		productRepository.save(product2);
		log.debug("Save in the database");
		BaseResponse<Product> baseResponse = new BaseResponse<>();
		baseResponse.setData(product2);
		return baseResponse;
////		try {
//		Cart cart = cartRepository.findById(id).get();
//		if (cart != null) {
//
//			throw new Exception();
//		}
//
//		List<BigDecimal> invoices = new LinkedList<>();
//		Product product2 = productRepository.findByName(product.getName());
//		if (product2.equals(null)) {
//			throw new Exception();
//		}
//		products.add(product2);
//		BigDecimal Sum = cart.getTotalprice();
//		invoices.add(product2.getPrice());
//		invoices.add(Sum);
//		Sum = invoices.stream().reduce(BigDecimal.ZERO, BigDecimal::add);
////		cart.setProducts(products);
//		cart.setTotalprice(Sum);
//		cartRepository.save(cart);
////		} catch (CartIdNotFoundException e) {
////			throw new CartIdNotFoundException();
////		}
	}
//

	public BaseResponse<Void> deleteProduct(Long id) {

		productRepository.deleteById(id);
		return new BaseResponse<>();
	}
	
	public BaseResponse<Void> addProductToCart(Long id1,Long id2){
		Cart cart=cartRepository.findById(id1).get();
		log.info("=====>"+id1);
		Product product=productRepository.findById(id2).get();
		log.info("=====>"+id2);
		BigDecimal Sum = cart.getTotalprice();
		log.info("=====>"+Sum);
		List<BigDecimal> invoices = new LinkedList<>();
		List<Product> list=new ArrayList<>();
		invoices.add(product.getPrice());
		invoices.add(Sum);
		Sum = invoices.stream().reduce(BigDecimal.ZERO, BigDecimal::add);
		cart.setTotalprice(Sum);
		log.info("=====>"+Sum);
		list.add(product);
		List<Product> list2= cart.getProducts();
		for (Product product2 : list2) {
			list.add(product2);	
		}
		cart.setProducts(list);
		
		cartRepository.save(cart);
		
//		return new BaseResponse<>();
		return null;
	}
	public BaseResponse<Void> deleteProductFromCart(Long id1,Long id2){
		Cart cart=cartRepository.findById(id1).get();
		log.info("==========>"+id1);
		List<Product> products= cart.getProducts();
		log.info("==========>"+id2);
		for (Product product : products) {
			log.info("======>"+product.getName());
		}
		
		products.removeIf(id->id.getId()==id2);
//		for (Product product : products) {
////			if(product.getId()==id2) {
//			log.info("======>"+product.getName());
////			}
//		}
		for (Product product : products) {
			log.info("======>"+product.getName());
		}
		
		cart.setProducts(products);
		cartRepository.save(cart);
	
		
		
		return null;
	}
}
