package com.Book.Store.Project.repository;

import com.Book.Store.Project.model.Order_Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface OrderBookRepository extends JpaRepository<Order_Book,Order_Book.CompositeKey > {

}
