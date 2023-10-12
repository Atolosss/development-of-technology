package algorithms;

import java.util.HashMap;
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

    public static String reversString(final String string) {
        char[] characters = string.toCharArray();
        for (int i = 0; i < characters.length / 2; i++) {
            char temp = characters[i];
            characters[i] = characters[characters.length - 1 - i];
            characters[characters.length - 1 - i] = temp;
        }
        return new String(characters);
    }

    public static String reversStringTwoPointers(final String string) {
        char[] chars = string.toCharArray();
        int left = 0;
        int right = chars.length - 1;

        while (left < right) {
            char temp = chars[left];
            chars[left] = chars[right];
            chars[right] = temp;

            left++;
            right--;
        }
        return new String(chars);
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

    public static StringBuilder getMaxWord(final String str, final Map<Character, Integer> charSize) {
        String[] words = str.split(" ");
        StringBuilder maxWord = new StringBuilder();
        int maxSum = 0;

        for (String word : words) {
            int currentSum = 0;
            for (char c : word.toCharArray()) {
                currentSum += charSize.get(c);
            }
            if (currentSum > maxSum) {
                maxSum = currentSum;
                maxWord = new StringBuilder(word);
            }
        }

        return maxWord;
    }

    public static int[] squaresOfASortedArray(final int[] array) {
        int[] result = new int[array.length];
        int left = 0;
        int right = array.length - 1;
        int index = array.length - 1;

        while (left <= right) {
            int leftSquare = (int) Math.pow(array[left], 2);
            int rightSquare = (int) Math.pow(array[right], 2);

            if (leftSquare > rightSquare) {
                result[index] = leftSquare;
                left++;
            } else {
                result[index] = rightSquare;
                right--;
            }
            index--;
        }

        return result;
    }

    public static boolean twoSum(final int[] array, final int target) {
        int left = 0;
        int right = array.length - 1;
        while (left <= right) {
            if (left + right > target) {
                right--;
            } else if (left + right < target) {
                left++;
            } else {
                return true;
            }

        }
        return false;
    }

    //    0,1  1 2 3 5 8 13 21
//    n= 8 -> 13
//    n = 1 -> 0
    public static int findNumberFibonachi(final int n) {
        int x1 = 0;
        int x2 = 1;
        if (n == 1) {
            return x1;
        }
        if (n == 2) {
            return x2;
        }
        int sum = 0;
        for (int i = 2; i < n; i++) {
            sum = x1 + x2;
            x1 = x2;
            x2 = sum;
        }
        return sum;
    }

    //Задача 1: Поиск первого неповторяющегося символа в строке.
    //
    //Описание: Дана строка, состоящая из букв в нижнем регистре и/или верхнем регистре.
    // Вам нужно найти первый неповторяющийся символ в этой строке и вернуть его индекс. Если такого символа нет, верните -1.

    public static void main(final String[] args) {
        System.out.println(findNumberFibonachi(8));
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

    // lvaccl
    public int findSingleChar(final String str) {
        char[] chars = str.toCharArray();
        Map<Character, Integer> characterIntegerMap = new HashMap<>();
        for (int i = 0; i < chars.length; i++) {
            characterIntegerMap.put(chars[i], characterIntegerMap.getOrDefault(chars[i], 1));
        }
        for (int i = 0; i < chars.length; i++) {
            if (characterIntegerMap.get(chars[i]) == 1) {
                return i;
            }
        }
        return -1;
    }
}





