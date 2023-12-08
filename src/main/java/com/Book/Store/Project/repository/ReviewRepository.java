package com.Book.Store.Project.repository;

import com.Book.Store.Project.model.Review;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReviewRepository extends JpaRepository<Review,Integer> {
    List<Review> findByBookId(Integer book_Id);
    List<Review> findByUserId(Integer user_Id);


}
