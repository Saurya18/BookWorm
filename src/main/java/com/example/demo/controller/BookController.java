package com.example.demo.controller;

import com.example.demo.entity.Book;
import com.example.demo.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;

@RestController
@RequestMapping("/books")
@Tag(name = "Books", description = "Book management APIs")
@SecurityRequirement(name = "BearerAuth")
public class BookController {

    @Autowired
    private BookService bookService;

    @GetMapping("/getAllBooks")
    @Operation(summary = "Get all books", description = "Retrieves all books from the library")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Successfully retrieved all books")
    })
    public ResponseEntity<Map<String, Object>> getAllBooks() {
        List<Book> books = bookService.getAllBooks();
        Map<String, Object> response = new HashMap<>();
        response.put("books", books);
        response.put("count", books.size());
        return ResponseEntity.ok(response);
    }

    @GetMapping
    @Operation(summary = "Get books with pagination", description = "Retrieves books with pagination and sorting")
    public Page<Book> getBooksPaginated(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size,
            @RequestParam(defaultValue = "title") String sortBy,
            @RequestParam(defaultValue = "asc") String direction
    ) {
        return bookService.getBooksPaginated(page, size, sortBy, direction);
    }

    @PostMapping("/addbook")
    @Operation(summary = "Add a new book", description = "Creates a new book in the library")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Book added successfully"),
        @ApiResponse(responseCode = "400", description = "Invalid input")
    })
    public ResponseEntity<Map<String, Object>> addBook(@RequestBody Book book) {
        try {
            Book savedBook = bookService.addBook(book);
            Map<String, Object> response = new HashMap<>();
            response.put("message", "Book added successfully");
            response.put("book", savedBook);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            Map<String, Object> response = new HashMap<>();
            response.put("error", e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }
    }

    @GetMapping("/search")
    @Operation(summary = "Search books", description = "Searches books by title, author, or genre")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Search completed successfully")
    })
    public ResponseEntity<Map<String, Object>> searchBooks(@RequestParam String keyword) {
        List<Book> books = bookService.searchBooks(keyword);
        Map<String, Object> response = new HashMap<>();
        response.put("books", books);
        response.put("count", books.size());
        response.put("keyword", keyword);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/update")
    @Operation(summary = "Update a book", description = "Updates an existing book")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Book updated successfully"),
        @ApiResponse(responseCode = "404", description = "Book not found")
    })
    public ResponseEntity<Map<String, Object>> updateBook(@RequestParam Integer id, @RequestBody Book book) {
        try {
            Book updatedBook = bookService.updateBook(id, book);
            Map<String, Object> response = new HashMap<>();
            response.put("message", "Book updated successfully");
            response.put("book", updatedBook);
            return ResponseEntity.ok(response);
        } catch (RuntimeException e) {
            Map<String, Object> response = new HashMap<>();
            response.put("error", e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
    }

    @DeleteMapping("/delete")
    @Operation(summary = "Delete a book", description = "Deletes a book from the library")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Book deleted successfully"),
        @ApiResponse(responseCode = "404", description = "Book not found")
    })
    public ResponseEntity<Map<String, String>> deleteBook(@RequestParam Integer id) {
        try {
            bookService.deleteBook(id);
            Map<String, String> response = new HashMap<>();
            response.put("message", "Book deleted successfully");
            return ResponseEntity.ok(response);
        } catch (RuntimeException e) {
            Map<String, String> response = new HashMap<>();
            response.put("error", e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
    }
}
