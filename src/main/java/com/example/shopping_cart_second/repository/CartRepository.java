package com.example.shopping_cart_second.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.shopping_cart_second.entity.Cart;

@Repository
public interface CartRepository extends JpaRepository<Cart, Long> {
	Cart findByUser_id(Long id);
}
