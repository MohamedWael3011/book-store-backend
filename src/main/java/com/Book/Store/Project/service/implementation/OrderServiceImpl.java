package com.Book.Store.Project.service.implementation;

import com.Book.Store.Project.DTO.OrderedBooksDTO;
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
    public List<Order_Book> makeOrder(int user_id, List<OrderedBooksDTO> books, String paymentMethod) {
        Optional<SaltedUser> user =  usersRepository.findById(user_id);
        Orders order = new Orders();
        if(user.isEmpty()){
            return null;
        }

        order.setUser(user.get());
        order.setOrder_date(Date.from(Instant.now()));
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
}
