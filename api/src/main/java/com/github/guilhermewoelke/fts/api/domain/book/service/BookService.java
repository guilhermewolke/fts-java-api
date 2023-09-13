package com.github.guilhermewoelke.fts.api.domain.book.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.guilhermewoelke.fts.api.domain.book.entity.BookModel;
import com.github.guilhermewoelke.fts.api.domain.book.repository.BookDAOInterface;
import com.github.guilhermewoelke.fts.api.infra.cache.CacheData;
import com.github.guilhermewoelke.fts.api.infra.cache.CacheDataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class BookService {
    @Autowired
    private BookDAOInterface dao;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private CacheDataRepository cache;

    //@Cacheable(value="book.find", key = "#id")
    public BookModel findByID(Integer id) {
        BookModel book = null;
        try {
            Optional<CacheData> cacheData = cache.findById("book.find."+id);

            if (cacheData.isPresent()) {
                String value = cacheData.get().getValue();
                TypeReference<BookModel> map = new TypeReference<BookModel>() {};
                book = objectMapper.readValue(value, map);
                System.out.println("Livro do cache");
                return book;
            }

            book = dao.findByID(id);
            String json = objectMapper.writeValueAsString(book);
            CacheData data = new CacheData("book.find."+id, json);
            cache.save(data);

            System.out.println("Livro do db");

        } catch (JsonProcessingException e) {
            System.out.println("Ocorreu um erro ao processar o json: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Ocorreu um erro ao recuperar/cachear o livro: " + e.getMessage());
        }

        return book;
    }

    //@Cacheable(value="book.list")
    public List<BookModel> list() {
        List<BookModel> books = new ArrayList<>();
        try {
            Optional<CacheData> cacheData = cache.findById("book.list");

            if (cacheData.isPresent()) {
                String value = cacheData.get().getValue();
                TypeReference<List<BookModel>> map = new TypeReference<List<BookModel>>() {};
                books = objectMapper.readValue(value, map);
                System.out.println("Livros do cache");
                return books;
            }

            books = dao.list();
            String json = objectMapper.writeValueAsString(books);
            CacheData data = new CacheData("book.list", json);
            cache.save(data);

            System.out.println("Livros do db");
        } catch (JsonProcessingException e) {
            System.out.println("Ocorreu um erro ao processar o json: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Ocorreu um erro ao recuperar/cachear a lista de livros: " + e.getMessage());
        }
        return books;
    }
}
