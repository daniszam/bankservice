package ru.itis.darZam.repository;

import java.util.List;
import java.util.Optional;

public interface RepositoryDao<T> {
    Optional<T> findOne(Long id);
    void save(T model);
    void delete(Long id);
    List<T> findAll();
    void update(T model);

}
