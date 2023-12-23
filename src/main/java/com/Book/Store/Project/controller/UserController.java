package com.Book.Store.Project.controller;


import com.Book.Store.Project.DTO.OrderedBooksDTO;
import com.Book.Store.Project.DTO.UserDTO;
import com.Book.Store.Project.model.Users.PlainUser;
import com.Book.Store.Project.service.CartService;
import com.Book.Store.Project.service.ProxyService;
import com.Book.Store.Project.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping({"/api/user"})
@CrossOrigin(origins = "*")
public class UserController {
    @Autowired
    private UsersService usersService;
    @Autowired
    private ProxyService proxyService;

    @Autowired
    private CartService cartService;


    @GetMapping("/all/users")
    public ResponseEntity<?> getAllBooks(){
        return new ResponseEntity<>(usersService.getUsers(), HttpStatus.OK);
    }

    @PostMapping("/register")
    public ResponseEntity<?> addAuthor(@RequestBody PlainUser user) throws NoSuchAlgorithmException {

        UserDTO register = usersService.addUser(user);
        if (register == null){
           Map<String, String> response = new HashMap<>();
            response.put("error", "Register Failed, Username already exists.");
            return new ResponseEntity<>(response, HttpStatus.UNAUTHORIZED);
        }
        return new ResponseEntity<>( register, HttpStatus.OK);
    }

    @PostMapping("/login")
    public  ResponseEntity<?> login(@RequestBody PlainUser user) throws NoSuchAlgorithmException {
        Integer loginSuccess = proxyService.login(user);
        if(loginSuccess == -2){
            return  new ResponseEntity<>(user, HttpStatus.OK);
        }
        if (loginSuccess != -1) {
            UserDTO userDTO = usersService.getUser(loginSuccess);
            return new ResponseEntity<>(userDTO, HttpStatus.OK);
        } else {
            Map<String, String> response = new HashMap<>();
            response.put("error", "Login failed. Invalid credentials.");
            return new ResponseEntity<>(response, HttpStatus.UNAUTHORIZED);
        }

    }

    @GetMapping("/cart")
    public  ResponseEntity<?>getCart(@RequestParam("user_id") int user_id){
        return  new ResponseEntity<>(cartService.getCart(user_id),HttpStatus.OK);
    }

    @PostMapping("/cart/add")
    public  ResponseEntity<?>addCart(@RequestParam("user_id") int user_id, @RequestBody OrderedBooksDTO orderedBooksDTO){
        return  new ResponseEntity<>(cartService.addItem(user_id,orderedBooksDTO),HttpStatus.OK);
    }
    @DeleteMapping("/cart/del")
    public  ResponseEntity<?>delCart(@RequestParam("cart_id") int cart_id, @RequestParam("book_id") int book_id){
        return  new ResponseEntity<>(cartService.removeItem(cart_id,book_id),HttpStatus.OK);
    }
}
