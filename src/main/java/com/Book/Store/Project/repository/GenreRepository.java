package com.Book.Store.Project.repository;


import com.Book.Store.Project.model.Genre;
import com.Book.Store.Project.model.Users.PlainUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GenreRepository extends JpaRepository<Genre,Integer> {
    Genre findByName(String username);

}
