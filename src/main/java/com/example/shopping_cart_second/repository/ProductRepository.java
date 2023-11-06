package com.example.shopping_cart_second.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.shopping_cart_second.entity.Product;


@Repository
public interface ProductRepository extends JpaRepository<Product, Long>{
//	public Product getByName(String name);
//	@Query(nativeQuery = true,value = "select * from products where name=:name")
	Product findByName(@Param("name") String name);
	
	
}
