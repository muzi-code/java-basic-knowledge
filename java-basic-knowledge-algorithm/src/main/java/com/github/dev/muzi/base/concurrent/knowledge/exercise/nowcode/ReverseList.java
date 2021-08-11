package com.github.dev.muzi.base.concurrent.knowledge.exercise.nowcode;

/**
 * @author lifuyi8
 * @since 2021/2/20 1:49 下午
 */
class ListNode {
    int val;
    ListNode next = null;

    ListNode(int val) {
        this.val = val;
    }
}

public class ReverseList {

    public static ListNode reverseList(ListNode head) {
        if (null == head) {
            return null;
        } else if (head.next == null) {
            return head;
        }
        ListNode newListHead = null;
        ListNode p = head;
        while (head != null) {
            head = head.next;
            p.next = newListHead;
            newListHead = p;
            p = head;
        }
        return newListHead;
    }

    public static void main(String[] args) {
        ListNode a = new ListNode(1);
        ListNode b = new ListNode(2);
        ListNode c = new ListNode(3);
        a.next = b;
        b.next = c;
        ListNode it = a;
        while (it != null) {
            System.out.println(it.val);
            it = it.next;
        }

        ListNode newHead = reverseList(a);

        while (newHead != null) {
            System.out.println(newHead.val);
            newHead = newHead.next;
        }
    }

}
