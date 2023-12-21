package com.Book.Store.Project.repository;

import com.Book.Store.Project.model.CartItem;
import com.Book.Store.Project.model.CartItemId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartItemRepository extends JpaRepository<CartItem, CartItemId> {
    // You can add custom query methods here if needed
}