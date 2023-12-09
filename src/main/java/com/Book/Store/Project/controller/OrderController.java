package com.Book.Store.Project.controller;


import com.Book.Store.Project.DTO.OrderedBooksDTO;
import com.Book.Store.Project.model.Order_Book;
import com.Book.Store.Project.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping({"/api/order"})
@CrossOrigin(origins = "*")
public class OrderController {
    @Autowired
    OrderService orderService;

    @PostMapping("/makeorder")
    ResponseEntity<?> makeOrder(@RequestParam(name = "user_id") int user_id, @RequestParam(name = "payment_method") String payment_method, @RequestBody List<OrderedBooksDTO> books){
        List<Order_Book> orders = orderService.makeOrder(user_id,books,payment_method);
        if (orders == null){
            return new ResponseEntity<>("Invalid Transaction", HttpStatus.NOT_ACCEPTABLE);
        }
        return new ResponseEntity<>(orders ,HttpStatus.OK);

    }
}
