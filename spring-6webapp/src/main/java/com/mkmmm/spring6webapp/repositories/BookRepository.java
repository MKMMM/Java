package com.mkmmm.spring6webapp.repositories;

import com.mkmmm.spring6webapp.domain.Book;
import org.springframework.data.repository.CrudRepository;

public interface BookRepository extends CrudRepository<Book, Long> {
}
