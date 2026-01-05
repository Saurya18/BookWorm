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
        List<Book> list = null;
        try {
            list = bookDAO.getAllBooks();
            System.out.println("Service: All Books fetched");
        } catch (Exception e) {
            throw new RuntimeException("Error fetching books: " + e.getMessage());
        }
        return list;
    }

    @Override
    public Book addBook(Book book) {
        Book saved = null;
        try {
            saved = bookDAO.addBook(book);
            System.out.println("Service: Book added → " + saved);
        } catch (Exception e) {
            throw new RuntimeException("Error adding book: " + e.getMessage());
        }
        return saved;
    }

    @Override
    public List<Book> getBookByName(String title) {
        List<Book> books = null;
        try {
            books = bookDAO.getBookByName(title);
            System.out.println("Service: Books found → " + books);
        } catch (Exception e) {
            throw new RuntimeException("Error finding book: " + e.getMessage());
        }
        return books;
    }

    @Override
    public void deleteBook(Integer id) {
        try {
            bookDAO.deleteBook(id);
            System.out.println("Service: Deleted book id → " + id);
        } catch (Exception e) {
            throw new RuntimeException("Error deleting book: " + e.getMessage());
        }
    }

    @Override
    public Book updateBook(Integer id, Book book) {
        Book updated = null;
        try {
            updated = bookDAO.updateBook(id, book);
            System.out.println("Service: Book updated → " + updated);
        } catch (Exception e) {
            throw new RuntimeException("Error updating book: " + e.getMessage());
        }
        return updated;
    }
}
