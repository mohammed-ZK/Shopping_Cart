package com.example.shopping_cart_second.entity;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "carts")
public class Cart {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private BigDecimal totalprice;

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id",unique = true)
//	@JoinTable(name = "user_cart", joinColumns = @JoinColumn(name = "cart_id"), inverseJoinColumns = @JoinColumn(name = "user_id"))
	User user = new User();

//	@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
//	@OnDelete(action = OnDeleteAction.CASCADE)
//	@JoinTable(name = "product_cart", joinColumns = @JoinColumn(name = "cart_id"), inverseJoinColumns = @JoinColumn(name = "product_id"))
//	private Set<Product> products = new HashSet<>();
	
	@OneToMany
	private List<Product> products =new ArrayList<>();

	public Cart(Long id, BigDecimal totalprice) {
		super();
		this.id = id;
		this.totalprice = totalprice;
	}

	public Cart() {
		super();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public BigDecimal getTotalprice() {
		return totalprice;
	}

	public void setTotalprice(BigDecimal totalprice) {
		this.totalprice = totalprice;
	}

	public List<Product> getProducts() {
		return products;
	}

	public void setProducts(List<Product> list) {
		this.products = list;
		
	}
	
	

//	public Set<Product> getProducts() {
//		return products;
//	}
//
//	public void setProducts(Set<Product> products) {
//		this.products = products;
//	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}
