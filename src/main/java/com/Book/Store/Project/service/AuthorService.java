package com.Book.Store.Project.service;

import com.Book.Store.Project.model.Author;

import java.util.List;

public interface AuthorService {
    Author addAuthor(Author author);
    List<Author> getAuthors();
}
