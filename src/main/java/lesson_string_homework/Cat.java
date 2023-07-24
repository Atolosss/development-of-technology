package lesson_string_homework;

public class Cat {
    private final String name;
    private final int appetite;

    private boolean satiety;

    public Cat(final String name, final int appetite) {
        this.name = name;
        this.appetite = appetite;
        this.satiety = false;
    }

    public String getName() {
        return name;
    }

    public boolean isSatiety() {
        return satiety;
    }

    public void eat(final Plate p) {
        int plateInfo = p.getFood();
        p.decreaseFood(appetite);
        if (plateInfo != p.getFood()) {
            this.satiety = true;
        }
    }
}
