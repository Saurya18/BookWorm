package com.example.demo.dao;

import com.example.demo.entity.Book;
import java.util.List;

public interface BookDAO {
    List<Book> getAllBooks();
    Book addBook(Book book);
    Book getBookByName(String title);
    Book updateBook(Integer id, Book book);
    void deleteBook(Integer id);
}
