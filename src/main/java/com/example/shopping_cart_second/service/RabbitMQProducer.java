package com.example.shopping_cart_second.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.example.shopping_cart_second.entity.Order;

@Service
public class RabbitMQProducer {

	private static final Logger log = LoggerFactory.getLogger(RabbitMQProducer.class);

	@Value("${rabbitmq.exchange.name}")
	private String Exchange;

	@Value("${rabbitmq.routing.key}")
	private String routingKey;

	private RabbitTemplate rabbitTemplate;

	public RabbitMQProducer(RabbitTemplate rabbitTemplate) {
		super();
		this.rabbitTemplate = rabbitTemplate;
	}

	public void sendMessage(Order order) {
//		MessageConverter converter=new Jackson2JsonMessageConverter(order);
		log.info(order.getUserId() + "====>" + order.getCardId());
		String message = order.getUserId() + "," + order.getCardId() + "," + order.getToken();
		rabbitTemplate.convertAndSend(Exchange, routingKey, message);
		log.info(order.getUserId() + "====>" + order.getCardId() + "====>" + order.getToken());
	}

}
