package com.example.demo.service.impl;

import com.example.demo.dao.BookDAO;
import com.example.demo.entity.Book;
import com.example.demo.repository.BookRepository;
import com.example.demo.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class BookServiceImpl implements BookService {

    @Autowired
    private BookDAO bookDAO;

    @Autowired
    private BookRepository bookRepository; // ✅ repository ko service me access dene ke liye inject kiya

    @Override
    public List<Book> getAllBooks() {
        List<Book> books = null;
        try {
            books = bookDAO.getAllBooks();
            System.out.println("Service: All books fetched successfully");
        } catch (Exception e) {
            throw new RuntimeException("Service error while fetching books: " + e.getMessage());
        }
        return books;
    }

    @Override
    public Book addBook(Book book) {
        Book saved = null;
        try {
            saved = bookDAO.addBook(book);
            System.out.println("Service: Book saved → " + saved);
        } catch (Exception e) {
            throw new RuntimeException("Service error while saving book: " + e.getMessage());
        }
        return saved;
    }

    @Override
    public List<Book> getBookByName(String title) {
        List<Book> books = null;
        try {
            books = bookRepository.findByTitle(title); // ✅ repository method use kiya
            System.out.println("Service: Books found → " + books);
        } catch (Exception e) {
            throw new RuntimeException("Service error while finding book: " + e.getMessage());
        }
        return books;
    }

    @Override
    public String deleteBook(Integer id) {
        try {
            bookDAO.deleteBook(id);
            System.out.println("Service: Book deleted → ID " + id);
        } catch (Exception e) {
            throw new RuntimeException("Service error while deleting book: " + e.getMessage());
        }
        return "Book deleted successfully";
    }

    @Override
    public Book updateBook(Integer id, Book newData) {
        Book updated = null;
        try {
            updated = bookDAO.updateBook(id, newData);
            System.out.println("Service: Book updated → " + updated);
        } catch (Exception e) {
            throw new RuntimeException("Service error while updating book: " + e.getMessage());
        }
        return updated;
    }
}
