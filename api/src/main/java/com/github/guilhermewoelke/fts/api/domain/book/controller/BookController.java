package com.github.guilhermewoelke.fts.api.domain.book.controller;

import com.github.guilhermewoelke.fts.api.config.SpringJDBCConfiguration;
import com.github.guilhermewoelke.fts.api.domain.book.repository.BookDAOInterface;
import com.github.guilhermewoelke.fts.api.domain.book.dto.BookDTO;
import com.github.guilhermewoelke.fts.api.domain.book.dto.BookListDTO;
import com.github.guilhermewoelke.fts.api.domain.book.entity.BookModel;
import com.github.guilhermewoelke.fts.api.domain.book.service.BookService;
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
@RequestMapping("/livros")
public class BookController {
    @Autowired
    private BookService service;

    AnnotationConfigApplicationContext context;

    @GetMapping
    public ResponseEntity<List<BookListDTO>> list() {
        this.context = new AnnotationConfigApplicationContext(SpringJDBCConfiguration.class);
        List<BookModel> books = this.service.list();
        this.context.close();

        List<BookListDTO> dto = new ArrayList<>();
        books.forEach(book -> {
            dto.add(new BookListDTO(book.getId(), book.getTitle(), book.getAuthor().getName()));
        });
        return ResponseEntity.ok().body(dto);
    }

    @GetMapping("/{id}")
    public ResponseEntity<BookDTO> findByID(@PathVariable Integer id) {
        this.context = new AnnotationConfigApplicationContext(SpringJDBCConfiguration.class);
        BookModel book = this.service.findByID(id);
        BookDTO dto = new BookDTO(book.getId(), book.getTitle(), book.getAuthor());
        this.context.close();
        return ResponseEntity.ok().body(dto);
    }
}
