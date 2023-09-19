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

@RequiredArgsConstructor
public class FileUserRepository implements CrudRepository<User, Long> {

    private final File file;

    public Optional<User> findById(final Long id) {
        return readUsersFromFile()
                .stream()
                .filter(user -> user.getId().equals(id))
                .findFirst();
    }


    public BigDecimal averageSalary(final int age) {

        return BigDecimal.valueOf(readUsersFromFile().stream()
                .filter(user -> user.getAge() > age)
                .map(User::getSalary)
                .mapToDouble(BigDecimal::doubleValue)
                .average()
                .orElse(0)
        );
    }

    public boolean salaryCheck(final int salary) {
        return readUsersFromFile()
                .stream()
                .allMatch(user -> user.getId() % 2 == 0 && user.getSalary().intValue() > salary);
    }

    public List<String> findUniqName(final long line) {
        return readUsersFromFile()
                .stream()
                .skip(line)
                .map(User::getName)
                .distinct()
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
                .map(user -> user.getSalary()
                        .add(BigDecimal.valueOf(user.getId()))
                        .add(BigDecimal.valueOf(user.getAge())))
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    //TODO слетает шапка при обновлении
    @Override
    public User save(final User user) {
        try {
            List<User> users = new ArrayList<>(readUsersFromFile());
            if (users.contains(user)) {
                int indexOf = users.indexOf(user);
                users.set(indexOf, user);
                saveAll(users);
            } else {
                users.add(user);
                appendUser(user);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return null;
    }

    @Override
    public void delete(final Long id) {
        List<User> userList = readUsersFromFile()
                .stream()
                .filter(user -> !user.getId().equals(id))
                .toList();
        try {
            saveAll(userList);
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
            return lines
                    .skip(1)
                    .map(UserMapper::lineToUser)
                    .toList();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    private void saveAll(final List<User> users) throws IOException {
        Files.writeString(Paths.get(file.toURI()), UserMapper.usersToLine(users), StandardOpenOption.TRUNCATE_EXISTING);
    }

    private void appendUser(final User user) throws IOException {
        Files.writeString(Paths.get(file.toURI()), UserMapper.userToLine(user), StandardOpenOption.APPEND);
    }
}
