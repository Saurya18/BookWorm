package com.example.demo.dao.impl;

import com.example.demo.dao.BookDAO;
import com.example.demo.entity.Book;
import com.example.demo.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class BookDAOImpl implements BookDAO {

    @Autowired
    private BookRepository bookRepository;

    @Override
    public List<Book> getAllBooks() {
        try {
            List<Book> books = bookRepository.findAll();
            System.out.println("DAO - All Books: " + books);
            return books;
        } catch (Exception e) {
            throw new RuntimeException("DAO Error fetching books: " + e.getMessage());
        }
    }

    @Override
    public Book addBook(Book book) {
        try {
            Book saved = bookRepository.save(book);
            System.out.println("DAO - Book Saved: " + saved);
            return saved;
        } catch (Exception e) {
            throw new RuntimeException("DAO Error saving book: " + e.getMessage());
        }
    }

    @Override
    public List<Book> getBookByName(String title) {
        try {
            List<Book> book = bookRepository.findByTitle(title);
            System.out.println("DAO - Book Found: " + book);
            return book;
        } catch (Exception e) {
            throw new RuntimeException("DAO Error finding book: " + e.getMessage());
        }
    }

    @Override
    public String deleteBook(Integer id) {
        try {
            bookRepository.deleteById(id);
            System.out.println("DAO - Book Deleted ID: " + id);
            return "Book deleted from DAO";
        } catch (Exception e) {
            throw new RuntimeException("DAO Error deleting book: " + e.getMessage());
        }
    }

    @Override
    public Book updateBook(Integer id, Book book) {
        try {
            Book existing = bookRepository.findById(id)
                    .orElseThrow(() -> new RuntimeException("Book not found in DAO"));

            existing.setTitle(book.getTitle());
            existing.setAuthor(book.getAuthor());
            existing.setGenre(book.getGenre());
            existing.setIsbn(book.getIsbn());
            existing.setPublisher(book.getPublisher());
            existing.setPublicationYear(book.getPublicationYear());
            existing.setPrice(book.getPrice());
            existing.setStockQuantity(book.getStockQuantity());

            Book updated = bookRepository.save(existing);
            System.out.println("DAO - Book Updated: " + updated);
            return updated;
        } catch (Exception e) {
            throw new RuntimeException("DAO Error updating book: " + e.getMessage());
        }
    }
}
