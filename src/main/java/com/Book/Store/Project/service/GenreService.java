package com.Book.Store.Project.service;

import com.Book.Store.Project.model.Genre;

import java.util.List;

public interface GenreService {
    Genre addGenre(Genre genre);
    boolean delGenre(int genreID);
    List<Genre> getGenres();

    Genre getGenre(String genreName);
}
