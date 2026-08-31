package com.polarbookshop.catalogservice;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webtestclient.autoconfigure.AutoConfigureWebTestClient;
import org.springframework.test.web.reactive.server.WebTestClient;
import com.polarbookshop.catalogservice.domain.Book;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureWebTestClient
class CatalogServiceApplicationTests {
	@Autowired

	
	private WebTestClient webTestClient;
	
	void whenPostRequestThenBookIsCreated() {
		var book = Book.of("1234567890", "Title", "Author", 9.99, "Sceptre");
		webTestClient.post().uri("/books")
			.bodyValue(book)
			.exchange()
			.expectStatus().isCreated()
			.expectBody()
			.jsonPath("$.isbn").isEqualTo(book.isbn())
			.jsonPath("$.title").isEqualTo(book.title())
			.jsonPath("$.author").isEqualTo(book.author())
			.jsonPath("$.price").isEqualTo(book.price());
	}


	@Test
	void contextLoads() {
	}

}
