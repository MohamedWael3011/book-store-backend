package com.Book.Store.Project.controller;


import com.Book.Store.Project.DTO.OrderedBooksDTO;
import com.Book.Store.Project.DTO.ShippingInfoDTO;
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
    ResponseEntity<?> makeOrder(@RequestParam(name = "user_id") int user_id, @RequestParam(name = "payment_method") String payment_method,
                                @RequestBody List<OrderedBooksDTO> books,
                                @RequestParam(name = "address") String address,
                                @RequestParam(name = "city") String city,
                                @RequestParam(name = "building") String building,
                                @RequestParam(name = "phone") String phone
                                ){
        List<Order_Book> orders = orderService.makeOrder(user_id,books,payment_method,new ShippingInfoDTO(address,city,building,phone));
        if (orders == null){
            return new ResponseEntity<>("Invalid Transaction", HttpStatus.NOT_ACCEPTABLE);
        }
        return new ResponseEntity<>(orders ,HttpStatus.OK);
    }

    @DeleteMapping("/delete")
    ResponseEntity<?> cancelOrder (@RequestParam(name = "order_id") int order_id,@RequestParam(name = "user_id") int user_id){
        int status = orderService.delOrder(order_id,user_id);
        if(status == -1) {
            return new ResponseEntity<>("There is no order with that ID", HttpStatus.NOT_FOUND);
        }
        else if(status == 0){
            return new ResponseEntity<>("This isn't your order", HttpStatus.UNAUTHORIZED);

        }
        else if(status == 2){
            return new ResponseEntity<>("Order can't be cancelled now, two days has been passed", HttpStatus.UNAUTHORIZED);

        }
        return new ResponseEntity<>("Order has been cancelled successfully", HttpStatus.OK);

    }

    @GetMapping("/get/all")
    ResponseEntity<?> getAllOrders(){
        return  new ResponseEntity<>(orderService.getOrders(),HttpStatus.OK);
    }
    @GetMapping("/get")
    ResponseEntity<?> getOrdersStatus(@RequestParam("status") String status){
        return  new ResponseEntity<>(orderService.getPendingOrders(status),HttpStatus.OK);
    }
    @GetMapping("/get/byUser")
    ResponseEntity<?> getOrderUser(@RequestParam(name = "user_id") int user_id){
        return  new ResponseEntity<>(orderService.getOrdersByUser(user_id),HttpStatus.OK);
    }

}
