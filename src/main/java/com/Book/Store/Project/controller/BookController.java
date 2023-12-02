package com.Book.Store.Project.controller;


import com.Book.Store.Project.model.Book;
import com.Book.Store.Project.service.BookService;
import com.Book.Store.Project.service.implementation.BookServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping({"/api/post"})
public class BookController {
    BookService bookService;
    public BookController(){
        this.bookService = BookServiceImpl.getInstance();
    }

    @GetMapping("/allBooks")
    public ResponseEntity<?> getAllBooks(){
        return new ResponseEntity<>(bookService.getBooks(), HttpStatus.OK);
    }

    @PostMapping("/addBook")
    public ResponseEntity<?> addBook(@RequestBody Book book){
        return new ResponseEntity<>(bookService.addBook(book), HttpStatus.OK);
    }
}
