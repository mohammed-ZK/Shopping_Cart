package com.example.shopping_cart_second;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class ShoppingCartSecondApplication {

	public static void main(String[] args) {
		SpringApplication.run(ShoppingCartSecondApplication.class, args);
	}

}
