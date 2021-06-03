package com.github.dev.muzi.base.concurrent.knowledge.exercise;

/**
 * @author lifuyi8
 * @since 2021/4/26 2:59 下午
 */
// 示例 1：
//
//
//输入：l1 = [1,2,4], l2 = [1,3,4]
//输出：[1,1,2,3,4,4]
//
//
// 示例 2：
//
//
//输入：l1 = [], l2 = []
//输出：[]
//
//
// 示例 3：
//
//
//输入：l1 = [], l2 = [0]
//输出：[0]
//
//
//
//
// 提示：
//
//
// 两个链表的节点数目范围是 [0, 50]
// -100 <= Node.val <= 100
// l1 和 l2 均按 非递减顺序 排列
//
// Related Topics 递归 链表
// 👍 1685 👎 0
/**
 * Definition for singly-linked list.
 * public class ListNode {
 * int val;
 * ListNode next;
 * ListNode() {}
 * ListNode(int val) { this.val = val; }
 * ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 *
 */
public class Exercise13_MergeDoubleLinked {
    public static class ListNode {
        int val;
        ListNode next;

        ListNode() {
        }

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }

    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        if (l1 == null && l2 != null){
            return l2;
        }else if (l1 != null && l2 == null){
            return l1;
        }else if (l1 == null && l2 == null){
            return null;
        }
        ListNode head = null, area = null;
        while (l1 != null && l2 != null) {
            ListNode append = null;
            if (l1.val <= l2.val) {
                append = l1;
                l1 = l1.next;
            } else {
                append = l2;
                l2 = l2.next;
            }

            if (area == null) {
                head = append;
                area = append;
            } else {
                area.next = append;
                append.next = null;
                area = area.next;
            }
        }

        if (l1 == null) {
            area.next = l2;
        } else {
            area.next = l1;
        }
        return head;
    }


    public static void main(String[] args) {

    }
}
