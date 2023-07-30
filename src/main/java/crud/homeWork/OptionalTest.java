package crud.homeWork;

import java.util.Optional;
import java.util.OptionalInt;

public class OptionalTest {

    public static int getValue(Optional<Integer> optional) {
        return optional.orElse(0);
    }

    public static void lengthString(Optional<String> optional) {
        optional.ifPresent(value -> System.out.println(value.length()));
        if (optional.isEmpty()) {
            System.out.println("Значение отсутсвует");
        }
    }

    public static void squareValue(Optional<Double> optional) {
        optional.map(value -> value * value);
    }

    public static boolean equalsBoolean(Optional<Boolean> optional) {
        return optional.orElse(false);
    }

    public static void returnString(Optional<String> optional) {
        optional.map(value -> new StringBuilder(value).reverse().toString());

    }

    public static Optional<Integer> checkValue(Optional<Integer> optional) {
         return optional.filter(value -> value > 0);
    }

    public static Optional<Integer> sumValues(Optional<Integer> opt1, Optional<Integer> opt2) {
        return opt1.flatMap(value1 -> opt2.map(value2 -> value1 + value2));
    }

}

