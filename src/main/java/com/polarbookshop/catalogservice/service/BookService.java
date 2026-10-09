package com.polarbookshop.catalogservice.service;

import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.polarbookshop.catalogservice.repository.BookRepository;

import com.polarbookshop.catalogservice.domain.Book;
import com.polarbookshop.catalogservice.exception.BookAlreadyExistsException;
import com.polarbookshop.catalogservice.exception.BookNotFoundException;

/**
 * BookService
 */

@Service
public class BookService {
    private static final Logger log = LoggerFactory.getLogger(BookService.class);
    private final BookRepository bookRepository;

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }
    public Iterable<Book> viewBookList() {
        return bookRepository.findAll();
    }
    public Book viewBookDetails(String isbn) {
        return bookRepository.findByIsbn(isbn)
                .orElseThrow(() -> new BookNotFoundException(isbn));
    }
    public Book addBookToCatalog(Book book) {
        if(bookRepository.existsByIsbn(book.isbn())) {
            throw new BookAlreadyExistsException(book.isbn());
        }
        var savedBook = bookRepository.save(book);
        log.info("Added book with ISBN {} to the catalog", savedBook.isbn());
        return savedBook;
    }
    public void removeBookFromCatalog(String isbn) {
        if(!bookRepository.existsByIsbn(isbn)) {
            throw new BookNotFoundException(isbn);
        }
        bookRepository.deleteByIsbn(isbn);
        log.info("Removed book with ISBN {} from the catalog", isbn);
    }
    public Book editBookDetails(String isbn, Book book) {

       return bookRepository.findByIsbn(isbn)
                .map(existingBook -> {
                    var bookToUpdate = new Book(book.id(),book.version(),book.createdDate(),book.lastModifiedDate(),existingBook.createdBy(), existingBook.lastModifiedBy(), book.isbn(), book.title(), book.author(), book.price(), book.publisher());
                  
                    var savedBook = bookRepository.save(bookToUpdate);
                    log.info("Updated book with ISBN {} in the catalog", savedBook.isbn());
                    return savedBook;
                })
                .orElseGet(() -> addBookToCatalog(book));
    }

    
}
