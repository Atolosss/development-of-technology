package lesson_string_homework;

public final class HomeWork {
    public static final int A = 1;
    public static final int B = 2;
    public static final int C = 3;

    //abc cca bbcc
    //Если каждой букве сопоставить некое число, например
    //a=1
    //b=2
    //c=3
    //То нужно написать метод, который вернет слово с максимальной суммой
    //в нашем случае в последнем слове будет 2+2+3+3 =10
    public static void maxSum(final String str) {
        int count = 0;
        int sumBig = 0;
        String[] input = str.split(" ");
        int answer = 0;
        while (count < input.length) {
            int sum = 0;
            for (int j = 0; j < input[count].length(); j++) {

                if (input[count].charAt(j) == 'a') {
                    sum += A;
                } else if (input[count].charAt(j) == 'b') {
                    sum += B;
                } else if (input[count].charAt(j) == 'c') {
                    sum += C;
                }
                if (j == input[count].length() - 1) {
                    if (sumBig < sum) {
                        sumBig = sum;
                        answer = count;
                    }
                }
            }
            count++;
        }
        System.out.println(input[answer]);
    }
}
