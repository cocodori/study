package sorting.insertion;

import java.util.Arrays;

public class Insertion {
    public static void main(String[] args) {
        insertionSort(new int[]{5,7,2,4,6,3,1});
    }

    private static void insertionSort(int[] arr) {
        for (int i = 1; i < arr.length; i++) {
            int temp = arr[i];
            int prevIndex = i - 1;

            while (isValidIndex(prevIndex)
                    && isPreviousElementBigger(arr[prevIndex], temp)) {
                //이전 요소의 자리를 한 칸 뒤로 미룬다.
                prevElementSwapNextIndex(arr, prevIndex);
                //prevIndex를 감소시킨다.
                prevIndex--;
            }

            //요소를 제자리에 삽입한다.
            insertElement(arr, prevIndex, temp);

            System.out.println(Arrays.toString(arr));
        }
    }

    //엘리먼트가 원래 위치해야 할 자리에 삽입한다.
    private static void insertElement(int[] arr, int index, int element) {
        arr[index + 1] = element;
    }

    //이전 요소가 현재 요소보다 클 때 이전 요소를 다음 인덱스로 이동시키는 메소드
    private static void prevElementSwapNextIndex(int[] arr, int prevIndex) {
        arr[prevIndex + 1] = arr[prevIndex];
    }

    //이전 요소가 더 크다면 true를 반환하는 메소드
    private static boolean isPreviousElementBigger(int prevElement, int targetElement) {
        //비교할 인덱스가 존재하고, 이전 요소가 더 크다면 true
        return prevElement > targetElement;
    }

    //index유효성 검사
    private static boolean isValidIndex(int prevIndex) {
        return prevIndex >= 0;
    }
}
