package com.polarbookshop.catalogservice.repository;

import java.util.Map;

import org.springframework.stereotype.Repository;

import com.polarbookshop.catalogservice.domain.Book;

@Repository
public class InMemoryBookRepository  implements BookRepository {
    private static final Map<String, Book> BOOKS = new java.util.concurrent.ConcurrentHashMap<>();

    @Override
    public Iterable<com.polarbookshop.catalogservice.domain.Book> findAll() {
        return BOOKS.values();
    }

    @Override
    public java.util.Optional<com.polarbookshop.catalogservice.domain.Book> findByIsbn(String isbn) {
        return java.util.Optional.ofNullable(BOOKS.get(isbn));
    }

    @Override
    public boolean existsByIsbn(String isbn) {
        return BOOKS.containsKey(isbn);
    }

    @Override
    public com.polarbookshop.catalogservice.domain.Book save(com.polarbookshop.catalogservice.domain.Book book) {
        BOOKS.put(book.isbn(), book);
        return book;
    }

    @Override
    public void deleteByIsbn(String isbn) {
        BOOKS.remove(isbn); 
    }
    
}
