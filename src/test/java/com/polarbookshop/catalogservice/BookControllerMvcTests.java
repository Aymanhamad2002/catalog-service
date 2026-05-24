package com.polarbookshop.catalogservice;

import org.junit.jupiter.api.Test;
import org.mockito.BDDMockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.polarbookshop.catalogservice.controller.BookController;
import com.polarbookshop.catalogservice.exception.BookNotFoundException;
import com.polarbookshop.catalogservice.service.BookService;


@WebMvcTest(BookController.class)
public class BookControllerMvcTests {
    @Autowired
    private MockMvc mockMvc;
    @MockitoBean
    private BookService bookService;

    @Test
    void whenGetBookNotExistingThenShouldReturn404() throws Exception{
        String isbn = "73737313940";
        BDDMockito.given(bookService.viewBookDetails(isbn)).willThrow(BookNotFoundException.class);
        mockMvc.perform(MockMvcRequestBuilders.get("/books/" + isbn)).andExpect(MockMvcResultMatchers. status().isNotFound());
    }
    
}
