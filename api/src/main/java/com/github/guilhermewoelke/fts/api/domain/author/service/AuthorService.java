package com.github.guilhermewoelke.fts.api.domain.author.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.guilhermewoelke.fts.api.domain.author.dto.AuthorBooksListDTO;
import com.github.guilhermewoelke.fts.api.domain.author.entity.AuthorModel;
import com.github.guilhermewoelke.fts.api.domain.author.repository.AuthorDAOInterface;
import com.github.guilhermewoelke.fts.api.domain.book.entity.BookModel;
import com.github.guilhermewoelke.fts.api.infra.cache.CacheData;
import com.github.guilhermewoelke.fts.api.infra.cache.CacheDataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.Cache;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AuthorService {
    @Autowired
    private AuthorDAOInterface dao;

    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private CacheDataRepository cache;


//    @Cacheable(value="author.find", key="#id")
    public AuthorModel findByID(Integer id) {
        AuthorModel author = null;
        try {
            Optional<CacheData> cacheData = cache.findById("author.find."+id);

            if (cacheData.isPresent()) {
                String value = cacheData.get().getValue();
                TypeReference<AuthorModel> map = new TypeReference<AuthorModel>() {};
                author = objectMapper.readValue(value, map);
                System.out.println("Autor do cache");
                return author;
            }

            author = dao.findByID(id);
            String json = objectMapper.writeValueAsString(author);
            CacheData data = new CacheData("author.find."+id, json);
            cache.save(data);

            System.out.println("Autor do db");

        } catch (JsonProcessingException e) {
            System.out.println("Ocorreu um erro ao processar o json: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Ocorreu um erro ao recuperar/cachear o autor: " + e.getMessage());
        }
        return author;
    }

//    @Cacheable(value="author.list")
    public List<AuthorModel> list() {// throws InterruptedException, JsonProcessingException {
        List<AuthorModel> authors = new ArrayList<>();
        try {
            Optional<CacheData> cacheData = cache.findById("author.list");

            if (cacheData.isPresent()) {
                String value = cacheData.get().getValue();
                TypeReference<List<AuthorModel>> map = new TypeReference<List<AuthorModel>>() {};
                authors = objectMapper.readValue(value, map);
                System.out.println("Autores do cache");
                return authors;
            }

            authors = dao.list();
            String json = objectMapper.writeValueAsString(authors);
            CacheData data = new CacheData("author.list", json);
            cache.save(data);

            System.out.println("Autores do db");
        } catch (JsonProcessingException e) {
            System.out.println("Ocorreu um erro ao processar o json: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Ocorreu um erro ao recuperar/cachear a lista de autores: " + e.getMessage());
        }
        return authors;
    }

//    @Cacheable(value="author.books", key="#id")
    public List<BookModel> listBooksByAuthorID(Integer id) {
        List<BookModel> books = new ArrayList<>();

        try {
            Optional<CacheData> cacheData = cache.findById("author.books."+id);

            if (cacheData.isPresent()) {
                String value = cacheData.get().getValue();
                TypeReference<List<BookModel>> map = new TypeReference<List<BookModel>>() {};
                books = objectMapper.readValue(value, map);
                System.out.println("Livros do Autor do cache");
                return books;
            }

            books = dao.listBooksByAuthorID(id);
            String json = objectMapper.writeValueAsString(books);
            CacheData data = new CacheData("author.books."+id, json);
            cache.save(data);

            System.out.println("Livros do Autor do db");
        } catch (JsonProcessingException e) {
            System.out.println("Ocorreu um erro ao processar o json: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Ocorreu um erro ao recuperar/cachear a lista de livros do autor: " + e.getMessage());
        }

        return books;
    }
}
