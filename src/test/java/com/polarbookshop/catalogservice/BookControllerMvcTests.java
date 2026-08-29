package com.polarbookshop.catalogservice;

import static org.mockito.Mockito.when;

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


/**
 * BookControllerMvcTests
 */

@WebMvcTest(BookController.class)
public class BookControllerMvcTests {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private BookService  bookService;

    @Test
    public void WhenGetBookNotExistingThenReturnNotFound() throws Exception {
        var isbn = "1234567890";
        BDDMockito.given(bookService.viewBookDetails(isbn)).willThrow(new BookNotFoundException(isbn));
        mockMvc.perform(MockMvcRequestBuilders.get("/books/{isbn}", isbn))
                .andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    
}