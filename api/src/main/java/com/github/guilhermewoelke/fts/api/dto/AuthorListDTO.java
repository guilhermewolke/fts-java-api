package com.github.guilhermewoelke.fts.api.dto;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class AuthorListDTO {
    private Integer id;
    @JsonProperty("nome")
    private String name;
    @JsonProperty("pais_de_origem")
    private String nationality;
}
