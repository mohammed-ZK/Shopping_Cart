package com.example.order.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.example.order.Entity.Order;
import com.example.order.service.OrderService;

@RestController
@RequestMapping("/orderAPI")
public class OrderController {

	@Autowired
	private OrderService orderService;
	
	@Autowired
	private RestTemplate restTemplate;
	
	@PostMapping("/order")
	public ResponseEntity<Order> insert(@RequestBody Order order){
		Order baseResponse = orderService.insert(order);
		return ResponseEntity.ok(baseResponse);
	}
	
	@GetMapping("/orders")
	public ResponseEntity<List<Order>> getAllOrders(){
		List<Order> baseResponse = orderService.getAllOrders();
		return ResponseEntity.ok(baseResponse);
	}
	
	@GetMapping("/order/{id}")
	public ResponseEntity<Order> getOrderById(@PathVariable Long id){
		Order baseResponse = orderService.getOrderById(id);
		return ResponseEntity.ok(baseResponse);
	}	
	
}
