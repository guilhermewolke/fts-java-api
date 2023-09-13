package com.github.guilhermewoelke.fts.api.domain.author.repository;

import com.github.guilhermewoelke.fts.api.domain.author.entity.AuthorModel;
import com.github.guilhermewoelke.fts.api.domain.book.entity.BookModel;

import java.util.List;

public interface AuthorDAOInterface {
    AuthorModel findByID(Integer id);

    List<AuthorModel> list();

    List<BookModel> listBooksByAuthorID(Integer id);
}
