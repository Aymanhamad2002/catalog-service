package com.polarbookshop.catalogservice;


import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import com.polarbookshop.catalogservice.domain.Book;

/**
 * BookValidationTests
 */
public class BookValidationTests {
    private static Validator validator;

    
    @BeforeAll
    static void setup() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
        
    }

    @Test
    void WhenAllFieldsCorrectThenValidationSucceeds() {
        var book = Book.of("   1234567890", "Title", "Author", 9.99, "Sceptre");
        var violations = validator.validate(book);
        assert(violations.isEmpty());
    }

    @Test
    void whenIsbnDefineButIncorrectThenValidationFails() {
        var book = Book.of("123", "Title", "Author", 9.99, "Sceptre");
        var violations = validator.validate(book);
        assert(!violations.isEmpty());
    }
}