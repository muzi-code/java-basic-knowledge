package com.github.dev.muzi.base.concurrent.knowledge.labuladong;

/**
 * 双向链表结点类型
 *
 * @author lifuyi8
 * @since 2021/8/11 6:41 下午
 */
public class DListNode {

    private int value;

    private ListNode next;

    private ListNode pre;

    public DListNode() {
    }

    public DListNode(int value) {
        this.value = value;
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

    public ListNode getPre() {
        return pre;
    }

    public void setPre(ListNode pre) {
        this.pre = pre;
    }
}
