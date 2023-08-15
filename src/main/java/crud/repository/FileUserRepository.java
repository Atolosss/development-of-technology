package crud.repository;

import crud.mapper.UserMapper;
import crud.model.User;
import lombok.RequiredArgsConstructor;

import java.io.File;
import java.io.IOException;
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

    @Override
    public Optional<User> findByObject(final User user) {
        return readUsersFromFile()
                .stream()
                .filter(usr -> usr.equals(user))
                .findFirst();
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
            return lines.map(UserMapper::lineToUser).toList();
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
