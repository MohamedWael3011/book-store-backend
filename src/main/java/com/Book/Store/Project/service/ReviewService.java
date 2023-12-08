package com.Book.Store.Project.service;

import com.Book.Store.Project.model.Review;

import java.util.List;

public interface ReviewService {

    Review addReview(Review review,int userID,int bookID);
    List<Review> getReviewsByUser(int userID);
    List<Review> getBookReviews(int bookID);
    List<Review> getReviews();


}
