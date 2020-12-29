package sorting.selection;

import java.util.Arrays;

public class Selection {
    public static void main(String[] args) {
        selectionSort(new int[]{5, 4, 8, 1, 3, 9, 2});
    }

    private static void selectionSort(int [] arr) {
        int minIndex;
        int temp;

        for (int i = 0; i < arr.length - 1; i++) {
            minIndex = i;
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[j] < arr[minIndex]) {
                    minIndex = j;
                }
            }

            temp = arr[minIndex];
            arr[minIndex] = arr[i];
            arr[i] = temp;

            System.out.println(Arrays.toString(arr));
        }
    }
}
