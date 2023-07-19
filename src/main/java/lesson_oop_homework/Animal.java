package lesson_oop_homework;

public abstract class Animal {

    private static int count = 0;
    private String name;

    public Animal(final String name) {
        if (name == null || name.equals("")) {

            this.name = "default";
        } else {


            this.name = name;
        }
        count++;
    }

    public static int getCount() {
        return count;
    }


    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public abstract void run(int distance);

    public abstract void swim(int distance);
}
