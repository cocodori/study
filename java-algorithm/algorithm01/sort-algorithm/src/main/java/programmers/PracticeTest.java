package programmers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static java.lang.Math.max;


/*
 *  https://programmers.co.kr/learn/courses/30/lessons/42840
 * */
public class PracticeTest {
    public static void main(String[] args) {
        System.out.println(Arrays.toString(solution(new int[]{1,2,3,4,5})));
    }

    public static int[] solution(int[] answers) {
        int[] student1 = {1,2,3,4,5};
        int[] student2 = {2,1,2,3,2,4,2,5};
        int[] student3 = {3,3,1,1,2,2,4,4,5,5};

        int student1Score = 0;
        int student2Score = 0;
        int student3Score = 0;


        for (int i=0; i < answers.length; i++) {
            if (answers[i] == student1[i%student1.length]) {
                student1Score++;
            }
            if (answers[i] == student2[i%student2.length]) {
                student2Score++;
            }
            if (answers[i] == student3[i%student3.length]) {
                student3Score++;
            }
        }

        int maxScore = max(max(student1Score, student2Score), student3Score);

        List<Integer> maxScores = new ArrayList<>();

        if (maxScore == student1Score)
            maxScores.add(1);
        if (maxScore == student2Score)
            maxScores.add(2);
        if (maxScore == student3Score)
            maxScores.add(3);

        return maxScores.stream()
                .mapToInt(Integer :: intValue)
                .toArray();
    }
}