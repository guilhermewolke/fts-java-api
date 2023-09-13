package com.github.guilhermewoelke.fts.api.domain.book.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.guilhermewoelke.fts.api.domain.author.dto.AuthorDTO;
import com.github.guilhermewoelke.fts.api.domain.author.entity.AuthorModel;
import com.github.guilhermewoelke.fts.api.domain.book.dto.BookDTO;
import com.github.guilhermewoelke.fts.api.domain.book.entity.BookModel;
import com.github.guilhermewoelke.fts.api.domain.book.service.BookService;
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

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureJsonTesters
class BookControllerTest {

    @Autowired
    private MockMvc mock;
    @MockBean
    private BookService bookService;
    @Test
    @DisplayName("Livros - Listar livros")
    void list() throws Exception {
        var response = mock.perform(get("/livros")).andReturn().getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
    }

    @Test
    @DisplayName("Livros - Listar livros")
    void findByID() throws Exception {
        AuthorModel author = new AuthorModel(1, "Fiodor Dostoievsky", "Russia");
        BookModel book = new BookModel(1, 1, author, "Noites Brancas");
        when(bookService.findByID(1)).thenReturn(book);

        RequestBuilder rb = MockMvcRequestBuilders.get("/livros/1").accept(MediaType.APPLICATION_JSON);
        MvcResult result = mock.perform(rb).andReturn();

        BookDTO expected = new BookDTO(1, "Noites Brancas", author);

        ObjectMapper mapper = new ObjectMapper();

        BookDTO response = mapper.readValue(result.getResponse().getContentAsString(), BookDTO.class);

        assertThat(response.getId()).isEqualTo(expected.getId());
        assertThat(response.getAuthor().getId()).isEqualTo(expected.getAuthor().getId());
        assertThat(response.getAuthor().getName()).isEqualTo(expected.getAuthor().getName());
        assertThat(response.getAuthor().getNationality()).isEqualTo(expected.getAuthor().getNationality());
        assertThat(response.getTitle()).isEqualTo(expected.getTitle());
    }
}