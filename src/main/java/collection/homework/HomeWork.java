package collection.homework;

import java.util.*;

public class HomeWork {
    public static void main(String[] args) {
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



    public static void findString(final String[] stringList) {

        HashSet<String> uniqueWords = new HashSet<>(Arrays.asList(stringList));

        HashMap<String, Integer> wordCount = new HashMap<>();
        for (String word : stringList) {
            if (wordCount.containsKey(word)) {
                wordCount.put(word, wordCount.get(word) + 1);
            } else {
                wordCount.put(word, 1);
            }
        }
        System.out.println(Arrays.toString(uniqueWords.toArray()));

        for (String word : wordCount.keySet()) {
            int count = wordCount.get(word);
            System.out.println(word + " : " + count);
        }

    }

    public static Set<Object> unionList(ArrayList<Object> list1, ArrayList<Object> list2) {
        Set<Object> setLists = new HashSet<>();
        setLists.addAll(list1);
        setLists.addAll(list2);
        return setLists;
    }


}
