package com.example.shopping_cart_second.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;

@Configuration
public class RabbitMQConfig {

	@Value("${rabbitmq.queue.name}")
	private String Queue;

	@Value("${rabbitmq.exchange.name}")
	private String Exchange;

	@Value("${rabbitmq.routing.key}")
	private String routingKey;

	@Bean
	public Queue queue() {
		return new Queue(Queue);
	}

	@Bean
	public TopicExchange exchange() {
		return new TopicExchange(Exchange);
	}

	@Bean
	public Binding binding() {
		return BindingBuilder.bind(queue()).to(exchange()).with(routingKey);
	}

}
