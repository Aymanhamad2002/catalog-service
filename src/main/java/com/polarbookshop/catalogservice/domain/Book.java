package com.polarbookshop.catalogservice.domain;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;

/**
 * Book
 */
public record Book(
    @NotBlank(message = "ISBN must not be blank")
    @Pattern(regexp = "^(97(8|9))?\\d{9}(\\d|X)$", message = "ISBN must be a valid format")
    String isbn,
    @NotBlank(message = "Title must not be blank")
    String title,
    @NotBlank(message = "Author must not be blank")
    String author,
    @NotNull(message = "Price must not be null")
    @Positive(message = "Price must be a positive value")
    Double price) {
        //create constructor with publisher parameter
        public Book(String isbn, String title, String author, Double price, String publisher) {
            this(isbn, title, author, price);
        }

}