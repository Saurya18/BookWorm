package com.example.demo.service.impl;

import com.example.demo.entity.Book;
import com.example.demo.repository.BookRepository;
import com.example.demo.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookServiceImpl implements BookService {

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
    public String deleteBook(Integer id) {
        bookRepository.deleteById(id);
        return "Book deleted successfully";
    }

    @Override
    public Book updateBook(Integer id, Book newBookData) {
        Book existingBook = bookRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Book not found"));

        existingBook.setTitle(newBookData.getTitle());
        existingBook.setAuthor(newBookData.getAuthor());
        existingBook.setGenre(newBookData.getGenre());
        existingBook.setIsbn(newBookData.getIsbn());
        existingBook.setPublisher(newBookData.getPublisher());
        existingBook.setPublicationYear(newBookData.getPublicationYear());
        existingBook.setPrice(newBookData.getPrice());
        existingBook.setStockQuantity(newBookData.getStockQuantity());

        return bookRepository.save(existingBook);
    }
}
