package com.Book.Store.Project.controller;


import com.Book.Store.Project.model.Genre;
import com.Book.Store.Project.service.GenreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping({"/api/genre"})
@CrossOrigin(origins = "*")
public class GenreController {

    @Autowired
    GenreService genreService;

    @GetMapping("/all")
    ResponseEntity<?> getGenres(){
        return new ResponseEntity<>( genreService.getGenres(), HttpStatus.OK);
    }
    @GetMapping("/get")
    ResponseEntity<?> getGenreByName(@RequestParam("genre_name") String name){
        return new ResponseEntity<>( genreService.getGenre(name), HttpStatus.OK);
    }
}
