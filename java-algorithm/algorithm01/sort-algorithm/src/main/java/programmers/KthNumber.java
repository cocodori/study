package programmers;

import java.util.*;
/*
*  문제 링크 https://programmers.co.kr/learn/courses/30/lessons/42748/solution_groups?language=java
*  풀이 과정
*   TODO
*    1. commands[][] 분리
*    2. array를 i부터 j까지 출력
*    3. 정렬
*    4. k번째 수
* */
class KthNumber {
    public static int[] solution(int[] array, int[][] commands) {
        List<Integer> results = new ArrayList<>();

        for(int[] command : commands) {
            List<Integer> list = new ArrayList<>();

            for(int i = 0; i < array.length; i++) {
                if(i+1 >= command[0] && i+1 <= command[1])
                    list.add(array[i]);
            }

            Collections.sort(list);

            results.add(list.get(command[2] - 1));
        }

        return results.stream()
                .mapToInt(Integer::new)
                .toArray();
    }

    public static void main(String[] args) {
        int[] solution = solution(new int[]{1, 5, 2, 6, 3, 7, 4}, new int[][]{{2, 5, 3}, {4, 4, 1}, {1, 7, 3}});
        System.out.println(Arrays.toString(solution));
    }
}
