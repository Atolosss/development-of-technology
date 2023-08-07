package collection.homework;

import java.util.HashMap;
import java.util.List;

public class PhoneBook {
    private final HashMap<String, List<String>> numbers = new HashMap<>();

    public void add(final String number, final String firstName) {
        //TODO: add
//        numbers.put(firstName, number);
    }

    public void get(final String firstname) {
        //TODO: add
        for (String s : numbers.keySet()) {
            if (firstname.equals(s)) {
                System.out.println(numbers.values());
            }
        }
    }
}
