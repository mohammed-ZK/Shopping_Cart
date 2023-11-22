package com.example.shopping_cart_second;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
//@EnableCaching
public class ShoppingCartSecondApplication {

	public static void main(String[] args) {
		SpringApplication.run(ShoppingCartSecondApplication.class, args);
	}

}
