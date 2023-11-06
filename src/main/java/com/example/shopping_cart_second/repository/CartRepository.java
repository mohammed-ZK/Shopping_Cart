package com.example.shopping_cart_second.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.shopping_cart_second.entity.Cart;

@Repository
public interface CartRepository extends JpaRepository<Cart, Long> {
//	@Modifying
//	@Transactional
//	@Query(nativeQuery = true, value = "delete from product_cart where cart_id=:id")
//	void deleteFromShareTable(@Param("id") Long id);

//	@Modifying
//	@Transactional
//	@Query(nativeQuery = true, value = "select * from users u inner join carts c on u.:id=c.:user_id")
//	Cart getMyCart(@Param("id_cart") Long id_cart,@Param("id_user") Long id_user);
}
