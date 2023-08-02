package crud.repository;

import java.util.List;
import java.util.Optional;

//Create read update delete
public interface CrudRepository<T> {

    Optional<T> findById(long id);


    Optional<T> findByObject(T t);

    void save(T t);

    void delete(long id);


    List<T> findAll();
}
