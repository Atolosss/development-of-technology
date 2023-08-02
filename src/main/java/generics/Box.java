package generics;

import java.util.ArrayList;
import java.util.List;

public class Box<T extends Fruit> {

    private final List<T> fruits = new ArrayList<>();

    public void addFruit(final T t) {
        fruits.add(t);
    }

    public void addFruits(final T... t1) {
        for (T t : t1) {
            fruits.add(t);
        }
    }


    /**
     * Сделать метод getWeight(), к
     * оторый высчитывает вес коробки, зная вес одного фрукта и
     * их количество: вес яблока - 1.0f, апельсина - 1.5f (единицы измерения не важны);
     *
     * @return считает вес коробки
     */
    public float getWeight() {
        float sum = 0;

        for (T t : fruits) {
            sum += t.getWeight();
        }
        return sum;
    }

    /**
     * Внутри класса Вох сделать метод сотраге(), к
     * оторый позволяет сравнить текущую коробку с той, которую подадут в с
     * отраге() в качестве параметра. true - если их массы ра
     * вны, false в противоположном случае. Можно сравнивать коробки с яблоками и апельсинами;
     *
     * @param anotherBox
     * @return сравнивает вес текущей коробки и вызываемой
     */
    public boolean compare(final Box<? extends Fruit> anotherBox) {
        float weight = getWeight();
        float weightAnotherBox = anotherBox.getWeight();
        return weight == weightAnotherBox;
    }

//    Написать метод, который позволяет пересыпать фрукты из текущей коробки в другую.
//    Помним про сортировку фруктов: нельзя яблоки высыпать в коробку с апельсинами.
//    Соответственно, в текущей коробке фруктов не остается, а в другую перекидываются объекты, которые были в первой;

    public void sprinkleInto(final Box<T> anotherBox) {
        for (T t : fruits) {
            anotherBox.addFruit(t);
        }
        fruits.clear();
    }
}
