package com.github.dev.muzi.base.concurrent.knowledge.exercise.struct.sort;

public class NSpaceSort {

    /**
     * 计数排序
     * area为域值，也就是数组中值的上界
     * 下届默认为0
     * 计数排序是以时间换取空间，申请一个阈值为上界大小的数组
     */
    public static int[] calcuNumberOrder(int[] array, int area) {
        int[] temp = new int[area + 1];
        int dt = -1;
        for (int i = 0; i < array.length; i++) {
            dt = array[i];
            temp[dt] = temp[dt] + 1;
        }
        dt = 0;
        for (int j = 0; j < temp.length; j++) {
            if (temp[j] == 0)
                continue;
            while (temp[j] > 0) {
                array[dt] = j;
                temp[j]--;
                dt++;
            }
        }
        return array;
    }


    /**
     * 基数排序
     * 按位归类放入桶中 然后再从桶中按 0 - 9编号的桶依次取出
     * 一共归类最大值的 位个数次，  也就是说 1000归类四次   100 归类三次  10 归类两次次
     */
    public static int[] baseNumberOrder(int[] array, int level) {
        int[][] box = new int[10][array.length];
        int nowbit = 10;        //取余位
        int nowbox = -1;        //向桶内插入数据的当前桶  取余被除计算后的值
        int countArray = 0;     //收取桶内的元素计数器
        int nowcut = 1;         //被除位
        int i = 0, j = 0;           //经常循环使用的迭代变量
        while (nowbit <= level) {
            for (i = 0; i < array.length; i++) {
                nowbox = (array[i] % nowbit) / nowcut;
                box[nowbox][0]++;
                box[nowbox][box[nowbox][0]] = array[i];
            }
            for (i = 0; i < box.length; i++) {
                if (box[i][0] == 0) {
                    continue;
                }
                for (j = 1; j <= box[i][0]; j++) {
                    array[countArray] = box[i][j];
                    countArray++;
                }
                box[i][0] = 0;
            }
            countArray = 0;
            nowbit *= 10;
            nowcut = nowbit / 10;
        }
        return array;
    }

    /**
     * 桶排序
     * 参数为数组，值域，桶个数
     */
    public static int[] bucketOrder(int[] array, int area, int bucketNum) {
        if (bucketNum < 1) return null;
        Bucket[] buckets = new Bucket[bucketNum];
        int bucketArea = area / bucketNum;
        int bucketIndex = 0;
        Bucket temp = null;
        Bucket comp = null;
        int i = 0;
        for (i = 0; i < array.length; i++) {
            bucketIndex = array[i] / bucketArea;
            if (buckets[bucketIndex] == null) {
                buckets[bucketIndex] = new Bucket(0);
            }
            temp = new Bucket(array[i]);            //插入桶
            comp = buckets[bucketIndex].next;
            while (comp != null) {
                if (temp.data < comp.data) {
                    comp.pre.next = temp;
                    temp.pre = comp.pre;
                    temp.next = comp;
                    comp.pre = temp;
                    break;
                } else if (comp.next == null) {
                    comp.next = temp;
                    temp.pre = comp;
                    break;
                }
                comp = comp.next;
            }
            if (comp == null) {
                buckets[bucketIndex].next = temp;
                temp.pre = buckets[bucketIndex];
            }
        }
        comp = null;                       //再次使comp指向空处
        bucketIndex = 0;                        //当作array下标表示器
        for (i = 0; i < buckets.length; i++) {
            if (buckets[i] == null) {
                continue;
            }
            comp = buckets[i].next;
            if (comp == null) {
                continue;
            }
            while (comp != null) {
                array[bucketIndex] = comp.data;
                bucketIndex++;
                comp = comp.next;
            }
        }
        return array;
    }

    /**
     * 桶的数据结构
     */
    private static class Bucket {
        public Bucket pre = null;
        public Bucket next = null;
        public int data = 0;

        public Bucket(int data) {
            this.data = data;
        }
    }
}
