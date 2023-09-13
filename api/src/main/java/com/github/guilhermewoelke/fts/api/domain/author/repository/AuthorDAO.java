package com.github.guilhermewoelke.fts.api.domain.author.repository;

import com.github.guilhermewoelke.fts.api.domain.author.entity.AuthorMapper;
import com.github.guilhermewoelke.fts.api.domain.author.entity.AuthorModel;
import com.github.guilhermewoelke.fts.api.domain.book.entity.BookMapper;
import com.github.guilhermewoelke.fts.api.domain.book.entity.BookModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.util.List;

@Component
public class AuthorDAO implements AuthorDAOInterface {

    private JdbcTemplate template;

    @Autowired
    public AuthorDAO(DataSource ds) {
        this.template = new JdbcTemplate(ds);
    }

    @Override
    public AuthorModel findByID(Integer id) {
        return template.queryForObject(
            "SELECT id, name, nationality FROM author WHERE id = ?",
                new Object[] {id},
                new AuthorMapper());
    }

    @Override
    public List<AuthorModel> list() {
        return template.query("SELECT id, name, nationality FROM author", new AuthorMapper());
    }

    @Override
    public List<BookModel> listBooksByAuthorID(Integer id) {
        return template.query(
            """
                SELECT
                    b.id,
                    b.title,
                    b.author_id,
                    a.name,
                    a.nationality
                FROM
                    author a
                INNER JOIN
                    book b
                ON
                    a.id = b.author_id
                WHERE
                    b.author_id = ?
                """,
                new Object[] {id},
                new BookMapper());
    }
}
