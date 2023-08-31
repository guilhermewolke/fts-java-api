package com.github.guilhermewoelke.fts.api.dao;

import com.github.guilhermewoelke.fts.api.models.Author;
import com.github.guilhermewoelke.fts.api.models.Book;

import java.util.List;

public interface AuthorDAOInterface {
    Author findByID(Integer id);

    List<Author> list();

    List<Book> listBooksByAuthorID(Integer id);
}
