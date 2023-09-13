package com.github.guilhermewoelke.fts.api.controller;

import com.github.guilhermewoelke.fts.api.domain.author.repository.AuthorDAOInterface;
import com.github.guilhermewoelke.fts.api.domain.author.dto.AuthorBooksListDTO;
import com.github.guilhermewoelke.fts.api.domain.author.dto.AuthorDTO;
import com.github.guilhermewoelke.fts.api.domain.author.dto.AuthorListDTO;
import com.github.guilhermewoelke.fts.api.domain.author.entity.AuthorModel;
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
public class AuthorControllerTest {

    @Autowired
    private TestRestTemplate testRestTemplate;

    @Autowired
    private AuthorDAOInterface dao;

    private AuthorModel author;

    @Test
    public void listTest() {
        ResponseEntity<AuthorListDTO[]> response = this.testRestTemplate.exchange(
                "/autores/",
                HttpMethod.GET,
    null,
                AuthorListDTO[].class
        );

        AuthorListDTO[] body = response.getBody();

        assertEquals(HttpStatus.OK, response.getStatusCode()) ;
        assertEquals(1, body[0].getId());
        assertEquals("Fiodor Dostoievski", body[0].getName());
        assertEquals("Rússia", body[0].getNationality());
    }

    @Test
    public void findByIDTest() {
        ResponseEntity<AuthorDTO> response = this.testRestTemplate.exchange(
                "/autores/2",
                HttpMethod.GET,
                null,
                AuthorDTO.class
        );

        assertEquals(response.getStatusCode(), HttpStatus.OK);
        AuthorDTO body = response.getBody();
        assertEquals(2, body.getId());
        assertEquals("Liev Tolstoi", body.getName());
        assertEquals("Rússia", body.getNationality());
    }

    @Test
    public void listBooksByAuthorIDTest() {
        ResponseEntity<AuthorBooksListDTO> response = this.testRestTemplate.exchange(
                "/autores/3/livros",
                HttpMethod.GET,
                null,
                AuthorBooksListDTO.class
        );

        assertEquals(response.getStatusCode(), HttpStatus.OK);
        AuthorBooksListDTO body = response.getBody();
        assertEquals(3, body.getAuthor().getId());
        assertEquals("Homero", body.getAuthor().getName());
        assertEquals("Grécia", body.getAuthor().getNationality());
        assertEquals(2, body.getBooks().size());
    }
}
