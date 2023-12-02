package com.Book.Store.Project.service.implementation;

import com.Book.Store.Project.model.Book;
import com.Book.Store.Project.service.BookService;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Optional;


public class BookServiceImpl implements BookService {
    final String filePath = "src/main/resources/books.json";
    ObjectMapper objectMapper = new ObjectMapper();
    static BookService  instance = null;

    private BookServiceImpl(){

    }


    public static BookService getInstance(){
        if(instance == null){
            return new BookServiceImpl();
        }
        return instance;
    }
    @Override
    public Book addBook(Book book) {
        try {
            List<Book> books = getBooks();
            books.add((Book)book.clone());
            objectMapper.writeValue(new File(filePath), books);
            return book;

        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public void delBook(int bookID) {
        try {
            List<Book> books = getBooks();
            for(Book b : books){
                if(b.getBookID() == bookID){
                    books.remove(b);
                    return;
                }
            }
            objectMapper.writeValue(new File(filePath), books);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Book editBook(Book post) {
        return null;
    }

    @Override
    public List<Book> getBooks() {
            try {
                List<Book> books = objectMapper.readValue(new File(filePath), new TypeReference<List<Book>>() {});

                return books;

        } catch (IOException e) {
            e.printStackTrace();
         return null;
        }
    }

    @Override
    public Optional<Book> getBook(int bookID) {
        return Optional.empty();
    }
}
