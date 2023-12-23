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

import java.util.List;
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
        System.out.println(book);
        Optional<SaltedUser> user =  usersRepository.findById(user_id);


        if(user.isEmpty()){
            return null;
        }
        Cart userCart = cartRepository.findByUser(user.get());
        if(userCart== null){
            Cart cart = new Cart();

            cart.setUser(user.get());
            userCart = cartRepository.save(cart);
        }
        System.out.println(userCart);

        CartItem cartItem = new CartItem();
        cartItem.setCart(userCart);
        cartItem.setQuantity(book.getQuantity());
        System.out.println("Book ID: " + book.getBook_id());
        int bid = book.getBook_id();
        Optional<Books> b = booksRepository.findById(bid);
        if(b.isEmpty()){
            return null;
        }
        cartItem.setBook(b.get());

        cartItem.setId(new CartItemId(userCart.getCartId(),bid));
        return cartItemRepository.save(cartItem);


    }

    @Override
    public boolean removeItem(int cart_id, int book_id) {
        CartItemId cid = new CartItemId(cart_id,book_id);
        Optional<CartItem> cartItem = cartItemRepository.findById(cid);
        if(cartItem.isPresent()){

            cartItemRepository.delete(cartItem.get());
            return true;
        }
        return  false;
    }

    @Override
    public List<CartItem> getCart(int user_id) {
        Optional<SaltedUser> user = usersRepository.findById(user_id);
        if (user.isPresent()) {
            Cart cart = cartRepository.findByUser(user.get());
            return cartItemRepository.findByCart(cart);


        }
        return null;
    }
}
