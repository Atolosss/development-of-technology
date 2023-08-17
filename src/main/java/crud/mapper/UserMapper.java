package crud.mapper;

import crud.model.User;
import lombok.experimental.UtilityClass;

import java.math.BigDecimal;
import java.util.List;

@UtilityClass
public final class UserMapper {


    public static final String SEPARATOR = ";";

    public static User lineToUser(final String line) {
        String[] cols = line.split(SEPARATOR);
        return User.builder()
                .id(Long.valueOf(cols[0]))
                .name(cols[1])
                .age(Integer.parseInt(cols[2]))
                .salary(new BigDecimal(cols[3]))
                .build();
    }


    public static String userToLine(final User user) {
        return user.getId() + SEPARATOR + user.getName() + SEPARATOR + user.getAge() + SEPARATOR + user.getSalary() + System.lineSeparator();
    }

    public static String usersToLine(final List<User> userList) {
        var stringBuilder = new StringBuilder();
        for (User u : userList) {
            stringBuilder.append(userToLine(u));
        }
        return stringBuilder.toString();
    }
}
