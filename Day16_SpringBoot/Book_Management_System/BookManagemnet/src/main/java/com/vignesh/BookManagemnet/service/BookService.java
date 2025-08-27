package com.vignesh.BookManagemnet.service;

import com.vignesh.BookManagemnet.model.Book;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

@Service
public class BookService {
    private static final List<Book> books = new ArrayList<>();
    private static int nextId = 1;

    public Book addBook(Book book) {
        book.setId(nextId++);
        books.add(book);
        return book;
    }

    public List<Book> getAllBooks() {
        return books;
    }

    public Optional<Book> getBookById(int id) {
        return books.stream().filter(b -> b.getId() == id).findFirst();
    }

    public Optional<Book> updateBook(int id, Book updatedBook) {
        return getBookById(id).map(existing -> {
            existing.setTitle(updatedBook.getTitle());
            existing.setAuthor(updatedBook.getAuthor());
            existing.setPrice(updatedBook.getPrice());
            return existing;
        });
    }

    public boolean deleteBook(int id) {
        return books.removeIf(book -> book.getId() == id);
    }

    public List<Book> getBooksByAuthor(String author) {
        return books.stream()
                .filter(b -> b.getAuthor().equalsIgnoreCase(author))
                .collect(Collectors.toList());
    }

    public List<Book> getBooksCheaperThan(double price) {
        return books.stream()
                .filter(b -> b.getPrice() < price)
                .collect(Collectors.toList());
    }

    public int getTotalBooks() {
        return books.size();
    }

    public Optional<Book> getMostExpensiveBook() {
        return books.stream()
                .max(Comparator.comparingDouble(Book::getPrice));
    }
}
