package lesson_string_homework;

public class Plate {
    private int food;


    public Plate(final int food) {
        this.food = food;
    }

    public int getFood() {
        return food;
    }

    public void addFood(final int n) {
        food += n;
    }

    public void decreaseFood(final int n) {
        if (n < food) {

            food -= n;
        }
    }

    public void info() {
        System.out.println("plate: " + food);
    }
}
