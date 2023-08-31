package com.github.guilhermewoelke.fts.api.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;

@Component
public class SetupDAO implements SetupDAOInterface {
    private JdbcTemplate template;

    @Autowired
    public SetupDAO(DataSource ds) {
        this.template = new JdbcTemplate(ds);
    }

    public void setup() {
        try {
            template.execute(
            """
                    TRUNCATE TABLE author;
                    TRUNCATE TABLE book;
                    
                    INSERT INTO author (id, name, nationality) VALUES
                    (1, "Fiodor Dostoievski", "Rússia"),
                    (2, "Liev Tolstoi", "Rússia"),
                    (3, "Homero", "Grécia"),
                    (4, "Dante Alighieri", "Itália"),
                    (5, "Machado de Assis", "Brasil"),
                    (6, "Eça de Queirós", "Portugal");
                    
                    INSERT INTO book (author_id, title) VALUES 
                    (1, "Crime e Castigo"),
                    (1, "Noites Brancas"),
                    (1, "Humilhados e Ofendidos"),
                    (2, "A Morte de Ivan Ilítch"),
                    (2, "Guerra e Paz"),
                    (2, "Anna Karenina"),
                    (3, "Ilíadas"),
                    (3, "Odisséia"),
                    (4, "A Divína Comédia"),
                    (5, "Helena"),
                    (5, "Memórias póstumas de Brás Cubas"),
                    (5, "Quincas Borba"),
                    (5, "Dom Casmurro"),
                    (5, "Relíquias de Casa velha"),
                    (6, "A Cidade e as serras"),
                    (6, "A Relíquia"),
                    (6, "O crime do padre Amaro"),
                    (1, "O primo Basílio");
                """
            );
        } catch(DataAccessException e) {
            e.printStackTrace();
        }
    }
}
