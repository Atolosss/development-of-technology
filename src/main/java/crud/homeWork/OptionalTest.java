package crud.homeWork;

import java.util.Optional;

public final class OptionalTest {
    private OptionalTest() {
    }

    public static int getValue(final Optional<Integer> optional) {
        return optional.orElse(0);
    }

    public static void lengthString(final Optional<String> optional) {
        optional.ifPresent(value -> System.out.println(value.length()));
        if (optional.isEmpty()) {
            System.out.println("Значение отсутсвует");
        }
    }

    public static void squareValue(final Optional<Double> optional) {
        optional.map(value -> value * value);
    }

    public static boolean equalsBoolean(final Optional<Boolean> optional) {
        return optional.orElse(false);
    }

    public static void returnString(final Optional<String> optional) {
        optional.map(value -> new StringBuilder(value).reverse().toString());

    }

    public static Optional<Integer> checkValue(final Optional<Integer> optional) {
        return optional.filter(value -> value > 0);
    }

    public static Optional<Integer> sumValues(final Optional<Integer> opt1, final Optional<Integer> opt2) {
        return opt1.flatMap(value1 -> opt2.map(value2 -> value1 + value2));
    }

}

