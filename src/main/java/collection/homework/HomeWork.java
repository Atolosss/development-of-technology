package collection.homework;

import java.util.*;

public class HomeWork {
    public static void main(String[] args) {
        String[] stringList = new String[]{"Дятел", "Дятел", "овен", "телец", "рак", "машина", "рак",
                "картина", "машина", "ключи", "карточка", "овощи", "ключи"};
        calcFrequency(stringList);

        PhoneBook phoneBook = new PhoneBook();
        phoneBook.add("89166077913", "Markov");
        phoneBook.add("89165546478", "Markov");
        phoneBook.add("89995647722", "Ivanov");
        phoneBook.add("80000000000", "Ivanov");
        phoneBook.get("Markov");
    }



    public static Map<String, Integer> calcFrequency(final String[] stringList) {
        Map<String, Integer> wordCount = new HashMap<>();
        for (String word : stringList) {
            wordCount.put(word, wordCount.getOrDefault(word, 0) + 1);
        }
        return wordCount;
    }

    public static Set<Object> unionList(ArrayList<Object> list1, ArrayList<Object> list2) {
        Set<Object> setLists = new HashSet<>();
        setLists.addAll(list1);
        setLists.addAll(list2);
        return setLists;
    }


}
