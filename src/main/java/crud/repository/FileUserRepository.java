package crud.repository;

import crud.mapper.UserMapper;
import crud.model.User;
import lombok.RequiredArgsConstructor;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

@RequiredArgsConstructor
public class FileUserRepository implements CrudRepository<User, Long> {

    private final File file;

    public Optional<User> findById(final Long id) {
        return readUsersFromFile()
                .filter(user -> user.getId().equals(id))
                .findFirst();
    }

    @Override
    public Optional<User> findByObject(final User user) {
        return readUsersFromFile()
                .filter(usr -> usr.equals(user))
                .findFirst();
    }

    //TODO если юзер user существует, то апдетим существующего, иначе аппендим нового
    @Override
    public void save(final User user) {
//        List<User> users = readUsersFromFile();
//        for (User u : users) {
//            if (!u.equals(user)) {
//                users.add(user);
//                writeUsersToFile(users);
//            }
//        }
    }

    //TODO рефактор с stream + nio
    @Override
    public void delete(final Long id) {
//        List<User> users = readUsersFromFile();
//        for (User u : users) {
//            if (u.getId() == id) {
//                users.remove(u);
//                writeUsersToFile(users);
//            }
//        }
    }

    @Override
    public List<User> findAll() {
        return readUsersFromFile().toList();
    }

    private Stream<User> readUsersFromFile() {
        try (Stream<String> lines = Files.lines(Paths.get(file.toURI()))) {
            return lines.map(UserMapper::lineToUser);
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
