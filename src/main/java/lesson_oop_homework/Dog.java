package lesson_oop_homework;

public class Dog extends Animal {
    int distance = 0;

    int count = 0;


    @Override
    public void run() {
        if (distance <= 500) {
            System.out.println("Собака пробежала " + distance + " м.");
        }
    }

    @Override
    public void swim() {
        if (distance <= 10) {
            System.out.println("Собака пропылала " + distance + " м.");
        }
    }

    public Dog(int distance) {
        this.distance = distance;
        count++;
    }

}
