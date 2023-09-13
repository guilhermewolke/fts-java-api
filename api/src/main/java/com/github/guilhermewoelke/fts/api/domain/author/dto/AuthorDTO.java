package com.github.guilhermewoelke.fts.api.domain.author.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class AuthorDTO {
    private Integer id;
    @JsonProperty("nome")

    private String name;
    @JsonProperty("pais_de_origem")
    private String nationality;
}
