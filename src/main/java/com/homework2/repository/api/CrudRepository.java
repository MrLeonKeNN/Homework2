package com.homework2.repository.api;

import java.util.List;
import java.util.Optional;

public interface CrudRepository<T, ID> {

    void save(T t);
    Optional<T> findById(ID id);
    List<T> findAll();
    void deleteById(ID id);
    void delete(T entity);
}
