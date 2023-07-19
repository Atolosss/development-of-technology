package lesson_oop_homework;

public class Dog extends Animal {


    private static int count = 0;

    public Dog(String name) {
        super(name);
        count++;
    }




    public static int getCount() {
        return count;
    }

    @Override
    public void run(int distance) {
        if (distance <= 500) {
            System.out.println("Собака пробежала " + distance + " м.");
        }
    }

    @Override
    public void swim(int distance) {
        if (distance <= 10) {
            System.out.println("Собака пропылала " + distance + " м.");
        }
    }

}
