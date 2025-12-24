package com.example.demo.service.impl;

import com.example.demo.dao.BookDAO;
import com.example.demo.entity.Book;
import com.example.demo.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class BookServiceImpl implements BookService {

    @Autowired
    private BookDAO bookDAO;

    @Override
    public List<Book> getAllBooks() {
        try {
            List<Book> books = bookDAO.getAllBooks();
            System.out.println("Service - BooksList: " + books);
            return books;
        } catch (Exception e) {
            throw new RuntimeException("Service Error while fetching books: " + e.getMessage());
        }
    }

    @Override
    public Book addBook(Book book) {
        try {
            Book saved = bookDAO.addBook(book);
            System.out.println("Service - SavedBook: " + saved);
            return saved;
        } catch (Exception e) {
            throw new RuntimeException("Service Error while saving book: " + e.getMessage());
        }
    }

    @Override
    public List<Book> getBookByName(String title) {
        try {
            return bookDAO.getBookByName(title);
        } catch (Exception e) {
            throw new RuntimeException("Service Error while finding book: " + e.getMessage());
        }
    }

    @Override
    public String deleteBook(Integer id) {
        try {
            return bookDAO.deleteBook(id);
        } catch (Exception e) {
            throw new RuntimeException("Service Error while deleting book: " + e.getMessage());
        }
    }

    @Override
    public Book updateBook(Integer id, Book book) {
        try {
            return bookDAO.updateBook(id, book);
        } catch (Exception e) {
            throw new RuntimeException("Service Error while updating book: " + e.getMessage());
        }
    }
}
