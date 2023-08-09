package collection.homework;

import java.util.Map;
import java.util.HashMap;
import java.util.Set;
import java.util.HashSet;
import java.util.ArrayList;


public final class HomeWork {
    private HomeWork() {
    }

    public static void main(final String[] args) {
        String[] stringList = new String[]{"Дятел", "Дятел", "овен", "телец", "рак", "машина", "рак",
                "картина", "машина", "ключи", "карточка", "овощи", "ключи"};
        findString(stringList);

        PhoneBook phoneBook = new PhoneBook();
        phoneBook.add("89166077913", "Markov");
        phoneBook.add("89165546478", "Markov");
        phoneBook.add("89995647722", "Ivanov");
        phoneBook.add("80000000000", "Ivanov");
        phoneBook.get("Markov");
    }


    public static Map<String, Integer> findString(final String[] stringList) {
        Map<String, Integer> wordCount = new HashMap<>();
        for (String word : stringList) {
            wordCount.put(word, wordCount.getOrDefault(word, 0) + 1);
        }
        return wordCount;
    }

    public static Set<Object> unionList(final ArrayList<Object> list1, final ArrayList<Object> list2) {
        Set<Object> setLists = new HashSet<>();
        setLists.addAll(list1);
        setLists.addAll(list2);
        return setLists;
    }


}
