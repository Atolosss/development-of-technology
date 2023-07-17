package lesson_oop;

interface DatabaseInterface {

    default void test() {
        System.out.println("Hello world");
    }
}

public class App {

    public static void main(String[] args) {
    }
}

abstract class Database {
    private String value;

    public abstract String connect();
}
