package com.github.dev.muzi.base.concurrent.knowledge.exercise.nowcode;

import java.util.Stack;

/**
 * @author lifuyi8
 * @since 2021/2/21 12:15 下午
 */
public class DoubleStackIsQueue {

    /*
     *结论：
     *   当插入时，直接插入 stack1
     *   当弹出时，当 stack2 不为空，弹出 stack2 栈顶元素，如果 stack2 为空，将 stack1 中的全部数逐个出栈入栈 stack2，再弹出 stack2 栈顶元素
     */

    Stack<Integer> stack1 = new Stack<Integer>();
    Stack<Integer> stack2 = new Stack<Integer>();

    public void push(int node) {
        stack1.push(node);
    }

    public int pop() {
        if (stack2.size() <= 0) {
            while (stack1.size() != 0) {
                stack2.push(stack1.pop());
            }
        }
        return stack2.pop();
    }
}
