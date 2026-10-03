package com.polarbookshop.catalogservice.domain;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;

import java.time.Instant;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.annotation.Version;

/**
 * Book
 */
public record Book(
    @Id
    Long id,

    @Version
    Integer version,

    @CreatedDate
    Instant createdDate,

    @LastModifiedDate
    Instant lastModifiedDate,

    @CreatedBy 
    String createdBy,

    @LastModifiedBy 
    String lastModifiedBy,

    @NotBlank(message = "ISBN must not be blank")
    @Pattern(regexp = "^(97(8|9))?\\d{9}(\\d|X)$", message = "ISBN must be a valid format")
    String isbn,

    @NotBlank(message = "Title must not be blank")
    String title,

    @NotBlank(message = "Author must not be blank")
    String author,

    @NotNull(message = "Price must not be null")
    @Positive(message = "Price must be a positive value")
    Double price,

    @NotBlank(message = "Publisher must not be blank")
    String publisher
) {
        //create constructor with publisher parameter
        public static Book of(String isbn, String title, String author, Double price, String publisher) {
            return new Book(null, null, null, null,null,null, isbn, title, author, price, publisher);
        }

}