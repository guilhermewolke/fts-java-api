package com.github.guilhermewoelke.fts.api.dto;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.github.guilhermewoelke.fts.api.models.Author;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class BookDTO {
    @JsonPropertyOrder("1")
    private Integer id;
    @JsonProperty("titulo")
    @JsonPropertyOrder("2")
    private String title;
    @JsonProperty("autor")
    @JsonPropertyOrder("3")
    private Author author;

}
