package sorting.bubble;

import java.util.Arrays;

public class Bubble {
    public static void main(String[] args) {
        bubbleSort(new int[]{8,3,5,7,4,1,2});
    }

    private static void bubbleSort(int[] arr) {
         int temp = 0;

         for (int i = 0; i < arr.length; i++) {
             for (int j = 1; j < arr.length-i; j++) {
                 if (arr[j] < arr[j-1]) {
                     temp = arr[j-1];
                     arr[j-1] = arr[j];
                     arr[j] = temp;
                 }
             }
             System.out.println(Arrays.toString(arr));
         } // end - for
    }
}
