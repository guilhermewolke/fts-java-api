package com.github.guilhermewoelke.fts.api.domain.author.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AuthorModel {
    private Integer id;
    private String name;
    private String nationality;
}
