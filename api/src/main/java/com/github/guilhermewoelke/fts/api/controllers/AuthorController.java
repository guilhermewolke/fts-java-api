package com.github.guilhermewoelke.fts.api.controllers;

import com.github.guilhermewoelke.fts.api.config.SpringJDBCConfiguration;
import com.github.guilhermewoelke.fts.api.dao.AuthorDAOInterface;
import com.github.guilhermewoelke.fts.api.dto.AuthorBooksListDTO;
import com.github.guilhermewoelke.fts.api.dto.AuthorDTO;
import com.github.guilhermewoelke.fts.api.dto.AuthorListDTO;
import com.github.guilhermewoelke.fts.api.models.Author;
import com.github.guilhermewoelke.fts.api.models.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/autores")
public class AuthorController {
    @Autowired
    private AuthorDAOInterface dao;

    AnnotationConfigApplicationContext context;

    @GetMapping("/")
    public ResponseEntity<List<AuthorListDTO>> list() {
        this.context = new AnnotationConfigApplicationContext(SpringJDBCConfiguration.class);
        List<AuthorListDTO> dto = new ArrayList<>();

        List<Author> authors = this.dao.list();
        authors.forEach(author -> {
            dto.add(new AuthorListDTO(author.getId(), author.getName(), author.getNationality()));
        });

        this.context.close();

        return ResponseEntity.ok().body(dto);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AuthorDTO> findByID(@PathVariable Integer id) {
        this.context = new AnnotationConfigApplicationContext(SpringJDBCConfiguration.class);
        Author author = this.dao.findByID(id);
        AuthorDTO dto = new AuthorDTO(author.getId(), author.getName(), author.getNationality());
        return ResponseEntity.ok().body(dto);
    }

    @GetMapping("/{id}/livros")
    public ResponseEntity<AuthorBooksListDTO> listBooksByAuthorID(@PathVariable Integer id) {
        this.context = new AnnotationConfigApplicationContext(SpringJDBCConfiguration.class);
        Author author = this.dao.findByID(id);
        List<Book> books = this.dao.listBooksByAuthorID(id);
        AuthorBooksListDTO dto = new AuthorBooksListDTO();

        dto.setAuthor(author);
        dto.setBooks(books);

        return ResponseEntity.ok().body(dto);
    }
}
