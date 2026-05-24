    package com.polarbookshop.catalogservice;

    import static org.assertj.core.api.Assertions.assertThat;

    import org.junit.jupiter.api.Test;
    import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.resttestclient.autoconfigure.AutoConfigureRestTestClient;
import org.springframework.boot.resttestclient.autoconfigure.AutoConfigureTestRestTemplate;
    import org.springframework.boot.test.context.SpringBootTest;
    import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.test.web.servlet.client.RestTestClient;

import com.polarbookshop.catalogservice.domain.Book;

    @SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
    @AutoConfigureRestTestClient
    class CatalogServiceApplicationTests {

        @Autowired
        private RestTestClient restTestClient;

        @Test
        void whenPostRequestThenBookCreated() {
            var expectedBook = new Book("1231231231", "Title", "Author", 9.90);

            restTestClient.post()
                .uri("/books")
                .body(expectedBook)
                .exchange()
                .expectStatus().isCreated()
                .expectBody(Book.class)
                .value(actual -> {
                    assertThat(actual).isNotNull();
                    assertThat(actual.isbn())
                        .isEqualTo(expectedBook.isbn());
                });
        }

        @Test
        void contextLoads() {

        }
    }