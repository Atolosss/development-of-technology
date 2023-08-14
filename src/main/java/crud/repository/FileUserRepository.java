package crud.repository;

import crud.model.User;

import java.util.List;
import java.util.Optional;

//TODO: Изучить NIO (new input output) радота с пользователями в .csv файле

//1;Igor;23
//2;Vasya;30
//3;Masha;12
//4;asdasd;12
//5;Vasya;30

public class FileUserRepository implements CrudRepository<User> {

    @Override
    public Optional<User> findById(final long id) {
        return null;
    }

    @Override
    public Optional<User> findByObject(User user) {
        return null;
    }


    @Override
    public void save(final User user) {
        return;
    }


    @Override
    public void delete(final long id) {
        return;
    }

    @Override
    public List<User> findAll() {
        return null;
    }
}
