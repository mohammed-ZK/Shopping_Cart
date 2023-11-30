package com.example.shopping_cart_second.entity;

public class Order {

	private Long id;

	private Long userId;

	private Long cardId;
	
	private String token="sss";
	
	public Order() {
		super();
	}

	public Order(Long userId, Long cardId) {
		super();
		this.userId = userId;
		this.cardId = cardId;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Long getCardId() {
		return cardId;
	}

	public void setCardId(Long cardId) {
		this.cardId = cardId;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}
	
}
