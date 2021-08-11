package com.github.dev.muzi.base.concurrent.knowledge.exercise.nowcode;

/**
 * @author lifuyi8
 * @since 2021/2/20 10:47 下午
 */
public class FindKNum {

    public static int findKth(int[] a, int n, int K) {
        // write code here
        return find(a, 0, n - 1, K);
    }

    public static int find(int[] a, int start, int end, int K) {
        int p = quickSortPartition(a, start, end);
        if (p + 1 < K) {
            return find(a, p + 1, end, K);
        }
        if (p + 1 > K) {
            return find(a, start, p - 1, K);
        }
        return a[p];
    }

    private static Integer quickSortPartition(int[] array, int pre, int rear) {
        // 选择尾部元素为比较的"主元素"
        int mainNumber = array[rear];

        // 设置跟班下标为在迭代下标的前一个位置
        int after = pre - 1;

        // 设置牵头在走的元素为
        for (; pre < rear; pre++) {

            /*
             * 当数组的迭代下标位置元素 >= 主元时，就交换after+1位置和当前pre位置的元素。
             * 当数组的迭代下标位置元素 < 主元时，pre位置就继续向前迭代。
             *
             * 备注：大的就交换到前面，小的就不换。
             */
            if (array[pre] >= mainNumber) {
                exchangeElem(array, ++after, pre);
            }

        }

        // [大,大] [小,小] 主元
        // 最后交换主元和最左面小的
        // [大,大] 主元 [小,小]
        exchangeElem(array, ++after, rear);

        // 返回主元的位置
        return after;
    }

    private static void exchangeElem(int[] array, int x, int y) {
        int temp = array[x];
        array[x] = array[y];
        array[y] = temp;
    }

    public static void main(String[] args) {
        System.out.println(findKth(new int[]{1,2,3,4,4,6},6,3));
    }
}
