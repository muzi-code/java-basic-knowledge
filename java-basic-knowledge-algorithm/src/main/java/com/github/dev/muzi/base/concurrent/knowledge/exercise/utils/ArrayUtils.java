package com.github.dev.muzi.base.concurrent.knowledge.exercise.utils;

/**
 * 数组常见操作
 * @author lifuyi8
 * @since 2021/3/16 8:12 下午
 */
public class ArrayUtils {

    public static void swap(int[] array, int x, int y) {
        int tmp = array[x];
        array[x] = array[y];
        array[y] = tmp;
    }
}
