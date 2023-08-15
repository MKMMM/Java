package com.mkmmm.spring6webapp.services;

import com.mkmmm.spring6webapp.domain.Book;

public interface BookService {
    Iterable<Book> findAll();

}
