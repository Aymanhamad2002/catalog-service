package com.polarbookshop.catalogservice.demo;

import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.Profile;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import com.polarbookshop.catalogservice.domain.Book;
import com.polarbookshop.catalogservice.repository.BookRepository;

@Component
@Profile("testdata")
public class BookLoader {
    private static final Logger log = LoggerFactory.getLogger(BookLoader.class);
    private final BookRepository bookRepository;;
    public BookLoader(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @EventListener(ApplicationReadyEvent.class)
    public void loadBooks() {
        log.info("Replacing catalog contents with demo books for the testdata profile");
        bookRepository.deleteAll();
        var book1 = Book.of("1234567890", "Northern Lights", "Philip Pullman", 9.90, "Sceptre");
        var book2 = Book.of("1234567891", "Polar Journey", "Philip Pullman", 12.90, "Sceptre");
        var book3 = Book.of("1234567892", "The Amber Spyglass", "Philip Pullman", 8.90, "Sceptre");

        bookRepository.saveAll(List.of(book1,book2,book3));
        log.info("Loaded 3 demo books into the catalog");
    }        
    
    
}
