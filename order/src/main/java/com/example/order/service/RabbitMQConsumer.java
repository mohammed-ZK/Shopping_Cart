package com.example.order.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.order.Entity.Order;

@Service
public class RabbitMQConsumer {

	private static final Logger log = LoggerFactory.getLogger(RabbitMQConsumer.class);

	@Autowired
	OrderService orderService;

	@RabbitListener(queues = "${rabbitmq.queue.name}")
	public void counsumer(String message) {

		Order order = new Order();
		
		String[] arrOfStr = message.split(",", 2);

		for (String a : arrOfStr)
			log.info("=====>" + a);

		order.setUserId(Long.parseLong(arrOfStr[0]));
		order.setCardId(Long.parseLong(arrOfStr[1]));
		
		orderService.insert(order);

	}

}
