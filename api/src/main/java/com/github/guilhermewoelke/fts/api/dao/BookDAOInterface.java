package com.github.guilhermewoelke.fts.api.dao;

import com.github.guilhermewoelke.fts.api.models.Book;

import java.util.List;

public interface BookDAOInterface {

    Book findByID(Integer id);
    List<Book> list();

}
