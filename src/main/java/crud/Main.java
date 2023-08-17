package crud;


import crud.model.User;
import crud.repository.FileUserRepository;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.Random;

public final class Main {
    public static final String USER_NOT_FOUND_WITH_ID_D = "user not found with id: %d";
    private static final Random RANDOM = new Random();

    private Main() {
    }

    public static void main(final String[] args) throws InterruptedException, IOException {
        File file = new File("list.csv");
        if (!file.exists() && file.createNewFile()) {
            System.out.println("file: " + file + " did created!");
        }

        FileUserRepository fileUserRepository = new FileUserRepository(file);
        User user = getUser(135L, "Igor");
        fileUserRepository.save(user);
        while (true) {
            long id = RANDOM.nextLong(100);
            fileUserRepository.findById(id)
                    .ifPresentOrElse(System.out::println,
                            () -> System.out.println(USER_NOT_FOUND_WITH_ID_D.formatted(id)));
            Thread.sleep(500);

            if (id == 99) {
                break;
            }
        }
    }


    private static User getUser(final long id, final String name) {
        return User.builder()
                .id(id)
                .name(name)
                .age(25).salary(new BigDecimal("2500.12"))
                .build();
    }
}


