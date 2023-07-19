package lesson_oop_homework;

public class Animal {

    int count= 0;
    public void run(){
        System.out.println("Животное бежит");
    };

    public void swim(){
        System.out.println("Животное плывет");
    }

    public Animal() {
        count++;
    }
}
