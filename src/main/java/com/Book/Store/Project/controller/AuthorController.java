package com.Book.Store.Project.controller;


import com.Book.Store.Project.model.Author;
import com.Book.Store.Project.service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping({"/api/author"})
@CrossOrigin(origins = "*")
public class AuthorController {
    @Autowired
    private AuthorService authorService;


    @GetMapping("/all/authors")
    public ResponseEntity<?> getAllBooks(){
        return new ResponseEntity<>(authorService.getAuthors(), HttpStatus.OK);
    }

    @PostMapping("/add/author")
    public ResponseEntity<?> addAuthor(@RequestBody Author author){
        return new ResponseEntity<>(authorService.addAuthor(author), HttpStatus.OK);
    }
}
