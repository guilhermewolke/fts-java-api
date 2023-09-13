package com.github.guilhermewoelke.fts.api.domain.book.entity;

import com.github.guilhermewoelke.fts.api.domain.author.entity.AuthorModel;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class BookMapper implements RowMapper<BookModel> {
    @Override
    public BookModel mapRow(ResultSet rs, int rowNum) throws SQLException {
        BookModel b = new BookModel();

        b.setId(rs.getInt("id"));
        b.setAuthorID(rs.getInt("author_id"));
        b.setTitle(rs.getString("title"));

        AuthorModel a = new AuthorModel(rs.getInt("author_id"), rs.getString("name"),  rs.getString("nationality"));
        b.setAuthor(a);

        return b;
    }
}
