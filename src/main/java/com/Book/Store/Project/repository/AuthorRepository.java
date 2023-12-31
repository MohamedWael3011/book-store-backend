package com.Book.Store.Project.repository;

import com.Book.Store.Project.model.Author;
import com.Book.Store.Project.model.Genre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthorRepository extends JpaRepository<Author,Integer> {
    Author findByName(String name);

}
