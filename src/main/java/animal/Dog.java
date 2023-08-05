package animal;

public class Dog extends Animal {


    public static final int MAXRUN = 500;
    public static final int MAXSWIM = 10;
    private static int count = 0;

    public Dog(final String name) {
        super(name);
        count++;
    }


    public static int getCount() {
        return count;
    }

    @Override
    public void run(final int distance) {
        if (distance <= MAXRUN) {
            System.out.println("Собака пробежала " + distance + " м.");
        }
    }

    @Override
    public void swim(final int distance) {
        if (distance <= MAXSWIM) {
            System.out.println("Собака проплыла " + distance + " м.");
        }
    }

}
