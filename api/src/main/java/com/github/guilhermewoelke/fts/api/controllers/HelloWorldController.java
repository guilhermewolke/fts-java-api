package com.github.guilhermewoelke.fts.api.controllers;

import com.github.guilhermewoelke.fts.api.config.SpringJDBCConfiguration;
import com.github.guilhermewoelke.fts.api.dao.SetupDAOInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/setup")
public class HelloWorldController {

    @Autowired
    private SetupDAOInterface dao;

    AnnotationConfigApplicationContext context;

    @GetMapping("/")
    public String setup() {
        this.context = new AnnotationConfigApplicationContext(SpringJDBCConfiguration.class);
        //INSERT's das tabelas


        this.context.close();

        return "Dados inseridos no banco com sucesso!";
    }
}
