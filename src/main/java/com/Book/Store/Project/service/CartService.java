package com.Book.Store.Project.service;

import com.Book.Store.Project.DTO.OrderedBooksDTO;
import com.Book.Store.Project.model.CartItem;

import java.util.List;

public interface CartService {

    CartItem addItem(int user_id,OrderedBooksDTO book);
    boolean removeItem(int cart_id,int book_id);
    List<CartItem> getCart(int user_id);


}
