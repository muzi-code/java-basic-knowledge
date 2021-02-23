package com.github.dev.muzi.base.concurrent.knowledge.nowcode;

/**
 * @author lifuyi8
 * @since 2021/2/21 9:16 上午
 */
public class BigGYS {

    static int xhzzxc(int m, int n) {
        // 余数，当余数为0的时候，最后的m即为最大公约数
        int rem;
        // 先用较小的数对较大的数取余，再用余数对较小的数求余，直到余数为零
        while (n > 0) {
            rem = m % n;
            m = n;
            n = rem;
        }
        return m;
    }

    static int dgzzxc(int m, int n) {
        if (n == 0) return m;
        return dgzzxc(n, m % n);
    }


    public static void main(String[] args) {
        int a = 48, b = 32;
        int gys = xhzzxc(a, b);
        System.out.println(gys);

        int gbs = a * b / gys;
        System.out.println(gbs);
    }
}
