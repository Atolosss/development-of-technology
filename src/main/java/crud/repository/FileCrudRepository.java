package crud.repository;

import crud.model.User;

import java.util.List;
import java.util.Optional;

public interface FileCrudRepository<T> {

    Optional<User> findById(long id);


    Optional<User> findByObject(User user);

    void save(User user);

    void delete(long id);

    List<T> findAll();
}
