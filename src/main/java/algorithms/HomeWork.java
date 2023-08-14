package algorithms;

import java.util.Map;
import java.util.Random;
import java.util.Scanner;

public final class HomeWork {

    public static final int REPEAT = 3;
    public static final int STEP = 3;

    public static final int SIX = 6;
    public static final int BOUND = 9;
    public static final String[] WORDS = new String[]{"apple", "orange", "lemon", "banana", "apricot", "avocado", "broccoli", "carrot",
            "cherry", "garlic", "grape", "melon", "leak", "kiwi", "mango", "mushroom", "nut", "olive", "pea", "peanut",
            "pear", "pepper", "pineapple", "pumpkin", "potato"};

    private HomeWork() {
    }

    public static int[] arrayChange(final int[] array) {
        for (int i = 0; i < array.length; i++) {
            if (array[i] == 0) {
                array[i] = 1;
            } else {
                array[i] = 0;
            }
        }
        return array;
    }

    public static int[] arrayFilling(final int[] array) {
        int step = STEP;
        for (int i = 0; i < array.length; i++) {
            if (i == 0) {
                array[i] = i;
            } else {
                array[i] = array[i - 1] + step;
            }
        }

        return array;
    }

    public static void findTheSix(final int[] array) {

        for (int i = 0; i < array.length; i++) {
            if (array[i] < SIX) {
                array[i] = array[i] * 2;
            }
        }
    }

    public static void fillingDiagonally(final int[][] array) {
        for (int i = 0; i < array.length; i++) {
            array[i][i] = 1;
            array[i][(array.length - 1) - i] = 1;
        }
    }

    public static void findTheMaximumAndMinimumValue(final int[] array) {
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < array.length; i++) {
            if (array[i] > max) {
                max = array[i];
            }
            if (array[i] < min) {
                min = array[i];
            }
        }
    }

    public static boolean checkBalance(final int[] array) {
        boolean check = false;
        int sum1 = 0;
        int sum2 = 0;
        int count = 0;

        while (count < array.length) {
            for (int i = 0; i <= count; i++) {
                sum1 += array[i];
            }
            for (int k = count + 1; k < array.length; k++) {
                sum2 += array[k];
            }
            if (sum1 == sum2) {
                check = true;
            }
            sum1 = 0;
            sum2 = 0;
            count++;
        }
        return check;
    }

    public static void reversString(final String string) {
        StringBuilder stringBuilder = new StringBuilder(string);
        stringBuilder.reverse();
        System.out.println(stringBuilder);
    }

    public static void findMaxValue(final int[] num) {
        int maxValue = Integer.MIN_VALUE;
        for (Integer i : num) {
            if (i > maxValue) {
                maxValue = i;
            }
        }
        System.out.println(maxValue);
    }

    // abc ccc bbb cccc, a = 1 , b = 2, c = 3;
//    TODO: возвращать не число ,а слово. StringBuilder или String

    public static StringBuilder getMaxWord(final String str, final Map<Character, Integer> charSize) {
        char[] chars = str.toCharArray();
        int max = 0;
        int currentMax = 0;
        int count = 0;
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < chars.length; i++) {
            if (chars[i] == ' ' || i == chars.length - 1) {
                if (currentMax > max) {
                    max = currentMax;
                    count = i;
                }
                currentMax = 0;
            } else {
                currentMax += charSize.get(chars[i]);
            }
        }
        for (int i = count; chars[i] != ' '; i--) {
            if (chars[i] != ' ') {
                sb.append(chars[i]);
            }
        }
        return sb.reverse();
    }

        public void guessTheNumberGame() {
            Random random = new Random();
            int answer = 1;
            int attempt = 0;
            Scanner sc = new Scanner(System.in);
            while (answer != 0) {
                int randomNumber = random.nextInt(BOUND);
                while (attempt < REPEAT) {
                    System.out.println("Введите число :");
                    int number = sc.nextInt();
                    if (number > randomNumber) {
                        System.out.println("Ваше число больше");
                    } else if (number < randomNumber) {
                        System.out.println("Ваше число меньше");
                    } else {
                        System.out.println("Поздравляю! Вы угадали!");
                        break;
                    }
                    attempt++;
                }
                attempt = 0;
                System.out.println("Повторить игру еще раз? 1 - да / 0 - нет");
                answer = sc.nextInt();
            }
            sc.close();
        }
        //    sting => abc bbc ccb tess
//sting => gfd hjk, map <key, value>
//нужно написать функцию, которая вернет слово с наибольшей суммой.
//Сумма - это соотношение буква к какому-то числу


}



