package com.github.dev.muzi.base.concurrent.knowledge.nowcode;

/**
 * 两个大整数相加
 * @author lifuyi8
 * @since 2021/2/20 7:53 下午
 */
public class BigNumAdd {

    public static String solve(String s, String t) {
        // 进位值
        int carry = 0;

        // 取最长的字符串加1的数组长度
        char[] res = new char[Math.max(s.length(), t.length()) + 1];

        // 字符串1变成数组
        char[] array1 = s.toCharArray();

        // 字符串2变成字符数组
        char[] array2 = t.toCharArray();

        // 结果填写下标
        int idx = res.length - 1;

        // 字符串1的末位下标
        int idx1 = array1.length - 1;

        // 字符串2的末位下标
        int idx2 = array2.length - 1;

        // 任意下标不为0或进位是1
        while (idx1 >= 0 || idx2 >= 0 || carry != 0) {
            // 字符串1的迭代位字符值，迭代位下标 - -
            int num1 = idx1 >= 0 ? array1[idx1--] - '0' : 0;

            // 字符串2的迭代位字符值，迭代位下标 - -
            int num2 = idx2 >= 0 ? array2[idx2--] - '0' : 0;

            // 数值和进位相加
            int sum = num1 + num2 + carry;

            // 维护进位值
            carry = sum / 10;

            // 结果数组跌打位值计算，迭代位下标 - -
            res[idx--] = (char) (sum % 10 + '0');
        }

        // 结果数组是最大数组的长度+1，若最后进位值为0，需要截取。
        return idx == 0 ? String.valueOf(res).substring(1) : String.valueOf(res);
    }

    public static void main(String[] args) {
        System.out.println(solve("123","456"));
    }
}
