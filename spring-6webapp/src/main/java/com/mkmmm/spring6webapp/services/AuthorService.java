package com.mkmmm.spring6webapp.services;
import com.mkmmm.spring6webapp.domain.Author;

public interface AuthorService {
    Iterable<Author> findAll();

}
