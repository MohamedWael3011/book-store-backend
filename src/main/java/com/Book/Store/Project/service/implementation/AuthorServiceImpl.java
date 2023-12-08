package com.Book.Store.Project.service.implementation;

import com.Book.Store.Project.model.Author;
import com.Book.Store.Project.repository.AuthorRepository;
import com.Book.Store.Project.service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthorServiceImpl implements AuthorService {
    @Autowired
    private AuthorRepository authorRepository;


    @Override
    public Author addAuthor(Author author) {
        return authorRepository.save(author);
    }

    @Override
    public List<Author> getAuthors() {
        return authorRepository.findAll();
    }
}
