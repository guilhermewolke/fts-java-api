package com.github.guilhermewoelke.fts.api.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.github.guilhermewoelke.fts.api.models.Author;
import com.github.guilhermewoelke.fts.api.models.Book;
import lombok.Getter;
import lombok.Setter;

import javax.management.ConstructorParameters;
import java.util.List;

@Getter
@Setter
public class AuthorBooksListDTO {
    @JsonPropertyOrder("1")
    @JsonProperty("autor")
    private Author author;
    @JsonPropertyOrder("2")
    @JsonProperty("livros")
    private List<Book> books;


}
