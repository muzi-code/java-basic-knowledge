package com.github.dev.muzi.base.concurrent.knowledge.labuladong;

/**
 * 单链表的结点类型
 *
 * @author lifuyi8
 * @since 2021/8/11 6:39 下午
 */
public class ListNode {

    private int value;

    private ListNode next;

    public ListNode(int value) {
        this.value = value;
    }

    public ListNode() {
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public ListNode getNext() {
        return next;
    }

    public void setNext(ListNode next) {
        this.next = next;
    }
}
