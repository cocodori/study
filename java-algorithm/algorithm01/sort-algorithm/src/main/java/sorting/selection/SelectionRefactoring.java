package sorting.selection;

import java.util.Arrays;

public class SelectionRefactoring {
    public static void main(String[] args) {
        selectionSort(new int[]{5, 4, 8, 1, 3, 9, 2});
    }

    private static void selectionSort(int [] arr) {
        for (int selectIndex = 0; selectIndex < arr.length-1; selectIndex++) {
            //가장 작은 원소를 가진 index를 찾는다.
            int minIndex = findMinIndex(arr, selectIndex);
            //원소와 selectIndex의 원소 minIndex의 원소를 교환한다.
            swapElements(arr, minIndex, selectIndex);

            System.out.println(Arrays.toString(arr));
        }
    }

    private static int findMinIndex(int[] arr, int minIndex) {
        for (int j = minIndex + 1; j < arr.length; j++) {
            if (isLessThanMinIndex(arr[j], arr[minIndex])) {
                minIndex = j;
            }
        }
        return minIndex;
    }

    private static void swapElements(int[] arr, int minIndex, int selectIndex) {
        int temp;
        temp = arr[minIndex];
        arr[minIndex] = arr[selectIndex];
        arr[selectIndex] = temp;
    }

    private static boolean isLessThanMinIndex(int nextIndex, int minIndex) {
        return nextIndex < minIndex;
    }
}
