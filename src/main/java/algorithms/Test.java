package algorithms;

import java.util.HashMap;
import java.util.Map;

public final class Test {
    private Test() {
    }

    public static void main(String[] args) {
        String test = "aaa bbb bbcc";
        Map<Character,Integer> chars = new HashMap<>();
        chars.put('a',1);
        chars.put('b',2);
        chars.put('c',3);
        System.out.println(HomeWork.getMaxWord(test,chars));
    }
}
