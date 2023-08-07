package collection;

import java.util.Comparator;
import java.util.Objects;
import java.util.Set;
import java.util.TreeSet;
import java.util.function.ToIntFunction;

public class App {
    public static void main(String[] args) {
        User user = User.builder()
                .id(1)
                .name("1")
                .age(12)
                .build();
        User user2 = User.builder()
                .id(1)
                .name("1")
                .age(10)
                .build();
        User user3 = User.builder()
                .id(1)
                .name("1")
                .age(15)
                .build();

        TreeSet<User> treeSet = new TreeSet<>(Comparator.comparingInt(User::getAge));
        treeSet.add(user);
        treeSet.add(user2);
        treeSet.add(user3);
        System.out.println(treeSet);


    }
}
