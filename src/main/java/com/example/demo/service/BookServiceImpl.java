package com.example.demo.service.impl;

import com.example.demo.entity.Book;
import com.example.demo.dao.BookDAO;
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
            List<Book> list = bookDAO.getAllBooks();
            System.out.println("Service: All Books fetched");
            return list;
        } catch (Exception e) {
            throw new RuntimeException("Error fetching books: " + e.getMessage());
        }
    }

    @Override
    public Book addBook(Book book) {
        try {
            Book b = bookDAO.addBook(book);
            System.out.println("Service: Book added");
            return b;
        } catch (Exception e) {
            throw new RuntimeException("Error adding book: " + e.getMessage());
        }
    }

    @Override
    public List<Book> getBookByName(String title) {
        try {
            List<Book> books = bookDAO.getAllBooks().stream()
                    .filter(b -> b.getTitle().equalsIgnoreCase(title))
                    .toList();
            System.out.println("Service: Book searched by title");
            return books;
        } catch (Exception e) {
            throw new RuntimeException("Error searching book: " + e.getMessage());
        }
    }

    @Override
    public Book updateBook(Integer id, Book book) {
        try {
            Book updated = bookDAO.updateBook(id, book);
            if(updated == null) throw new RuntimeException("Book not found");
            System.out.println("Service: Book updated");
            return updated;
        } catch (Exception e) {
            throw new RuntimeException("Error updating book: " + e.getMessage());
        }
    }

    @Override
    public String deleteBook(Integer id) {
        try {
            bookDAO.deleteBook(id);
            return "Book deleted from service layer";
        } catch (Exception e) {
            return "Error deleting book: " + e.getMessage();
        }
    }
}
