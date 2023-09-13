package com.github.guilhermewoelke.fts.api.domain.author.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.guilhermewoelke.fts.api.domain.author.dto.AuthorBooksListDTO;
import com.github.guilhermewoelke.fts.api.domain.author.dto.AuthorDTO;
import com.github.guilhermewoelke.fts.api.domain.author.entity.AuthorModel;
import com.github.guilhermewoelke.fts.api.domain.author.service.AuthorService;
import com.github.guilhermewoelke.fts.api.domain.book.entity.BookModel;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import java.util.Arrays;
import java.util.List;

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureJsonTesters
class AuthorControllerTest {

    @Autowired
    private MockMvc mock;

    @MockBean
    private AuthorService authorService;

    @Test
    @DisplayName("Autores - Listar autores")
    public void listar_autores() throws Exception {
        var response = mock.perform(get("/autores")).andReturn().getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
    }

    @Test
    @DisplayName("Autores - Recuperar autor por id")
    public void recuperar_autor_por_id() throws Exception {
        AuthorModel author = new AuthorModel(1, "Fiodor Dostoievsky", "Russia");
        when(authorService.findByID(1)).thenReturn(author);

        RequestBuilder rb = MockMvcRequestBuilders.get("/autores/1").accept(MediaType.APPLICATION_JSON);
        MvcResult result = mock.perform(rb).andReturn();

        AuthorDTO expected = new AuthorDTO(1, "Fiodor Dostoievsky", "Russia");

        ObjectMapper mapper = new ObjectMapper();

        AuthorDTO response = mapper.readValue(result.getResponse().getContentAsString(), AuthorDTO.class);

        assertThat(response.getId()).isEqualTo(expected.getId());
        assertThat(response.getName()).isEqualTo(expected.getName());
        assertThat(response.getNationality()).isEqualTo(expected.getNationality());
    }

    @Test
    @DisplayName("Autores - Listar Livros de um autor")
    public void listar_livros_do_autor() throws Exception {
        AuthorModel author = new AuthorModel(1, "Fiodor Dostoievsky", "Russia");
        BookModel book = new BookModel(1, 1, author, "Noites Brancas");
        List<BookModel> books = Arrays.asList(book);

        when(authorService.listBooksByAuthorID(1)).thenReturn(books);
        when(authorService.findByID(1)).thenReturn(author);

        RequestBuilder rb = MockMvcRequestBuilders.get("/autores/1/livros").accept(MediaType.APPLICATION_JSON);
        MvcResult result = mock.perform(rb).andReturn();

        AuthorBooksListDTO expected = new AuthorBooksListDTO();
        expected.setAuthor(author);
        expected.setBooks(books);

        ObjectMapper mapper = new ObjectMapper();
        AuthorBooksListDTO response = mapper.readValue(result.getResponse().getContentAsString(), AuthorBooksListDTO.class);

        assertThat(response.getAuthor().getId()).isEqualTo(expected.getAuthor().getId());
        assertThat(response.getAuthor().getName()).isEqualTo(expected.getAuthor().getName());
        assertThat(response.getAuthor().getNationality()).isEqualTo(expected.getAuthor().getNationality());
        assertThat(response.getBooks().size()).isEqualTo(expected.getBooks().size());
        assertThat(response.getBooks().get(0).getId()).isEqualTo(expected.getBooks().get(0).getId());
        assertThat(response.getBooks().get(0).getTitle()).isEqualTo(expected.getBooks().get(0).getTitle());


    }
}