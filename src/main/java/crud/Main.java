package crud;


import crud.model.User;
import crud.repository.FileUserRepository;

import java.io.File;

public final class Main {
    private Main() {
    }

    public static void main(String[] args) {
        File file = new File("list.csv");
        FileUserRepository repository = new FileUserRepository( file);

        User user = getUser(1L, "Igor");
        User user2 = getUser(2L, "Max");
        User user3 = getUser(1L, "Vas");




        repository.save(user);
        repository.save(user2);
        repository.save(user3);



    }

    private static User getUser(long id, String name) {
        return User.builder()
                .id(id)
                .name(name)
                .age(25)
                .build();
    }
}


