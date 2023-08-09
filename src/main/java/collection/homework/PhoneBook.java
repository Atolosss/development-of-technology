package collection.homework;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PhoneBook {
    private final Map<String, List<String>> numbers = new HashMap<>();


    public void add(final String number, final String firstName) {
        if (numbers.containsKey(firstName)) {
            numbers.get(firstName).add(number);
        } else {
            List<String> arrayList = new ArrayList<>();
            arrayList.add(number);
            numbers.put(firstName, arrayList);
        }
    }

    public List<String> get(final String firstname) {
        return numbers.get(firstname);
    }
}
