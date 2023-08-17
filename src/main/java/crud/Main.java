package crud;


import crud.model.User;
import crud.repository.FileUserRepository;

import java.io.File;
import java.math.BigDecimal;

public final class Main {
    private Main() {
    }

    public static void main(final String[] args) {
        File file = new File("list.csv");
        FileUserRepository repository = new FileUserRepository(file);
        User user = getUser(13L, "Igor");
        System.out.println(repository.averageSalary(26));
        System.out.println(repository.sumOfAllNumbers());
        System.out.println(repository.salaryCheck(2000));
        System.out.println(repository.findUniqName(6));

    }


    private static User getUser(final long id, final String name) {
        return User.builder()
                .id(id)
                .name(name)
                .age(25).salary(new BigDecimal("2500.12"))
                .build();
    }
}


