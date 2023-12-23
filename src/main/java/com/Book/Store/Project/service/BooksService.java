package com.Book.Store.Project.service;

import com.Book.Store.Project.model.Books;

import java.util.List;

public interface BooksService {
    Books addBook(Books books,int authorID,int genreID);
    void delBook(int bookID);
    Books editBook(Books book);
    List<Books> getBooks();
    Books getBook(int bookID);
    Books addStock(int book_id,int quantity);
    List<Books> getByGenre(String genreName);
}
