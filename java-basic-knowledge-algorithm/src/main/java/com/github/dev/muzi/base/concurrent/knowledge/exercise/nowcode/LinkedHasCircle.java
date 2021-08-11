package com.github.dev.muzi.base.concurrent.knowledge.exercise.nowcode;

/**
 * @author lifuyi8
 * @since 2021/2/21 8:48 上午
 */

public class LinkedHasCircle {


    public static boolean hasCycle(ListNode head) {
        ListNode after = head;

        while (head.next != null){
            head = head.next.next;
            after = after.next;

            if (head == after){
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        ListNode a = new ListNode(1);
        ListNode b = new ListNode(2);
        ListNode c = new ListNode(3);
        a.next = b;
        b.next = c;
//        c.next = a;
        System.out.println(hasCycle(a));
    }
}
