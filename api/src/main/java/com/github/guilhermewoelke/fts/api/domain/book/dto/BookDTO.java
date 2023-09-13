package com.github.guilhermewoelke.fts.api.domain.book.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.github.guilhermewoelke.fts.api.domain.author.entity.AuthorModel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class BookDTO {
    @JsonPropertyOrder("1")
    private Integer id;
    @JsonProperty("titulo")
    @JsonPropertyOrder("2")
    private String title;
    @JsonProperty("autor")
    @JsonPropertyOrder("3")
    private AuthorModel author;

}
