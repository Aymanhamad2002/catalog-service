package com.polarbookshop.catalogservice.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.polarbookshop.catalogservice.domain.Book;
import com.polarbookshop.catalogservice.service.BookService;

import jakarta.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;



@RestController
@RequestMapping("books")
public class BookController {
    private static final Logger log = LoggerFactory.getLogger(BookController.class);
    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }
    
    @GetMapping
    public Iterable<Book> viewBookList() {
        log.info("Fetching the list of books in the catalog");
        return bookService.viewBookList();
    }

    @GetMapping("{isbn}")
    public Book getByIsbn(@PathVariable("isbn")  String isbn) {

        log.info("Fetching book with ISBN {}", isbn);
        return bookService.viewBookDetails(isbn);

    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Book postBook(@Valid @RequestBody Book book) {
        log.info("Received request to add book with ISBN {}", book.isbn());
        return bookService.addBookToCatalog(book);
    }

    @DeleteMapping("{isbn}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteBook(@PathVariable String isbn) {
        log.info("Received request to delete book with ISBN {}", isbn);
        bookService.removeBookFromCatalog(isbn);
    }
    
    public Book putBook(@PathVariable String isbn, @Valid @RequestBody Book book) {
        log.info("Received request to update book with ISBN {}", isbn);
        return bookService.editBookDetails(isbn, book); 

    
    }

}
    
    
    
    
