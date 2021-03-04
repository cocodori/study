package programmers;

/*
*  link : https://programmers.co.kr/learn/courses/30/lessons/12903
*
*   문제 :
*       단어 s의 가운데 글자를 반환하는 함수, solution을 만들어 보세요. 단어의 길이가 짝수라면 가운데 두글자를 반환하면 됩니다.
*   풀이
*       substring()메서드 사용하여 해결.
*       substring(int begin, int end)는
*       begin은 포함하고 end는 포함하지 않는다.
*       String의 length()는 1부터 시작하는 반면
*       substring은 문자열을 0번부터 시작한다.
*
*
*       1. s가 짝수일 때 길이/2-1, 길이/2+1
*       2. s가 홀수일 때 길이/2, 길이/2+1
*
* */
public class MiddleCharacter {
    public String solution(String s) {
        int centerNum = s.length() / 2;

        if (s.length()%2 == 0)
            return s.substring(centerNum-1, centerNum+1);
        else
            return s.substring(centerNum, centerNum+1);
    }

    public static void main(String[] args) {
        MiddleCharacter m = new MiddleCharacter();
        System.out.println(m.solution("abcde")); // c
        System.out.println(m.solution("qwer")); // we
    }
}
