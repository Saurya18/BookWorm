package com.example.demo.controller;

import com.example.demo.repository.BookRepository;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.Map;

@RestController
@RequestMapping("/rent")
public class BookRentController {

    @Autowired
    private BookRepository bookRepository;

    @PostMapping("/book/{id}")
    public Map<String, String> rentBook(@PathVariable Integer id, @RequestBody Map<String, String> body) {
        int stock = bookRepository.findById(id).get().getStockQuantity();
        if(stock <= 0){
            return Map.of("message","Book out of stock");
        }
        bookRepository.findById(id).ifPresent(b -> {
            b.setStockQuantity(b.getStockQuantity() - 1);
            bookRepository.save(b);
        });
        return Map.of("message","Book rented successfully");
    }

    @PutMapping("/return/{id}")
    public Map<String, String> returnBook(@PathVariable Integer id) {
        bookRepository.findById(id).ifPresent(b -> {
            b.setStockQuantity(b.getStockQuantity() + 1);
            bookRepository.save(b);
        });
        return Map.of("message","Book returned successfully");
    }
}
