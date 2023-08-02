package crud.service;

import crud.exception.UserNotFoundException;
import crud.model.User;
import crud.repository.CrudRepository;

public class UserService {
    private final CrudRepository CrudRepository;

    public UserService(final CrudRepository userCrudRepository) {
        this.CrudRepository = userCrudRepository;
    }

    public User findById(final long id) {
        return CrudRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("user with id " + id + " not found!"));
    }

    public User save(final User user) {
        return (User) CrudRepository.findById(user.getId())
                .orElse(CrudRepository.save(user));
    }

}
