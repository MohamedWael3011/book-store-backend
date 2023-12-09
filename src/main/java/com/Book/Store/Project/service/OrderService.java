package com.Book.Store.Project.service;

import com.Book.Store.Project.DTO.OrderedBooksDTO;
import com.Book.Store.Project.model.Books;
import com.Book.Store.Project.model.Order_Book;
import com.Book.Store.Project.repository.OrderBookRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public interface OrderService {




    List<Order_Book> makeOrder(int user_id, List<OrderedBooksDTO> books, String paymentMethod);
}
