package com.example.demo.controller;

import com.example.demo.entity.Book;
import com.example.demo.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/books")
public class BookController {

    @Autowired
    private BookService bookService;

    // 1️⃣ GET ALL BOOKS
    @GetMapping("/getAllBooks")
    public List<Book> getAllBooks() {
        List<Book> booksList = bookService.getAllBooks();
        System.out.println("All Books: " + booksList);
        return booksList;
    }

    // 2️⃣ ADD BOOK
    @PostMapping("/addBook")
    public Book addBook(@RequestBody Book book) {
        Book savedBook = bookService.addBook(book);
        System.out.println("Book Added: " + savedBook);
        return savedBook;
    }

    // 3️⃣ GET BOOK BY NAME (SINGLE BOOK RETURN)
    @GetMapping("/getBookByName/{title}")
    public List<Book> getBookByName(@PathVariable String title) {
        List<Book> book = bookService.getBookByName(title);
        System.out.println(book);
        return book;
    }






    // 4️⃣ DELETE BOOK
    @DeleteMapping("/deleteBook/{id}")
    public String deleteBook(@PathVariable Integer id) {
        bookService.deleteBook(id);
        System.out.println("Deleted Book ID: " + id);
        return "Book deleted successfully";
    }

    // 5️⃣ UPDATE BOOK
    @PutMapping("/updateBook/{id}")
    public Book updateBook(@PathVariable Integer id, @RequestBody Book book) {

        // update result ko variable me store kiya
        Book updatedBook = bookService.updateBook(id, book);

        // print kiya taaki update track ho sake
        System.out.println("Book updated for ID " + id + " : " + updatedBook);

        return updatedBook;
    }

}
