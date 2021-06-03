package com.github.dev.muzi.base.concurrent.knowledge.struct.sort;

import com.alibaba.fastjson.JSON;

/**
 * @author lifuyi8
 * @since 2021/3/16 7:52 下午
 */
public class InsertSort {


    public static int[] insertSort(int[] array) {
        for (int i = 1; i < array.length; i++) {
            int key = array[i];
            int j = i - 1;
            while (j > -1 && array[j] > key) {
                array[j + 1] = array[j];
                j = j - 1;
            }
            array[j + 1] = key;
        }
        return array;
    }



    public static void main(String[] args) {
        System.out.println(JSON.toJSONString(insertSort(new int[]{5, 2, 4, 6, 1, 3})));
    }
}
