package com.example.demo.repository;

import com.example.demo.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Integer> {
    Book findByTitle(String title);
    List<Book> findByTitleContainingIgnoreCaseOrAuthorContainingIgnoreCaseOrGenreContainingIgnoreCase(
            String title, String author, String genre);
}
