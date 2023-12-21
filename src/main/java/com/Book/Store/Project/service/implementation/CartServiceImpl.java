package com.Book.Store.Project.service.implementation;

import com.Book.Store.Project.DTO.OrderedBooksDTO;
import com.Book.Store.Project.model.Books;
import com.Book.Store.Project.model.Cart;
import com.Book.Store.Project.model.CartItem;
import com.Book.Store.Project.model.CartItemId;
import com.Book.Store.Project.model.Users.SaltedUser;
import com.Book.Store.Project.repository.BooksRepository;
import com.Book.Store.Project.repository.CartItemRepository;
import com.Book.Store.Project.repository.CartRepository;
import com.Book.Store.Project.repository.UsersRepository;
import com.Book.Store.Project.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CartServiceImpl implements CartService {

    @Autowired
    private UsersRepository userRepository;

    @Autowired
    private CartRepository cartRepository;
    @Autowired
    private CartItemRepository cartItemRepository;
    @Autowired
    private BooksRepository booksRepository;

    @Autowired
    private UsersRepository usersRepository;

    @Override
    public CartItem addItem(int user_id,OrderedBooksDTO book) {
        Optional<SaltedUser> user =  usersRepository.findById(user_id);
        Cart cart = new Cart();
        if(user.isEmpty()){
            return null;
        }
        cart.setUser(user.get());
        Cart userCart = cartRepository.save(cart);
        CartItem cartItem = new CartItem();
        cartItem.setCart(userCart);
        cartItem.setQuantity(book.getQuantity());
        Optional<Books> b = booksRepository.findById(book.getBook_id());
        if(b.isEmpty()){
            return null;
        }
        cartItem.setBook(b.get());
        return cartItemRepository.save(cartItem);


    }

    @Override
    public void removeItem(int cart_id, int book_id) {
        CartItemId cid = new CartItemId(cart_id,book_id);
        Optional<CartItem> cartItem = cartItemRepository.findById(cid);
        cartItem.ifPresent(item -> cartItemRepository.delete(item));
    }
}
