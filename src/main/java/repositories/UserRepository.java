package repositories;

public interface UserRepository<T> {
    T findByName();
}
