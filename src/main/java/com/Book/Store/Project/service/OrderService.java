package com.Book.Store.Project.service;

import com.Book.Store.Project.DTO.OrderedBooksDTO;
import com.Book.Store.Project.DTO.ShippingInfoDTO;
import com.Book.Store.Project.model.Books;
import com.Book.Store.Project.model.Order_Book;
import com.Book.Store.Project.model.Orders;
import com.Book.Store.Project.model.Users.PlainUser;
import com.Book.Store.Project.repository.OrderBookRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public interface OrderService {




    List<Order_Book> makeOrder(int user_id, List<OrderedBooksDTO> books, String paymentMethod, ShippingInfoDTO shipping);

    List<Orders> getOrders();
    List<Orders> getPendingOrders(String status);
    Orders getOrder(int order_id);
    int delOrder(int order_id,int user_id);

    List<Orders> getOrdersByUser(int user_id);
    public int acceptOrder(int order_id, int user_id);

}
