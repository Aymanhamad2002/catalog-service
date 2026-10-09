package com.polarbookshop.catalogservice;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.JsonTest;
import org.springframework.boot.test.json.JacksonTester;

import com.polarbookshop.catalogservice.domain.Book;

@JsonTest
public class BookJsonTests {

    @Autowired
    private JacksonTester<Book> json;

    @Test
    public void testSerialization() throws Exception {
        var book = Book.of(
            "1234567890",
            "Title",
            "Author",
            9.99,
            "Sceptre"
        );

        var jsonContent = json.write(book);

        Assertions.assertThat(jsonContent)
            .extractingJsonPathStringValue("@.isbn")
            .isEqualTo(book.isbn());

        Assertions.assertThat(jsonContent)
            .extractingJsonPathStringValue("@.title")
            .isEqualTo(book.title());

        Assertions.assertThat(jsonContent)
            .extractingJsonPathStringValue("@.author")
            .isEqualTo(book.author());

        Assertions.assertThat(jsonContent)
            .extractingJsonPathNumberValue("@.price")
            .isEqualTo(book.price());

        Assertions.assertThat(jsonContent)
            .extractingJsonPathStringValue("@.publisher")
            .isEqualTo(book.publisher());
    }

    @Test
    public void testDeserialization() throws Exception {
        var content = """
            {
                "id": null,
                "version": null,
                "createdDate": null,
                "lastModifiedDate": null,
                "createdBy": null,
                "lastModifiedBy": null,
                "isbn": "1234567890",
                "title": "Title",
                "author": "Author",
                "price": 9.99,
                "publisher": "Sceptre"
            }
        """;

        Assertions.assertThat(json.parse(content))
            .usingRecursiveComparison()
            .isEqualTo(
                Book.of(
                    "1234567890",
                    "Title",
                    "Author",
                    9.99,
                    "Sceptre"
                )
            );
    }
}
