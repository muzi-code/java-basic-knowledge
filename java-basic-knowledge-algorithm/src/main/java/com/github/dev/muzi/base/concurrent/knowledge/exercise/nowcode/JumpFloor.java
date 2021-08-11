package com.github.dev.muzi.base.concurrent.knowledge.exercise.nowcode;

/**
 * @author lifuyi8
 * @since 2021/2/21 9:07 上午
 */
public class JumpFloor {


    public static int jumpFloor(int target) {
        int a = 1, b = 2, c = 0;
        if (target == 1) return a;
        if (target == 2) return b;

        for (int i = 3; i <= target; i++) {
            c = a + b;
            a = b;
            b = c;
        }
        return c;
    }

    public static void main(String[] args) {

        // 青蛙跳台阶有几种跳法
        System.out.println(jumpFloor(4));
    }
}
