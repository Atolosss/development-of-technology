package lessons;

import java.util.Random;
import java.util.Scanner;

public final class HomeWork {

    public static final int REPEAT = 3;
    public static final int STEP = 3;

    public static final int SIX = 6;
    public static final int BOUND = 9;

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


    public void wordGuessingGame() {


        String[] words = {"apple", "orange", "lemon", "banana", "apricot", "avocado", "broccoli", "carrot",
                "cherry", "garlic", "grape", "melon", "leak", "kiwi", "mango", "mushroom", "nut", "olive", "pea", "peanut",
                "pear", "pepper", "pineapple", "pumpkin", "potato"};

        Random random = new Random();
        String secretWord = words[random.nextInt(words.length)];
        int wordLength = secretWord.length();

        System.out.println("Компьютер загадал слово из списка.");

        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.print("Введите ваш ответ: ");
            String userGuess = scanner.nextLine().toLowerCase();

            if (userGuess.equals(secretWord)) {
                System.out.println("Поздравляем! Вы угадали слово!");
                break;
            } else {
                int minLength = Math.min(wordLength, userGuess.length());
                StringBuilder hint = new StringBuilder("###############");

                for (int i = 0; i < minLength; i++) {
                    if (userGuess.charAt(i) == secretWord.charAt(i)) {
                        hint.setCharAt(i, userGuess.charAt(i));
                    }
                }

                System.out.println("Вы не угадали. Подсказка:");
                System.out.println(hint);
            }
        }

        scanner.close();
    }
}



