package com.example.demo.service;

import com.example.demo.entity.Book;
import java.util.List;

public interface BookService {
    List<Book> getAllBooks();
    Book addBook(Book book);
    List<Book> getBookByName(String title);
    Book updateBook(Integer id, Book book);
    String deleteBook(Integer id);
}
