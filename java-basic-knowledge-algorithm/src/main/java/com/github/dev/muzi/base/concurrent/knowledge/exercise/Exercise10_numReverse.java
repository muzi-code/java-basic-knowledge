package com.github.dev.muzi.base.concurrent.knowledge.exercise;

public class Exercise10_numReverse {


    public static int reverse(int x) {
        int result = 0;
        for (int i = 10; x > 0;) {
            result *= i;
            result += (x % 10);
            x /= 10;
        }
        return result;
    }

    public static void main(String[] args) {

        System.out.println(reverse(123456));
    }
}
