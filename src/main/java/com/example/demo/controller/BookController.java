package com.example.demo.controller;

import com.example.demo.entity.Book;
import com.example.demo.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/books")
public class BookController {

    @Autowired
    private BookRepository bookRepository;

    // 1️⃣ GET ALL BOOKS
    @GetMapping("/getAllBooks")
    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    // 2️⃣ ADD BOOK
    @PostMapping("/addBook")
    public Book addBook(@RequestBody Book book) {
        return bookRepository.save(book);
    }

    // 3️⃣ GET BOOK BY NAME
    @GetMapping("/getBookByName/{title}")
    public List<Book> getBookByName(@PathVariable String title) {
        return bookRepository.findByTitle(title);
    }

    // 4️⃣ DELETE BOOK
    @DeleteMapping("/deleteBook/{id}")
    public String deleteBook(@PathVariable Integer id) {
        bookRepository.deleteById(id);
        return "Book deleted successfully";
    }
    // 5️⃣ UPDATE BOOK
    @PutMapping("/updateBook/{id}")
    public Book updateBook(@PathVariable Integer id, @RequestBody Book newBookData) {

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
