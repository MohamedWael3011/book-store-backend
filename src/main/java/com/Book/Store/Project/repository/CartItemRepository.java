package com.Book.Store.Project.repository;

import com.Book.Store.Project.model.Cart;
import com.Book.Store.Project.model.CartItem;
import com.Book.Store.Project.model.CartItemId;
import com.Book.Store.Project.model.Users.SaltedUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CartItemRepository extends JpaRepository<CartItem, CartItemId> {
    List<CartItem> findByCart(Cart cart);

}