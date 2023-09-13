package com.github.guilhermewoelke.fts.api.domain.author.entity;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class AuthorMapper implements RowMapper<AuthorModel> {
    @Override
    public AuthorModel mapRow(ResultSet rs, int rowNum) throws SQLException {
        AuthorModel a = new AuthorModel();

        a.setId(rs.getInt("id"));
        a.setName(rs.getString("name"));
        a.setNationality(rs.getString("nationality"));

        return a;
    }
}
