package com.vig.SpringSecurityFinal.repository;


import com.vig.SpringSecurityFinal.model.Book;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class BookRepository {

    private final Map<Long, Book> books = new HashMap<>();

    public BookRepository() {
        books.put(1L, new Book(1L, "Clean Code", "Robert C. Martin", "9780132350884", true));
        books.put(2L, new Book(2L, "Spring in Action", "Craig Walls", "9781617294945", true));
    }

    public List<Book> findAll() {
        return new ArrayList<>(books.values());
    }

    public Optional<Book> findById(Long id) {
        return Optional.ofNullable(books.get(id));
    }

    public void save(Book book) {
        books.put(book.getId(), book);
    }

    public void deleteById(Long id) {
        books.remove(id);
    }}
