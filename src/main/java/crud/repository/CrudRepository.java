package crud.repository;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public interface CrudRepository<T, K> {

    Optional<T> findById(K id);

    void save(T t) throws SQLException;

    void delete(K id);

    List<T> findAll();

}
