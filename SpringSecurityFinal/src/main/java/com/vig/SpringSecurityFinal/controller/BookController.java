package com.vig.SpringSecurityFinal.controller;


import com.vig.SpringSecurityFinal.model.Book;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/books")
public class BookController {

    private final Map<Long,Book> bookDB = new HashMap<>();

    public BookController() {
        bookDB.put(1L, new Book(1L, "Java Programming", "Author A", "ISBN123", true));
        bookDB.put(2L, new Book(2L, "Spring Boot", "Author B", "ISBN456", true));
    }

    @GetMapping("/public")
    public Collection<Book> publicCatalog() {
        return bookDB.values();
    }

    @GetMapping
    @PreAuthorize("hasAnyRole('STUDENT', 'LIBRARIAN', 'ADMIN')")
    public Collection<Book> allBooks() {
        return bookDB.values();
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('STUDENT')")
    public Book getBook(@PathVariable Long id) {
        return bookDB.get(id);
    }

    @PostMapping
    @PreAuthorize("hasRole('LIBRARIAN')")
    public String addBook(@RequestBody Book book) {
        bookDB.put(book.getId(), book);
        return "Book added";
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public String deleteBook(@PathVariable Long id) {
        bookDB.remove(id);
        return "Book deleted";
    }
}

