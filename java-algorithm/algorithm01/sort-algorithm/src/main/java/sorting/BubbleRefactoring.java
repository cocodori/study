package sorting;

import java.util.Arrays;

/*
*   blog-link: https://coco-log.tistory.com/120
* */
public class BubbleRefactoring {
    public static void main(String[] args) {
        bubbleSort(new int[]{8,3,5,7,4,1,2});
    }

    private static void bubbleSort(int[] arr) {
         int temp = 0;

         for (int i = 0; i < arr.length; i++) {
             for (int j = 1; j < arr.length-i; j++) {
                 if (isPreviousElementBigger(arr[j], arr[j-1])) {
                     arr = swapElements(arr, j);
                 }
             }
             System.out.println(Arrays.toString(arr));
         } // end - for
    }

    private static int[] swapElements(int[] arr, int j) {
        int temp;

        temp = arr[j];
        arr[j] = arr[j -1];
        arr[j -1] = temp;

        return arr;
    }

    private static boolean isPreviousElementBigger(int element, int prevElement) {
        return element < prevElement;
    }
}
