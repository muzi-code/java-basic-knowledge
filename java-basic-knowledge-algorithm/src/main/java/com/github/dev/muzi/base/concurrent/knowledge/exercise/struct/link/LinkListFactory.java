package com.github.dev.muzi.base.concurrent.knowledge.exercise.struct.link;

public class LinkListFactory {

    public static Node getLinkList(int... args) {
        if (args == null) {
            return null;
        }
        Node head = null, p = null;
        for (int item : args) {
            if (head == null) {
                head = new Node(item);
                p = head;
            } else {
                p.setNext(new Node(item));
                p = p.getNext();
            }
        }
        return head;
    }

    public static void show(Node node) {
        Node p = node;
        System.out.println();
        while (p != null) {
            System.out.print(p.getData() + " ");
            p = p.getNext();
        }
        System.out.println();
    }
}
