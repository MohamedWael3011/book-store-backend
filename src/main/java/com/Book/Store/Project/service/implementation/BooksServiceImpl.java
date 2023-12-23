package com.Book.Store.Project.service.implementation;

import com.Book.Store.Project.model.Author;
import com.Book.Store.Project.model.Books;
import com.Book.Store.Project.model.Genre;
import com.Book.Store.Project.repository.AuthorRepository;
import com.Book.Store.Project.repository.GenreRepository;
import com.Book.Store.Project.service.BooksService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.Book.Store.Project.repository.BooksRepository;

import java.util.List;

@Service
public class BooksServiceImpl implements BooksService {

    static BooksService instance = null;
    @Autowired
    private  BooksRepository booksRepository;
    @Autowired
    private AuthorRepository authorRepository;
    @Autowired
    private GenreRepository genreRepository;


    private  BooksServiceImpl(){
    }
    public static BooksService getInstance(){
        if(instance == null){
            instance = new BooksServiceImpl();
        }
        return  instance;
    }

    @Override
    public Books addBook(Books books,int authorID,int genreID) {
        books.setAuthor(authorRepository.findById(authorID).orElseThrow(() -> {
            return new RuntimeException("There is no Author with this ID");}));
        books.setGenre(genreRepository.findById(genreID).orElseThrow(() -> {
            return new RuntimeException("There is no Genre with this ID");}));

        return booksRepository.save(books);
    }

    @Override
    public void delBook(int bookID) {
        Books book = booksRepository.findById(bookID).orElseThrow(() -> {
            return new RuntimeException("There is no Book with this ID");
        });
        booksRepository.deleteById(bookID);
    }

    @Override
    public Books editBook(Books book) {
        Books b = booksRepository.findById(book.getId()).orElseThrow(() -> {
            return new RuntimeException("There is no Book with this ID");
        });
        Author author = authorRepository.findByName(book.getAuthor().getName());
        book.setAuthor(author);
        return booksRepository.saveAndFlush(book);
    }

    @Override
    public List<Books> getBooks() {
        return booksRepository.findAll();
    }

    @Override
    public Books getBook(int bookID) {
        return booksRepository.findById(bookID).orElseThrow(() -> {
            return new RuntimeException("There is no Book with this ID");
        });
    }

    @Override
    public Books addStock(int bookID, int quantity) {
            Books book = booksRepository.findById(bookID).orElseThrow(() -> {
            return new RuntimeException("There is no Book with this ID");
        });;

        book.setQuantity_in_stock(book.getQuantity_in_stock() + quantity);
        return booksRepository.save(book);

    }

    @Override
    public List<Books> getByGenre(String genreName) {
        Genre genre = genreRepository.findByName(genreName);
        return  booksRepository.findByGenre(genre);
    }
}
