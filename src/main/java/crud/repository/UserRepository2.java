package crud.repository;

import crud.model.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UserRepository2 implements CrudRepository {
    private final List<User> users = new ArrayList<>();

    @Override
    public Optional<User> findById(final long id) {
        int idInt = (int) id;

        return Optional.ofNullable(users.get(idInt));
    }

    @Override
    public Optional<User> findByGeneric(final User user) {
        return users.stream()
                .filter(u -> u.equals(user))
                .findFirst();
    }


    @Override
    public void save(final Object o) {

        users.add((User) o);
    }


    @Override
    public void delete(final long id) {
        users.remove(id);
    }

    @Override
    public List<User> findAll() {
        return users;
    }
}
