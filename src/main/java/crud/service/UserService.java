package crud.service;

import crud.exception.UserNotFoundException;
import crud.model.User;
import crud.repository.UserCrudRepository;

public class UserService {
    private final UserCrudRepository userCrudRepository;

    public UserService(UserCrudRepository userCrudRepository) {
        this.userCrudRepository = userCrudRepository;
    }

    public User findById(long id) {
        return userCrudRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("user with id " + id + " not found!"));
    }

    public User save(User user) {
        return userCrudRepository.findById(user.getId())
                .orElse(userCrudRepository.save(user));
    }

}
