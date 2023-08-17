package crud.repository;

import crud.mapper.UserMapper;
import crud.model.User;
import lombok.RequiredArgsConstructor;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

//TODO: Файл содержит шапку, поправить логику в коде+
//TODO: BigDecimal разобраться, что это и зачем+
//TODO: Расширить функционал для новой колонки salary
//TODO: nio vs io

//TODO: Посчитать среднюю зарплату по юзерам старше 30 лет+
//TODO: Просуммировать колонки id, age, salary и посчитать общуюю сумму этих колонок -> BigDecimal+
//TODO: Все Юзеры стоящие на четных id имеют зарплату больше 2000.0+
//TODO: Получить уникальных список имен для всех юзеров начиная с позиции 10+
//TODO: Найти максимальную зарплату по всем юзерам

@RequiredArgsConstructor
public class FileUserRepository implements CrudRepository<User, Long> {

    private final File file;

    public Optional<User> findById(final Long id) {
        return readUsersFromFile()
                .stream()
                .filter(user -> user.getId().equals(id))
                .findFirst();
    }

    @Override
    public Optional<User> findByObject(final User user) {
        return readUsersFromFile()
                .stream()
                .filter(usr -> usr.equals(user))
                .findFirst();
    }

    public BigDecimal averageSalary(final int age) {
        BigDecimal sumSalary = readUsersFromFile().stream()
                .filter(user -> user.getAge() > age)
                .map(User::getSalary)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        long count = readUsersFromFile().stream()
                .filter(user -> user.getAge() > age)
                .count();

        if (count == 0) {
            return BigDecimal.ZERO;
        }

        return sumSalary.divide(BigDecimal.valueOf(count));

    }

    public boolean salaryCheck(final int salary) {
        boolean check = readUsersFromFile()
                .stream()
                .allMatch(user -> user.getId() % 2 == 0 && user.getSalary().intValue() > salary);
        return check;

    }

    public List<String> findUniqName(final long line) {
        return readUsersFromFile()
                .stream()
                .skip(line)
                .map(User::getName).
                distinct()
                .toList();
    }

    public BigDecimal findMaxSalary() {
        return readUsersFromFile()
                .stream()
                .map(User::getSalary)
                .max(BigDecimal::compareTo)
                .orElse(BigDecimal.ZERO);
    }

    public BigDecimal sumOfAllNumbers() {
        return readUsersFromFile()
                .stream()
                .map(user -> user.getSalary().add(BigDecimal.valueOf(user.getId())).add(BigDecimal.valueOf(user.getAge())))
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    @Override
    public void save(final User user) {
        try {
            List<User> users = new ArrayList<>(readUsersFromFile());
            if (users.contains(user)) {
                int indexOf = users.indexOf(user);
                users.set(indexOf, user);
                Files.writeString(Paths.get(file.toURI()), UserMapper.usersToLine(users), StandardOpenOption.TRUNCATE_EXISTING);
            } else {
                users.add(user);
                Files.writeString(Paths.get(file.toURI()), UserMapper.userToLine(user), StandardOpenOption.APPEND);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public void delete(final Long id) {
        List<User> userList = readUsersFromFile()
                .stream()
                .filter(user -> !user.getId().equals(id))
                .toList();
        try {
            Files.writeString(Paths.get(file.toURI()), UserMapper.usersToLine(userList), StandardOpenOption.TRUNCATE_EXISTING);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<User> findAll() {
        return readUsersFromFile();
    }

    private List<User> readUsersFromFile() {
        try (Stream<String> lines = Files.lines(Paths.get(file.toURI()))) {
            return lines.skip(1)
                    .filter(line -> !line.isEmpty()) // Фильтруем пустые строки
                    .map(UserMapper::lineToUser)
                    .toList();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void writeUsersToFile(final List<User> users) {
        try {
            Files.writeString(Paths.get(file.toURI()), UserMapper.usersToLine(users),
                    StandardOpenOption.APPEND);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
