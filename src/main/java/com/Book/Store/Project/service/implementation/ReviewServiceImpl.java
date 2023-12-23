package com.Book.Store.Project.service.implementation;

import com.Book.Store.Project.model.Books;
import com.Book.Store.Project.model.Review;
import com.Book.Store.Project.model.Users.PlainUser;
import com.Book.Store.Project.model.Users.SaltedUser;
import com.Book.Store.Project.repository.BooksRepository;
import com.Book.Store.Project.repository.ReviewRepository;
import com.Book.Store.Project.repository.UsersRepository;
import com.Book.Store.Project.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReviewServiceImpl implements ReviewService {
    @Autowired
    private ReviewRepository reviewRepository;
    @Autowired
    private UsersRepository usersRepository;
    @Autowired
    private BooksRepository booksRepository;

    @Override
    public Review addReview(Review review,int userID, int bookID) {
        SaltedUser u = usersRepository.findById(userID).orElseThrow(() -> { return new RuntimeException("There is no Book with this ID");});
        Books b = booksRepository.findById(bookID).orElseThrow(() -> { return new RuntimeException("There is no Book with this ID");});
        review.setBook(b);
        review.setUser(u);
        return reviewRepository.save(review);
    }

    @Override
    public List<Review> getReviewsByUser(int userID) {
        try{
        return reviewRepository.findByUserId(userID);}
        catch (Exception e){
            System.out.println(e.getMessage());
            return null;
        }
    }

    @Override
    public List<Review> getBookReviews(int bookID) {
        try{
            return reviewRepository.findByBookId(bookID);}
        catch (Exception e){
            System.out.println(e.getMessage());
            return null;
        }
    }

    @Override
    public List<Review> getReviews() {
        return reviewRepository.findAll();
    }
}
