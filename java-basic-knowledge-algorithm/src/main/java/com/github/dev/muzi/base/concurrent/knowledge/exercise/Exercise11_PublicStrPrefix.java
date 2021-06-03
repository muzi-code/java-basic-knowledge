package com.github.dev.muzi.base.concurrent.knowledge.exercise;


//编写一个函数来查找字符串数组中的最长公共前缀。
//如果不存在公共前缀，返回空字符串 ""。
//
// 示例 1：
// 输入：strs = ["flower","flow","flight"]
// 输出："fl"
//
// 示例 2：
// 输入：strs = ["dog","racecar","car"]
// 输出：""
// 解释：输入不存在公共前缀。
//
// 提示：
// 0 <= strs.length <= 200
// 0 <= strs[i].length <= 200
// strs[i] 仅由小写英文字母组成
// Related Topics 字符串
// 👍 1579 👎 0
public class Exercise11_PublicStrPrefix {

    public static void main(String[] args) {
        String[] strs = new String[]{"flower", "flow", "flight"};
        System.out.println(longestCommonPrefix(strs));
    }

    public static String longestCommonPrefix(String[] strs) {
        String flag = strs[0];
        for (int i = 1; i < strs.length; i++) {
            String prefix = prefix(flag, strs[i]);
            if (null == prefix) {
                return "";
            }
            flag = prefix;
        }
        return flag;
    }

    public static String prefix(String s1, String s2) {
        // 获得两串的长度
        int len1 = s1.length(), len2 = s2.length();
        // 原串串为空
        if (len1 <= 0 || len2 <= 0) {
            return null;
        }
        // count用来统计公共前缀个数
        int count = 0, n = 0;
        // 取最短串的长度，最复杂为短串整体为长串的前缀
        if (len1 > len2) {
            n = len2;
        } else {
            n = len1;
        }
        // 循环比较
        for (int i = 0; i < n; i++) {
            if (s1.charAt(i) == s2.charAt(i)) {
                // 若相同则加1
                count++;
            } else {
                // 因为获得的是前缀，只要有一个不同跳出循环
                break;
            }
        }
        // 经过比较无公共前缀
        if (count == 0) {
            return null;
        }
        // substring(i,j) 输出该串从i下标到j下标的子串
        return s1.substring(0, count);
    }
}
