package com.example.demo.controller;

import com.example.demo.repository.BookRepository;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.HashMap;
import java.util.Map;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;

@RestController
@RequestMapping("/rent")
@Tag(name = "Book Rental", description = "Book rental and return APIs")
@SecurityRequirement(name = "BearerAuth")
public class BookRentController {

    @Autowired
    private BookRepository bookRepository;

    @PostMapping("/book/{id}")
    @Operation(summary = "Rent a book", description = "Rents a book by decreasing stock quantity")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Book rented successfully"),
        @ApiResponse(responseCode = "400", description = "Book out of stock"),
        @ApiResponse(responseCode = "404", description = "Book not found")
    })
    public ResponseEntity<Map<String, String>> rentBook(@PathVariable Integer id, @RequestBody Map<String, String> body) {
        return bookRepository.findById(id).map(book -> {
            if (book.getStockQuantity() <= 0) {
                Map<String, String> response = new HashMap<>();
                response.put("message", "Book out of stock");
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
            }
            book.setStockQuantity(book.getStockQuantity() - 1);
            bookRepository.save(book);
            Map<String, String> response = new HashMap<>();
            response.put("message", "Book rented successfully");
            return ResponseEntity.ok(response);
        }).orElseGet(() -> {
            Map<String, String> response = new HashMap<>();
            response.put("error", "Book not found");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        });
    }

    @PutMapping("/return/{id}")
    @Operation(summary = "Return a book", description = "Returns a book by increasing stock quantity")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Book returned successfully"),
        @ApiResponse(responseCode = "404", description = "Book not found")
    })
    public ResponseEntity<Map<String, String>> returnBook(@PathVariable Integer id) {
        return bookRepository.findById(id).map(book -> {
            book.setStockQuantity(book.getStockQuantity() + 1);
            bookRepository.save(book);
            Map<String, String> response = new HashMap<>();
            response.put("message", "Book returned successfully");
            return ResponseEntity.ok(response);
        }).orElseGet(() -> {
            Map<String, String> response = new HashMap<>();
            response.put("error", "Book not found");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        });
    }
}
