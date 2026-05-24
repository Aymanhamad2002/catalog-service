package com.polarbookshop.catalogservice;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.json.JsonTest;
import org.springframework.boot.test.json.JacksonTester;

import com.polarbookshop.catalogservice.domain.Book;

@JsonTest
public class BookJsonTests {
    private JacksonTester<Book> json;

    @Test
    void testSerialize() throws Exception{
        var book = new Book("1234567890","Title","Author",9.0);
        var jsonContent = json.write(book);
        assertThat(jsonContent).extractingJsonPathStringValue("@.isbn")
        .isEqualTo(book.isbn());
        assertThat(jsonContent).extractingJsonPathStringValue("@.title")
        .isEqualTo(book.title());
        assertThat(jsonContent).extractingJsonPathStringValue("@.author")
        .isEqualTo(book.author());
        assertThat(jsonContent).extractingJsonPathNumberValue("@.price")
        .isEqualTo(book.price());

    }
    @Test
    void testDeserialize() throws Exception{
        var content = """
        {
            "isbn" : "1234567890",
            "title": "Title",
            "author" : "Author",
            "price" : 9.0
        }
                """;
        assertThat(json.parse(content)).usingRecursiveComparison().isEqualTo(new Book("1234567890","Title","Author",9.0));
    }
    
}
