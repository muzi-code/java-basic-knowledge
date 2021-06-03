package com.github.dev.muzi.base.concurrent.knowledge.exercise;

import java.util.LinkedList;

//给定一个只包括 '('，')'，'{'，'}'，'['，']' 的字符串 s ，判断字符串是否有效。
//
// 有效字符串需满足：
//
//
// 左括号必须用相同类型的右括号闭合。
// 左括号必须以正确的顺序闭合。
//
//
//
//
// 示例 1：
//
//
//输入：s = "()"
//输出：true
//
//
// 示例 2：
//
//
//输入：s = "()[]{}"
//输出：true
//
//
// 示例 3：
//
//
//输入：s = "(]"
//输出：false
//
//
// 示例 4：
//
//
//输入：s = "([)]"
//输出：false
//
//
// 示例 5：
//输入：s = "{[]}"
//输出：true
//
// 提示：
// 1 <= s.length <= 104
// s 仅由括号 '()[]{}' 组成
//
// Related Topics 栈 字符串
// 👍 2360 👎 0
public class Exercise12_ValidBrackets {
    public static boolean isValid(String s) {
        LinkedList stack = new LinkedList();
        stack.push(s.charAt(0));
        for (int i = 1; i < s.length(); i++) {
            char item = s.charAt(i);
            char stackTop = stack.isEmpty() ? '\u0001' : (char) stack.getFirst();
            if (isNeedPop(stackTop, item)) {
                stack.pop();
            } else {
                stack.push(item);
            }
        }
        return stack.isEmpty();
    }

    public static boolean isNeedPop(char stackTop, char item) {
        boolean result = false;
        switch (item) {
            case ')':
                result = stackTop == '(';
                break;
            case ']':
                result = stackTop == '[';
                break;
            case '}':
                result = stackTop == '{';
                break;
        }
        return result;
    }

    public static void main(String[] args) {
        System.out.println(isValid("()"));
        System.out.println(isValid("()[]{}"));
        System.out.println(isValid("(]"));
        System.out.println(isValid("([)]"));
        System.out.println(isValid("{[]}"));
    }
}
