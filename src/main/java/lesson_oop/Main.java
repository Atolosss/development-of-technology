package lesson_oop;

public class Main {
    public static void main(String[] args) {

    }

}

class User {
    private String value;

    public User(String value) {
        this.value = value;
    }

    public static void printStaticInfo(User user) {
        System.out.println(user.value);
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public void printInfo() {
        System.out.println(this.value);
    }
}
