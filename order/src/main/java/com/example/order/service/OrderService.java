package com.example.order.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.order.Entity.Order;
import com.example.order.repository.OrderRepository;

@Service
public class OrderService {

	@Autowired
	private OrderRepository orderRepository;

	public Order insert(Order order) {

		return orderRepository.save(order);

	}

	public List<Order> getAllOrders() {

		List<Order> list= orderRepository.findAll();
		
		return list;
	}

	public Order getOrderById(Long id) {

		return orderRepository.findById(id).get();
		
	}

}
