package lesson_oop_homework;

public class Main {
    public static void main(String[] args) {
        Animal dog1 = new Dog("1");
        Animal dog4 = new Dog(null);
        Animal dog5 = new Dog("");
        Animal cat = new Cat("2");
        Dog dog3 = new Dog("3");
        System.out.println(Dog.getCount());
        System.out.println(Cat.getCount());
        System.out.println(Cat.getCount());
        Animal[] animals = new Animal[5];
        animals[0] = dog1;
        animals[1] = dog4;
        animals[2] = dog5;
        animals[3] = cat;
        animals[4] = dog3;
        for (Animal a : animals) {
            System.out.println(a.getName());
        }


    }
}
