package generics;

import collection.MyArrayList;

public final class Main {
    private Main() {
    }

    public static void main(final String[] args) {

        Box<Orange> orangeBox = new Box<>();
        Box<Apple> appleBox = new Box<>();
        appleBox.addFruits(new Apple(), new Apple(), new Apple());
        orangeBox.addFruits(new Orange(), new Orange());
        appleBox.compare(orangeBox);


        MyArrayList<String> myArrayList = new MyArrayList<>();
        myArrayList.add("apple");
        myArrayList.add("banana");
        myArrayList.add("orange");

        System.out.println(myArrayList.size());
        System.out.println(myArrayList.isEmpty());

        System.out.println(myArrayList.get(2));

        myArrayList.set(2, "grape");
        System.out.println(myArrayList.get(2)); // Output: After setting: grape

        myArrayList.remove(0);
        System.out.println(myArrayList.get(0)); // Output: After removing: banana
    }
}
