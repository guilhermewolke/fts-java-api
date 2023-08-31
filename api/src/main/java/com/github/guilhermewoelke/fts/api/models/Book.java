package com.github.guilhermewoelke.fts.api.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Book {

    private Integer id;
    @JsonIgnore
    private Integer AuthorID;
    @JsonIgnore
    private Author author;
    private String title;
}
