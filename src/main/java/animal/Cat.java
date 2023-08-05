package animal;

public class Cat extends Animal {


    public static final int MAX = 200;
    private static int count = 0;

    public Cat(final String name) {
        super(name);
        count++;
    }

    public static int getCount() {
        return count;
    }

    @Override
    public void run(final int distance) {
        if (distance <= MAX) {
            System.out.println("Кот пробеажал " + distance + " м.");
        }
    }

    @Override
    public void swim(final int distance) {
        System.out.println("Коты не плавают");
    }
}
