package com.mkmmm.spring6webapp.repositories;

import com.mkmmm.spring6webapp.domain.Publisher;
import org.springframework.data.repository.CrudRepository;

public interface publisherRepository extends CrudRepository<Publisher, Long> {
}
