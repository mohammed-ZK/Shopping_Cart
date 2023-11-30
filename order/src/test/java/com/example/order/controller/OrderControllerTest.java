package com.example.order.controller;

import static org.junit.Assert.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.order.Entity.Order;
import com.example.order.service.OrderService;

@SpringBootTest
public class OrderControllerTest {

	@Autowired
	private OrderService orderService;

	@Test
	void insertTrueTest() {
		Order order = new Order(1L,1L);
		Order order2 = orderService.insert(order);
//		assertNotEquals(order2, order);
	}

	@Test
	void insertFalseTest() {

	}

	@Test
	void getAllOrdersNotEmptyTest() {

	}

	@Test
	void getAllOrdersEmptyTest() {

	}

	@Test
	void getOrderByIdFalidTest() {

	}

	@Test
	void getOrderByIdNotFalidTest() {

	}
}
