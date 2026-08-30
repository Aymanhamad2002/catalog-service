package com.polarbookshop.catalogservice.demo;

import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.Profile;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import com.polarbookshop.catalogservice.domain.Book;
import com.polarbookshop.catalogservice.repository.BookRepository;

@Component
@Profile("testdata")
public class BookLoader {
    private final BookRepository bookRepository;;
    public BookLoader(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @EventListener(ApplicationReadyEvent.class)
    public void loadBooks() {
        var book1 = new Book("1234567890", "Northern Lights", "Philip Pullman", 9.90, "Sceptre");
        var book2 = new Book("1234567891", "Polar Journey", "Philip Pullman", 12.90, "Sceptre");
        var book3 = new Book("1234567892", "The Amber Spyglass", "Philip Pullman", 8.90, "Sceptre");
        
        bookRepository.save(book1);
        bookRepository.save(book2);
        bookRepository.save(book3);
    }        
    
    
}
