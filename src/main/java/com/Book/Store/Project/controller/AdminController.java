package com.Book.Store.Project.controller;


import com.Book.Store.Project.model.Books;
import com.Book.Store.Project.model.Genre;
import com.Book.Store.Project.repository.BooksRepository;
import com.Book.Store.Project.repository.GenreRepository;
import com.Book.Store.Project.repository.UsersRepository;
import com.Book.Store.Project.service.BooksService;
import com.Book.Store.Project.service.GenreService;
import com.Book.Store.Project.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping({"/api/admin"})
@CrossOrigin(origins = "*")
public class AdminController {
    @Autowired
    GenreService genreService;
    @Autowired
    BooksService booksService;
    @Autowired
    UsersService usersService;

    @PostMapping("/add/genre")
    public ResponseEntity<?> addGenre(@RequestBody Genre genre){
        return new ResponseEntity<>(genreService.addGenre(genre), HttpStatus.OK);

    }
    @DeleteMapping("/delete/genre")
    public ResponseEntity<?> deleteGenre(@RequestParam("genre_id") int genre_id){
        return new ResponseEntity<>(genreService.delGenre(genre_id), HttpStatus.OK);
    }
    @PostMapping("/add/book")
    public ResponseEntity<?> addBook(@RequestBody Books books, @RequestParam(name = "author_id",defaultValue = "1") int author_id, @RequestParam(name = "genre_id",defaultValue = "1") int genre_id){
        return new ResponseEntity<>(booksService.addBook(books,author_id,genre_id), HttpStatus.OK);
    }

    @PostMapping("/add/stock")
    public ResponseEntity<?> addStock(@RequestParam(name = "book_id") int book_id,@RequestParam("quantity") int quantity){
        return new ResponseEntity<>(booksService.addStock(book_id,quantity), HttpStatus.OK);
    }
    @DeleteMapping("/delete/books")
    public ResponseEntity<?> delBook(@RequestParam("book_id") int book_id){
        booksService.delBook(book_id);
        return new ResponseEntity<>("Deleted", HttpStatus.OK);
    }
}
