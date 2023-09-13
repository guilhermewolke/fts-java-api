package com.github.guilhermewoelke.fts.api.domain.book.repository;

import com.github.guilhermewoelke.fts.api.domain.book.entity.BookModel;

import java.util.List;

public interface BookDAOInterface {

    BookModel findByID(Integer id);
    List<BookModel> list();

}
