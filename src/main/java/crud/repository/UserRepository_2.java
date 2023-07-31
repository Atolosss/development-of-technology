package crud.repository;

import crud.model.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UserRepository_2 implements UserCrudRepository {
    private final List<User> users = new ArrayList<>();

    @Override
    public Optional<User> findById(long id) {
        return null;
    }

    @Override
    public Optional<User> findByUser(User user) {
        return Optional.empty();
    }

    @Override
    public User save(User user) {
        return null;
    }

    @Override
    public void delete(long id) {

    }

    @Override
    public List<User> findAll() {
        return null;
    }
}
