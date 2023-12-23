package com.Book.Store.Project.repository;

import com.Book.Store.Project.model.Books;
import com.Book.Store.Project.model.Genre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BooksRepository extends JpaRepository<Books,Integer> {
List<Books> findByGenre(Genre genre);
}
