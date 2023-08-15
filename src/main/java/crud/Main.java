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

        User user1 = new User(1, "Alice", 29);
        User user2 = new User(2, "Bob", 27);

        repository.save(user1);
        repository.save(user2);



    }
}


