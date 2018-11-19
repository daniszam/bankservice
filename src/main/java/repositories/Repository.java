package repositories;

import java.util.List;
import java.util.Optional;

public interface Repository<T> {
    Optional<T> findOne(Long id);
    boolean save(T model);
    void delete(Long id);
    List<T> findAll();
    void update(T model);
}
