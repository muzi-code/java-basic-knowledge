package com.github.dev.muzi.base.concurrent.knowledge.exercise;


/**
 * 题目：面试题10- II. 青蛙跳台阶问题
 * 一只青蛙一次可以跳上1级台阶，也可以跳上2级台阶。求该青蛙跳上一个 n 级的台阶总共有多少种跳法。
 * 答案需要取模 1e9+7（1000000007），如计算初始结果为：1000000008，请返回 1。
 * 示例 1：
 *      输入：n = 2
 *      输出：2
 * 示例 2：
 *      输入：n = 7
 *      输出：21
 * 提示：
 *      0 <= n <= 100
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/qing-wa-tiao-tai-jie-wen-ti-lcof
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class Exercise05_numWays {

    public static int numWays(int n) {
        if (n <= 0) {
            return 1;
        }
        int f1 = 1,f2 = 2,result = f1 + f2;
        if (n == 1) {
            return f1;
        } else if (n == 2) {
            return f2;
        }
        for (int i = 3; i <= n; i++) {
            f1 = f2;
            f2 = (f1 + f2) % 1000000007;
        }
        return f2;
    }

    public static void main(String[] args) {
        System.out.println(numWays(44));
    }
}
