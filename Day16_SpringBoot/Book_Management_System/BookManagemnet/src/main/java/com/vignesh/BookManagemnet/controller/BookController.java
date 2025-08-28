package com.vignesh.BookManagemnet.controller;

import com.vignesh.BookManagemnet.model.Book;
import com.vignesh.BookManagemnet.service.BookService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/books")
public class BookController {

    private final BookService service;

    public BookController(BookService service) {
        this.service = service;
    }

    @PostMapping
    public Book addBook(@RequestBody Book book) {
        return service.addBook(book);
    }

    @GetMapping
    public List<Book> getAllBooks() {
        return service.getAllBooks();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Book> getBookById(@PathVariable int id) {
        return service.getBookById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Book> updateBook(@PathVariable int id, @RequestBody Book book) {
        return service.updateBook(id, book)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBook(@PathVariable int id) {
        return service.deleteBook(id)
                ? ResponseEntity.noContent().build()
                : ResponseEntity.notFound().build();
    }

    @GetMapping("/search")
    public List<Book> getBooksByAuthor(@RequestParam String author) {
        return service.getBooksByAuthor(author);
    }

    @GetMapping("/filter")
    public List<Book> getBooksCheaperThan(@RequestParam double price) {
        return service.getBooksCheaperThan(price);
    }

    @GetMapping("/total")
    public int getTotalBooks() {
        return service.getTotalBooks();
    }

    @GetMapping("/most-expensive")
    public ResponseEntity<Book> getMostExpensiveBook() {
        return service.getMostExpensiveBook()
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.noContent().build());
    }
}
