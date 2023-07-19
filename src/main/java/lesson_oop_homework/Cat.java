package lesson_oop_homework;

public class Cat extends Animal {
    int distance = 0;

    int count = 0;

    @Override
    public void run() {
        if (distance <= 200) {
            System.out.println("Кот пробеажал " + distance + " м.");
        }
    }

    @Override
    public void swim() {
        System.out.println("Коты не плавают");
    }

    public Cat(int distance) {
        this.distance = distance;
        count++;
    }
}
