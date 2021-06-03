package com.github.dev.muzi.base.concurrent.knowledge.struct.sort;

/**
 * create by muzi  2019-05-09
 */
public class MergeSort {


    public static void main(String[] args) {
        int[] arr = {12, 53, 78, 6, 4, 29, 13, 26, 78, 33, 19, 14, 25};
        defaultRecursionMergeOrder(arr);
        for (Integer value : arr) {
            System.out.print(value + " ");
        }
    }

    /**
     * 归并排序入口
     * 不需要设置其边界值的入口
     */
    public static void defaultRecursionMergeOrder(int[] array) {
        if (array != null)
            mergeSort(array, 0, array.length - 1);
    }

    /**
     * 归并排序，传入待排序数组，以及待排序的左边界和右边界。
     */
    public static void mergeSort(int[] array, int left, int right) {
        if (left == right) {
            return;
        }
        int mid = (right + left) / 2;
        mergeSort(array, left, mid);
        mergeSort(array, mid + 1, right);
        leftMergeRight(array, left, mid, mid + 1, right);
    }

    /**
     * 归并排序：和并两个有序数组
     */
    public static void leftMergeRight(int[] array, int ll, int lr, int rl, int rr) {
        int[] newArray = new int[rr - ll  + 1];
        int point = 0, leftPoint = ll, rightPoint = rl;

        while (leftPoint <= lr && rightPoint <= rr) {
            //两个引用分别指向两个数组  从0开始比较大的计入新数组中  新数组指针 比较者大的数组指针+1
            newArray[point++] = array[leftPoint] < array[rightPoint] ? array[leftPoint++] : array[rightPoint++];
        }

        //一旦左右数组有一个指针不能继续比较了，那么就对剩下的单个数组进行合并
        while (leftPoint <= lr) {
            //左面数组没合并玩 依次合并入新数组
            newArray[point++] = array[leftPoint++];
        }
        while (rightPoint <= rr) {
            //右边数组没合并完 依次合并入新数组
            newArray[point++] = array[rightPoint++];
        }

        int copyPoint = ll;
        for (int i = 0; i < newArray.length; i++) {
            array[copyPoint++] = newArray[i];
        }
    }
}
