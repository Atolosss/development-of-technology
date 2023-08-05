package collection;

import java.util.HashSet;
import java.util.Set;

public class App {
    public static void main(String[] args) {
        Set<User> userSet = new HashSet<>();
        User user = new User(1, "1");
        System.out.println(user.hashCode());

        userSet.add(user);

        user.setName("2");
        System.out.println(userSet.contains(user));

        userSet.forEach(System.out::println);
    }
}
