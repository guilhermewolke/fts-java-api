package com.github.guilhermewoelke.fts.api.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Author {
    private Integer id;
    private String name;
    private String nationality;
}
