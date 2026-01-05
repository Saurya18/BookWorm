package com.example.demo.dao.impl;

import com.example.demo.dao.BookDAO;
import com.example.demo.entity.Book;
import com.example.demo.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public class BookDAOImpl implements BookDAO {

    @Autowired
    private BookRepository bookRepository;

    @Override
    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    @Override
    public Book addBook(Book book) {
        return bookRepository.save(book);
    }

    @Override
    public List<Book> getBookByName(String title) {
        return bookRepository.findByTitle(title);
    }

    @Override
    public void deleteBook(Integer id) {
        bookRepository.deleteById(id);
    }

    @Override
    public Book updateBook(Integer id, Book book) {
        Book existing = bookRepository.findById(id).orElse(null);
        if (existing == null) return null;

        existing.setTitle(book.getTitle());
        existing.setAuthor(book.getAuthor());
        existing.setGenre(book.getGenre());
        existing.setIsbn(book.getIsbn());

        return bookRepository.save(existing);
    }
}
