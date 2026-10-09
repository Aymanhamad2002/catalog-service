package com.polarbookshop.catalogservice;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.http.MediaType;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.jwt;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("integration")
class CatalogServiceApplicationTests {
	@Autowired
	private MockMvc mockMvc;

	@MockitoBean
	private JwtDecoder jwtDecoder;
	
	@Test
	void whenPostRequestThenBookIsCreated() throws Exception {
		mockMvc.perform(post("/books")
			.with(jwt().authorities(new SimpleGrantedAuthority("ROLE_employee")))
			.contentType(MediaType.APPLICATION_JSON)
			.content("""
				{"isbn":"1234567890","title":"Title","author":"Author","price":9.99,"publisher":"Sceptre"}
				"""))
			.andExpect(status().isCreated())
			.andExpect(jsonPath("$.isbn").value("1234567890"))
			.andExpect(jsonPath("$.title").value("Title"))
			.andExpect(jsonPath("$.author").value("Author"))
			.andExpect(jsonPath("$.price").value(9.99));
	}

	@Test
	void whenPostRequestWithoutAuthenticationThenUnauthorized() throws Exception {
		mockMvc.perform(post("/books")
			.contentType(MediaType.APPLICATION_JSON)
			.content("""
				{"isbn":"1234567891","title":"Title","author":"Author","price":9.99,"publisher":"Sceptre"}
				"""))
			.andExpect(status().isUnauthorized());
	}


	@Test
	void contextLoads() {
	}

}
