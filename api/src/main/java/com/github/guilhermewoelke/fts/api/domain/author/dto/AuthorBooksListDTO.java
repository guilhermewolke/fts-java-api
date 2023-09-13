package com.github.guilhermewoelke.fts.api.domain.author.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.github.guilhermewoelke.fts.api.domain.author.entity.AuthorModel;
import com.github.guilhermewoelke.fts.api.domain.book.entity.BookModel;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class AuthorBooksListDTO {
    @JsonPropertyOrder("1")
    @JsonProperty("autor")
    private AuthorModel author;
    @JsonPropertyOrder("2")
    @JsonProperty("livros")
    private List<BookModel> books;


}
