package com.Book.Store.Project.repository;

import com.Book.Store.Project.model.Cart;
import com.Book.Store.Project.model.Users.SaltedUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartRepository extends JpaRepository<Cart, Long> {

    Cart findByUser(SaltedUser user);
}