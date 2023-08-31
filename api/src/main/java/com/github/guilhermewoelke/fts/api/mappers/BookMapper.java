package com.github.guilhermewoelke.fts.api.mappers;

import com.github.guilhermewoelke.fts.api.models.Author;
import com.github.guilhermewoelke.fts.api.models.Book;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class BookMapper implements RowMapper<Book> {
    @Override
    public Book mapRow(ResultSet rs, int rowNum) throws SQLException {
        Book b = new Book();

        b.setId(rs.getInt("id"));
        b.setAuthorID(rs.getInt("author_id"));
        b.setTitle(rs.getString("title"));

        Author a = new Author(rs.getInt("author_id"), rs.getString("name"),  rs.getString("nationality"));
        b.setAuthor(a);

        return b;
    }
}
