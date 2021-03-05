package programmers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Duplication {
    public int[] solution(int[] arr) {
        List<Integer> list = new ArrayList<>();

        list.add(arr[0]);

        for(int i  = 1; i < arr.length; i++)
            if(arr[i] != arr[i-1])
                list.add(arr[i]);

        return list.stream()
                .mapToInt(Integer::new)
                .toArray();
    }

    public static void main(String[] args) {
        Duplication duplication = new Duplication();
        int[] solution1 = duplication.solution(new int[]{1, 1, 1, 0, 0, 1, 1});
        int[] solution2 = duplication.solution(new int[]{4, 4, 4, 4, 3, 3, 3, 0, 0, 0});
        System.out.println(Arrays.toString(solution1));
        System.out.println(Arrays.toString(solution2));
    }
}
