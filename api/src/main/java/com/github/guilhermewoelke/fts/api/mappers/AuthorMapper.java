package com.github.guilhermewoelke.fts.api.mappers;

import com.github.guilhermewoelke.fts.api.models.Author;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class AuthorMapper implements RowMapper<Author> {
    @Override
    public Author mapRow(ResultSet rs, int rowNum) throws SQLException {
        Author a = new Author();

        a.setId(rs.getInt("id"));
        a.setName(rs.getString("name"));
        a.setNationality(rs.getString("nationality"));

        return a;
    }
}
