package com.github.guilhermewoelke.fts.api.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;

@Configuration
@ComponentScan("com.baeldung.jdbc")
@PropertySource("classpath:application.properties")
public class SpringJDBCConfiguration {

    @Autowired
    Environment env;

    @Bean
    public DataSource mysqlDataSource() {
        DriverManagerDataSource ds = new DriverManagerDataSource();

        ds.setDriverClassName(env.getProperty("spring.datasource.driver"));
        ds.setUrl(env.getProperty("spring.datasource.url"));
        ds.setUsername(env.getProperty("spring.datasource.username"));
        ds.setPassword(env.getProperty("spring.datasource.password"));

        return ds;
    }
}