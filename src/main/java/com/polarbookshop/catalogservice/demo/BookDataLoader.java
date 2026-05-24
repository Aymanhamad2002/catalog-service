package com.polarbookshop.catalogservice.demo;

import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.Profile;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import com.polarbookshop.catalogservice.domain.Book;
import com.polarbookshop.catalogservice.repository.BookRepository;

@Component
@Profile("testdata")
public class BookDataLoader {
    private final BookRepository bookRepository;
    public BookDataLoader(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }
    @EventListener(ApplicationReadyEvent.class)
    public void loadBookTestData(){
        var book1= new Book("1234567891","Northern Lights","lyra Silverstar",9.90);
        var book2 =new Book("1234567892","Northern "," Silverstar",9.90);
        bookRepository.save(book2);
        bookRepository.save(book1);
    }
    
}
