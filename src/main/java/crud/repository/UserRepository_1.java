package crud.repository;

import crud.model.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UserRepository_1 implements UserCrudRepository {
    private final List<User> users = new ArrayList<>();

    @Override
    public Optional<User> findById(long id) {
        for (User u: users) {
            if (u.getId() == id) {
                return Optional.of(u);
            }
        }
        return Optional.empty();
    }

    @Override
    public User save(User user) {
        users.add(user);
        return user;
    }
}
