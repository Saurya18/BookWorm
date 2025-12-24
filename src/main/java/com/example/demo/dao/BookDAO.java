package com.example.demo.dao;

import com.example.demo.entity.Book;
import java.util.List;

public interface BookDAO {
    List<Book> getAllBooks();
    Book addBook(Book book);
    List<Book> getBookByName(String title);
    String deleteBook(Integer id);
    Book updateBook(Integer id, Book book);
}
