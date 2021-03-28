package programmers;

import java.util.Arrays;

public class DivisibleNumber {
    public static void main(String[] args) {
        int[] solution = solution(new int[]{5, 9, 7, 10}, 99);
        System.out.println(Arrays.toString(solution));
    }

    static int[] solution(int[] arr, int divisor) {
        int[] result = Arrays.stream(arr)
                .filter(number -> number % divisor == 0)
                .sorted()
                .toArray();

        if (result.length == 0)
            result = new int[]{-1};

        return result;
    }
}
