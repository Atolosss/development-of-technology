package crud.repository;

import crud.model.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UserRepository1 implements CrudRepository {
    private final List<User> users = new ArrayList<>();

    @Override
    public Optional<User> findById(final long id) {
        for (User u : users) {
            if (u.getId() == id) {
                return Optional.of(u);
            }
        }
        return Optional.empty();
    }

    @Override
    public Optional<User> findByGeneric(final User user) {
        return users.stream()
                .filter(u -> u.equals(user))
                .findFirst();
    }

    @Override
    public void save(final User user) {
        users.add(user);
    }


    @Override
    public void delete(final long id) {
        for (User u : users) {
            if (u.getId() == id) {
                users.remove(u);
            }
        }
    }

    @Override
    public List<User> findAll() {
        return users;
    }
}
