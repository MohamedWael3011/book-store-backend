package com.Book.Store.Project.service.implementation;

import com.Book.Store.Project.DTO.OrderedBooksDTO;
import com.Book.Store.Project.DTO.ShippingInfoDTO;
import com.Book.Store.Project.model.Books;
import com.Book.Store.Project.model.Order_Book;
import com.Book.Store.Project.model.Orders;
import com.Book.Store.Project.model.Payment;
import com.Book.Store.Project.model.Users.SaltedUser;
import com.Book.Store.Project.repository.OrderBookRepository;
import com.Book.Store.Project.repository.OrdersRepository;
import com.Book.Store.Project.repository.PaymentRepository;
import com.Book.Store.Project.repository.UsersRepository;
import com.Book.Store.Project.service.BooksService;
import com.Book.Store.Project.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    OrderBookRepository orderBookRepository;
    @Autowired
    OrdersRepository ordersRepository;
    @Autowired
    PaymentRepository paymentRepository;
    @Autowired
    UsersRepository usersRepository;
    @Autowired
    BooksService booksService;

    @Override
    public List<Order_Book> makeOrder(int user_id, List<OrderedBooksDTO> books, String paymentMethod, ShippingInfoDTO shipping) {
        Optional<SaltedUser> user =  usersRepository.findById(user_id);
        Orders order = new Orders();
        if(user.isEmpty()){
            return null;
        }

        order.setUser(user.get());
        order.setOrder_date(Date.from(Instant.now()));
        order.setUser_address(shipping.getUser_address());
        order.setUser_city(shipping.getUser_city());
        order.setUser_buildingno(shipping.getUser_buildingno());
        order.setUser_phone(shipping.getUser_phone());
        order.setOrderStatus("Pending Approval");



        Orders madeOrder = ordersRepository.save(order);
        List<Order_Book> orders = new ArrayList<>();
        books.forEach(book -> {
            Order_Book orderBook = new Order_Book();
            orderBook.setOrder_id(madeOrder.getId());
            orderBook.setBook_id(book.getBook_id());
            orderBook.setQuantity(book.getQuantity());
            orders.add(orderBook);
            orderBookRepository.save(orderBook);
        });

        Payment pay = new Payment();
        pay.setOrder(madeOrder);
        pay.setPayment_method(paymentMethod);

        pay.setPayment_amount(15);

        double price = 0;

        for(OrderedBooksDTO book : books)
         {
            Books currBook = booksService.getBook(book.getBook_id());
            currBook.setNum_sales(currBook.getNum_sales() + book.getQuantity());
            price += (currBook.getPrice() * book.getQuantity());
         }

        pay.setPayment_amount(price);


        paymentRepository.save(pay);
        return orders;

    }

    @Override
    public List<Orders> getOrders() {
        return ordersRepository.findAll();
    }

    @Override
    public List<Orders> getPendingOrders(String status) {
        return ordersRepository.findAllByOrderStatus(status);
    }

    @Override
    public Orders getOrder(int order_id) {
        Optional<Orders> order =  ordersRepository.findById(order_id);
        return order.orElse(null);
    }

    @Override
    public int delOrder(int order_id, int user_id) {
        Orders order = getOrder(order_id);
        if(order == null){
            return -1;
        }
        else if(order.getUser().getId() != user_id){
            return 0;
        }
        else{
            long difference_In_Time = order.getOrder_date().getTime() - Date.from(Instant.now()).getTime();
            long difference_In_Days
                    = (difference_In_Time
                    / (1000 * 60 * 60 * 24))
                    % 365;

            if(difference_In_Days <= 2) {
                List<Order_Book> orderBooks = orderBookRepository.findByOrderId(order_id);
                orderBookRepository.deleteAll(orderBooks);
                ordersRepository.deleteById(order_id);
                return 1;
            }
            return 2;
        }
    }

    @Override
    public int acceptOrder(int order_id, int user_id) {
        Orders order = getOrder(order_id);
        if(order == null){
            return -1;
        }
        else if(order.getUser().getId() != user_id){
            return 0;
        }
        else{
            order.setOrderStatus("Accepted");
            ordersRepository.saveAndFlush(order);
            return 1;
        }
    }
    @Override
    public List<Orders> getOrdersByUser(int user_id) {
        Optional<SaltedUser> orderUser = usersRepository.findById(user_id);
        if(orderUser.isPresent()){
            List<Orders> orders = ordersRepository.findByUser(orderUser.get());
            return orders;
        }
        return null;
    }


}
