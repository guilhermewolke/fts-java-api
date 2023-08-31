package com.github.guilhermewoelke.fts.api.controller;

import com.github.guilhermewoelke.fts.api.dao.BookDAOInterface;
import com.github.guilhermewoelke.fts.api.dto.BookDTO;
import com.github.guilhermewoelke.fts.api.dto.BookListDTO;
import com.github.guilhermewoelke.fts.api.models.Book;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class BookControllerTest {

    @Autowired
    private TestRestTemplate testRestTemplate;

    @Autowired
    private BookDAOInterface dao;

    private Book book;

    @Test
    public void listTest() {
        ResponseEntity<BookListDTO[]> response = this.testRestTemplate.exchange(
                "/livros/",
                HttpMethod.GET,
                null,
                BookListDTO[].class
        );

        BookListDTO[] body = response.getBody();

        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    public void findByIDTest() {
        ResponseEntity<BookDTO> response = this.testRestTemplate.exchange(
                "/livros/2",
                HttpMethod.GET,
                null,
                BookDTO.class
        );

        BookDTO body = response.getBody();
        assertEquals(response.getStatusCode(), HttpStatus.OK);
        assertEquals(2, body.getId());
        assertEquals("Noites Brancas", body.getTitle());
        assertEquals(1, body.getAuthor().getId());
        assertEquals("Fiodor Dostoievski", body.getAuthor().getName());
        assertEquals("Rússia", body.getAuthor().getNationality());
    }
}
