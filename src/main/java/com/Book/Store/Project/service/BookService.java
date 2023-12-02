package com.Book.Store.Project.service;

import com.Book.Store.Project.model.Book;

import java.util.List;
import java.util.Optional;

public interface BookService {
    Book addBook( Book book);
    void delBook(int bookID);
    Book editBook(Book post);
    List<Book> getBooks();
    Optional<Book> getBook(int bookID);
}
