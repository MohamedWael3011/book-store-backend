package com.Book.Store.Project.service.implementation;

import com.Book.Store.Project.model.Books;
import com.Book.Store.Project.model.Genre;
import com.Book.Store.Project.repository.GenreRepository;
import com.Book.Store.Project.service.GenreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GenreInterfaceImpl implements GenreService {
    @Autowired
    GenreRepository genreRepository;
    @Override
    public Genre addGenre(Genre genre) {
        return genreRepository.save(genre);
    }

    @Override
    public boolean delGenre(String genreID) {
        Genre genreOptional = genreRepository.findByName(genreID);
        if (genreOptional != null){
            genreRepository.delete(genreOptional);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public List<Genre> getGenres() {
        return genreRepository.findAll();
    }

    @Override
    public Genre getGenre(String genreName) {
        return genreRepository.findByName(genreName);
    }
}
