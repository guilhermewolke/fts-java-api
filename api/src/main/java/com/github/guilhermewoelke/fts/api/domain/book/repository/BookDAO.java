package com.github.guilhermewoelke.fts.api.domain.book.repository;

import com.github.guilhermewoelke.fts.api.domain.book.entity.BookMapper;
import com.github.guilhermewoelke.fts.api.domain.book.entity.BookModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.util.List;

@Component
public class BookDAO implements BookDAOInterface {

    private JdbcTemplate template;

    @Autowired
    public BookDAO(DataSource ds) {
        this.template = new JdbcTemplate(ds);
    }
    @Override
    public BookModel findByID(Integer id) {
        return template.queryForObject(
                """
                    SELECT
                        b.id,
                        b.author_id,
                        b.title,
                        a.name,
                        a.nationality
                    FROM
                        book b
                    INNER JOIN
                        author a
                    ON
                        b.author_id = a.id
                    WHERE
                        b.id = ?;
                """,
                new Object[]{id},
                new BookMapper()
        );
    }

    @Override
    public List<BookModel> list() {
        return template.query(
                """
                    SELECT
                        b.id,
                        b.author_id,
                        b.title,
                        a.name,
                        a.nationality
                    FROM
                        book b
                    INNER JOIN
                        author a
                    ON
                        a.id = b.author_id;
                    """,
                new BookMapper());
    }
}
