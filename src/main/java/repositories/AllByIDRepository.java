package repositories;

import java.util.List;

public interface AllByIDRepository<T> {

    List<T> findAllByUserId(Long id);
    void deleteAllByUserId(Long id);
}
