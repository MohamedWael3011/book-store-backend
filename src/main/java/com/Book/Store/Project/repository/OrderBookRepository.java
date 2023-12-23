package com.Book.Store.Project.repository;

import com.Book.Store.Project.model.Order_Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface OrderBookRepository extends JpaRepository<Order_Book,Order_Book.CompositeKey > {

    @Query(value = "SELECT * FROM order_book WHERE order_id=?", nativeQuery = true)
    List<Order_Book> findByOrderId(int uid);

}
