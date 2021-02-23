package com.github.dev.muzi.base.concurrent.knowledge.struct.search;

/**
 * 二分查找的前提是数组有序
 */
public class BinarySearch {


    private static int binarySearch(int[] array, int obj) {
        int left = 0, right = array.length - 1;

        while (left <= right) {
            int mid = (left + right) / 2;
            if (obj == array[mid]) {
                return mid;
            } else if (obj > array[mid]) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return -1;
    }

    private static int binarySearchFirstGt(int[] array, int obj) {
        int left = 0, right = array.length - 1;

        while (left <= right) {
            int mid = (left + right) / 2;
            if (array[mid] > obj) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return (left < array.length) ? left : -1;
    }

    public static void main(String[] args) {
        System.out.println(binarySearchFirstGt(new int[]{0, 1, 2, 3, 3, 5, 6}, 3));
    }
}
