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
    public User save(User user) {
        return null;
    }
}
