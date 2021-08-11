package com.github.dev.muzi.base.concurrent.knowledge.exercise.struct.link;

public class ReverseLinkList {

    public static Node reverse(Node node) {
        Node pre = null;
        Node next = null;
        while (node != null) {
            next = node.getNext();
            node.setNext(pre);
            pre = node;
            node = next;
        }
        return pre;
    }

    public static void main(String[] args) {
        Node head = LinkListFactory.getLinkList(1, 2, 3, 4, 5, 6);
        LinkListFactory.show(head);

        head = reverse(head);

        LinkListFactory.show(head);
    }
}
