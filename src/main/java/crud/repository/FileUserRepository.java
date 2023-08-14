package crud.repository;

import crud.model.User;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


public class FileUserRepository implements FileCrudRepository<User> {

    public static final int COLUMN = 3;
    private final String filePath;
    public FileUserRepository(final String filePathInput) {
        this.filePath = filePathInput;
    }


    public Optional<User> findById(final long id) {
        List<User> users = readUsersFromFile();
        for (User u : users) {
            if (id == u.getId()) {
                return Optional.of(u);
            }
        }
        return Optional.ofNullable(users.get((int) id));
    }


    @Override
    public Optional<User> findByObject(final User user) {
        List<User> users = readUsersFromFile();
        return users.stream().filter(u -> u.equals(user)).findFirst();
    }

    @Override
    public void save(final User user) {
        List<User> users = readUsersFromFile();
        for (User u : users) {
            if (!u.equals(user)) {
                users.add(user);
                writeUsersToFile(users);
            }
        }
    }

    @Override
    public void delete(final long id) {
        List<User> users = readUsersFromFile();
        for (User u : users) {
            if (u.getId() == id) {
                users.remove(u);
                writeUsersToFile(users);
            }
        }
    }

    @Override
    public List<User> findAll() {
        return readUsersFromFile();
    }

    private List<User> readUsersFromFile() {
        List<User> users = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(";");
                if (parts.length == COLUMN) {
                    int id = Integer.parseInt(parts[0]);
                    String name = parts[1];
                    int age = Integer.parseInt(parts[2]);
                    users.add(new User(id, name, age));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return users;
    }

    private void writeUsersToFile(final List<User> users) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            for (User user : users) {
                writer.write(user.getId() + ";" + user.getName() + ";" + user.getAge());
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
