package com.Book.Store.Project.controller;


import com.Book.Store.Project.model.Review;
import com.Book.Store.Project.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping({"/api/review"})
@CrossOrigin(origins = "*")

public class ReviewController {
    @Autowired
    private ReviewService reviewService;


    @GetMapping("/all")
    public ResponseEntity<?> getAllReviews(){
        return new ResponseEntity<>(reviewService.getReviews(), HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<?> addBook(@RequestBody Review review, @RequestParam("user_id") int user_id,@RequestParam("book_id") int book_id){
        return new ResponseEntity<>(reviewService.addReview(review,user_id,book_id), HttpStatus.OK);
    }
    @GetMapping("/all/book")
    public ResponseEntity<?> getAllBookReviews(@RequestParam("book_id") int book_id){
        return new ResponseEntity<>(reviewService.getBookReviews(book_id), HttpStatus.OK);
    }
    @GetMapping("/all/user")
    public ResponseEntity<?> getAllUserReviews(@RequestParam("user_id") int user_id){
        return new ResponseEntity<>(reviewService.getReviewsByUser(user_id), HttpStatus.OK);
    }

}
