package com.github.dev.muzi.base.concurrent.knowledge.nowcode;

/**
 * @author lifuyi8
 * @since 2021/2/20 8:05 下午
 */
public class StringReverse {


    public static String reverse(String str) {
        char[] array = str.toCharArray();

        for (int i = 0, j = array.length - 1; i < j; i++, j--) {
            char tmp = array[i];
            array[i] = array[j];
            array[j] = tmp;
        }

        return new String(array);
    }

    public static void main(String[] args) {
        System.out.println(reverse("123456789"));
    }
}
