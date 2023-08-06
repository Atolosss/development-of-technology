package collection.homework;

import java.util.HashMap;

public class PhoneBook {
    private HashMap<String, String> numbers = new HashMap<>();



    public void add(final String number,final String firstName) {
        numbers.put(firstName, number);
    }

    public void get(final String firstname) {
        for (String s : numbers.keySet()) {
            if (firstname.equals(s)) {
                System.out.println(numbers.values());
            }
        }
    }
}
