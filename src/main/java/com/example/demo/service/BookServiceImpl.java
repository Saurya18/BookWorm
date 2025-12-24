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
        List<Book> booksList = bookDAO.getAllBooks();
        System.out.println("Service - getAllBooks feature: fetch all DB records");
        return booksList;
    }

    @Override
    public Book addBook(Book book) {
        Book savedBook = bookDAO.addBook(book);
        System.out.println("Service - addBook feature: insert new record");
        return savedBook;
    }

    @Override
    public List<Book> getBookByName(String title) {
        List<Book> book = bookDAO.getBookByName(title);
        System.out.println("Service - getBookByName feature: search by title");
        return book;
    }

    @Override
    public String deleteBook(Integer id) {
        String msg = bookDAO.deleteBook(id);
        System.out.println("Service - deleteBook feature: remove record by ID");
        return msg;
    }

    @Override
    public Book updateBook(Integer id, Book book) {
        Book updated = bookDAO.updateBook(id, book);
        System.out.println("Service - updateBook feature: modify existing record");
        return updated;
    }
}
