package lessons2;

import java.util.Random;
import java.util.Scanner;

public class HomeWork {

    public static final int STEP = 3;

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

    public static int[] arrayFilling(int[] array) {
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

    public static void findTheSix() {
        int[] array = {1, 5, 3, 2, 11, 4, 5, 2, 4, 8, 9, 1};
        for (int i = 0; i < array.length; i++) {
            if (array[i] < 6) {
                array[i] = array[i] * 2;
            }
        }
    }

    public static void fillingDiagonally(int[][] arr) {
        for (int i = 0; i < arr.length; i++) {
            arr[i][i] = 1;
            arr[i][(arr.length - 1) - i] = 1;
        }
    }

    public static void findTheMaximumAndMinimumValue() {
        int[] array = {-20, 6, 56, -12, 20, 101, 3};
        int min = Integer.MAX_VALUE, max = Integer.MIN_VALUE;
        for (int i = 0; i < array.length; i++) {
            if (array[i] > max) {
                max = array[i];
            }
            if (array[i] < min) {
                min = array[i];
            }
        }
    }

    public static boolean checkBalance(int[] array) {
        boolean check = false;
        int sum1 = 0, sum2 = 0;
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

    public static void guessTheNumberGame() {
        Random random = new Random();
        int answer = 1;
        int attempt = 0;
        Scanner sc = new Scanner(System.in);
        while (answer != 0) {
            int randomNumber = random.nextInt(9);
            while (attempt < 3) {
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


}
