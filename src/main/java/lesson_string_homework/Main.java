package lesson_string_homework;

public final class Main {
    public static void main(String[] args) {
        Cat[] nursery = new Cat[6];
        nursery[0] = new Cat("Бася", 10);
        nursery[1] = new Cat("Василий", 6);
        nursery[2] = new Cat("Боня", 3);
        nursery[3] = new Cat("Мартин", 8);
        nursery[4] = new Cat("Тэди", 11);
        nursery[5] = new Cat("Веня", 5);
        Plate plate = new Plate(30);
        for (Cat n : nursery) {
            n.eat(plate);
        }
        for (Cat n : nursery) {
            System.out.println(n.getName() + " сытость: " + n.isSatiety());
        }

        HomeWork.maxSum("abc cca bbcc");
    }
}
