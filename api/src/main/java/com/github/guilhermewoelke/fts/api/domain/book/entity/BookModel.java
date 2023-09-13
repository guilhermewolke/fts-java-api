package com.github.guilhermewoelke.fts.api.domain.book.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.github.guilhermewoelke.fts.api.domain.author.entity.AuthorModel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BookModel {

    private Integer id;
    @JsonIgnore
    private Integer AuthorID;
    @JsonIgnore
    private AuthorModel author;
    private String title;
}
