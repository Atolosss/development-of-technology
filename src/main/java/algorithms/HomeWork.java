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

    // abc ccc bbb cccc, a = 1 , b = 2, c = 3;
//    TODO: возвращать не число ,а слово. StringBuilder или String
    //TODO: сделать за O(n)
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

    //TODO Squares of a Sorted Array
    //Given an integer array nums sorted in non-decreasing order, return an array of the squares of each number sorted in non-decreasing order.
    //
    //
    //
    //Example 1:
    //
    //Input: nums = [-4,-1,0,3,10]
    //Output: [0,1,9,16,100]
    //Explanation: After squaring, the array becomes [16,1,0,9,100].
    //After sorting, it becomes [0,1,9,16,100].
    //Example 2:
    //
    //Input: nums = [-7,-3,2,3,11]
    //Output: [4,9,9,49,121]
    //
    //
    //Constraints:
    //
    //1 <= nums.length <= 104
    //-104 <= nums[i] <= 104
    //nums is sorted in non-decreasing order.
    //
    //
    //Follow up: Squaring each element and sorting the new array is very trivial, could you find an O(n) solution using a different approach?

    //TODO: Two Sum
    //Example 2: Given a sorted array of unique integers and a target integer, return true if there exists a pair of numbers that sum to target, false otherwise. This problem is similar to Two Sum. (In Two Sum, the input is not sorted).
    //
    //For example, given nums = [1, 2, 4, 6, 8, 9, 14, 15] and target = 13, return true because 4 + 9 = 13.
    public static boolean twoSum(int[] array, int target) {
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
}





