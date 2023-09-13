package com.github.guilhermewoelke.fts.api.domain.book.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class BookListDTO {
    @JsonPropertyOrder("1")
    private Integer id;
    @JsonPropertyOrder("2")
    @JsonProperty("titulo")
    private String title;
    @JsonProperty("autor")
    @JsonPropertyOrder("3")
    private String authorName;
}
