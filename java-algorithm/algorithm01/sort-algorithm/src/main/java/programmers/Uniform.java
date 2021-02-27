package programmers;

public class Uniform {
    public int solution(int n, int[] lost, int[] reserve) {

        /*
         * 문제 link https://programmers.co.kr/learn/courses/30/lessons/42862
         * 1. 체육복을 가지고 오지 않은 학생 확인
         * 2. 이전/다음 사람에게 요청(다음 사람이 있는 지 확인)
         * 3. 요청 받은 사람이 여벌을 가지고 있는 지 확인
         * 4. 요청 받은 사람이 이미 다른 학생에게 체육복을 빌려주었는 지 확인
         */
        int count = 0;
        final int lastStudent = n;

        for(int i = 0; i < lost.length; i++) {
            int prevStudent = lost[i] - 1;
            int nextStudent = lost[i] + 1;

            for(int j = 0; j < reserve.length; j++) {
                //여벌을 챙겨온 학생이 체육복을 도난당한 경우
                if(lost[i] == reserve[j]) {
                    reserve[j] = -1;
                    count++;
                    break;
                }

                if ((i < lost.length-1) && lost[i+1] == reserve[j]) {
                    continue;
                }

                if ((reserve[j] == prevStudent || reserve[j] == nextStudent)
                        && lost[i] != lastStudent) {
                    reserve[j] = -1;
                    count++;
                    break;
                }
            }
        }

        return n - (lost.length - count);
    }
}
