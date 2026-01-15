package com.example.demo.service;

import com.example.demo.entity.Book;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Optional;

public interface BookService {
    Page<Book> getBooksPaginated(int page, int size, String sortBy, String direction);
    List<Book> getAllBooks();
    Book addBook(Book book);
    Book updateBook(Integer id, Book book);
    void deleteBook(Integer id);
    List<Book> searchBooks(String keyword);
    Optional<Book> getBookById(Integer id);
}
