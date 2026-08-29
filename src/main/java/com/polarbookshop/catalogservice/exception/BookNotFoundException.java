package com.polarbookshop.catalogservice.exception;

/**
 * BookNotFoundException
 */
public class BookNotFoundException extends RuntimeException {
    public BookNotFoundException(String isbn) {
        super("Book with ISBN " + isbn + " not found");
    }

    
}