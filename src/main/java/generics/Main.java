package generics;

import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {

        List<Fruit> fruitList = new ArrayList<>();
        Box<Orange> orangeBox = new Box<>();
        Box<Apple> appleBox = new Box<>();
        appleBox.addFruits(new Apple(), new Apple(), new Apple());
        orangeBox.addFruits(new Orange(),new Orange());
        appleBox.compare(orangeBox);
    }
}
