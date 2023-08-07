package crud.repository;

import crud.model.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ListUserRepository implements CrudRepository<User> {
    private final List<User> users = new ArrayList<>();

    @Override
    public Optional<User> findById(final long id) {
        return users.stream()
                .filter(user -> user.getId() == id)
                .findFirst();
    }

    @Override
    public Optional<User> findByObject(final User user) {
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
        users.removeIf(u -> u.getId() == id);
    }

    @Override
    public List<User> findAll() {
        return users;
    }
}
