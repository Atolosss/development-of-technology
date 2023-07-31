package crud.repository;

import crud.model.User;

import java.util.List;
import java.util.Optional;

//Create read update delete
public interface UserCrudRepository {

    Optional<User> findById(long id);
    Optional<User> findByUser(User user); //equals / hashcode
    User save(User user); //update
    void delete(long id);
    List<User> findAll();
}
