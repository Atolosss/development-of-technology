package crud;

import crud.model.User;
import crud.repository.UserRepository_1;
import crud.repository.UserRepository_2;
import crud.service.UserService;

import java.util.Random;

public class Main {
    public static void main(String[] args) {
        UserService userService;

        if (new Random().nextBoolean()) {
            UserRepository_2 userRepository2 = new UserRepository_2();
            userService = new UserService(userRepository2);
        } else {
            UserRepository_1 userRepository1 = new UserRepository_1();
            userService = new UserService(userRepository1);
        }

        User byId = userService.findById(12);

        User igor = new User(1, "Igor");
        userService.save(igor);
        System.out.println(byId.toString());
    }
}
