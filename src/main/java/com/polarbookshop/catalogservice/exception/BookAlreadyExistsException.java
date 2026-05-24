package com.polarbookshop.catalogservice.exception;

public class BookAlreadyExistsException extends RuntimeException {
    public BookAlreadyExistsException(String isbn){
        super("A book with isbn "  + isbn + "Already exists");
    }
    
}
