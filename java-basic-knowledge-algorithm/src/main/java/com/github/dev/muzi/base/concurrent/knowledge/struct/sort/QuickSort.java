package com.github.dev.muzi.base.concurrent.knowledge.struct.sort;

/**
 * create by muzi  2019-05-09
 */
public class QuickSort {


    public static void main(String[] args) {
        int[] arr = {12, 53, 78, 6, 4, 29, 13, 26, 78, 33, 19, 14, 25};
        quickSort(arr, 0, arr.length - 1);
        for (Integer value : arr) {
            System.out.print(value + " ");
        }
    }

    public static int[] quickSort(int[] array, int pre, int rear) {
        int minNumber;
        if (pre < rear) {
            minNumber = quickSortPartition(array, pre, rear);
            quickSort(array, pre, minNumber - 1);
            quickSort(array, minNumber + 1, rear);
        }
        return array;
    }

    private static Integer quickSortPartition(int[] array, int pre, int rear) {
        int mainNumber = array[rear];
        int index = pre - 1;

        for (int j = pre; j < rear; j++) {
            if (array[j] <= mainNumber) {
                index++;
                exchangeElem(array, index, j);
            }
        }
        exchangeElem(array, ++index, rear);
        return index;
    }

    private static void exchangeElem(int[] array, int x, int y) {
        int temp = array[x];
        array[x] = array[y];
        array[y] = temp;
    }
}
