package com.Book.Store.Project.repository;


import com.Book.Store.Project.model.Orders;
import com.Book.Store.Project.model.Users.PlainUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrdersRepository extends JpaRepository<Orders,Integer> {
    List<Orders> findByUser(PlainUser user);
}
