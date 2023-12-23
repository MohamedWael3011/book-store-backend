package com.Book.Store.Project.controller;


import com.Book.Store.Project.model.Books;
import com.Book.Store.Project.service.BooksService;
import com.Book.Store.Project.service.implementation.BooksServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping({"/api/books"})
@CrossOrigin(origins = "*")

public class BooksController {
    @Autowired
    private BooksService bookService;
    public BooksController(){
        bookService = BooksServiceImpl.getInstance();
    }

    @GetMapping("/all/books")
    public ResponseEntity<?> getAllBooks(){
        return new ResponseEntity<>(bookService.getBooks(), HttpStatus.OK);
    }
    @PutMapping("/edit/books")
    public ResponseEntity<?> editBook(@RequestBody Books books){
        return new ResponseEntity<>(bookService.editBook(books), HttpStatus.OK);
    }

    @GetMapping("/books")
    public ResponseEntity<?> getBooks(@RequestParam("book_id")int book_id){
        return new ResponseEntity<>(bookService.getBook(book_id), HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<?> getBooksByGenre(@RequestParam("genre_name")String genre_name){
        return new ResponseEntity<>(bookService.getByGenre(genre_name), HttpStatus.OK);
    }

}
