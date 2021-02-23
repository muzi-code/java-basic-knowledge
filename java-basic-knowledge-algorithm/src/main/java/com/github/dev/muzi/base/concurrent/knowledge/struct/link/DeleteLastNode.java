package com.github.dev.muzi.base.concurrent.knowledge.struct.link;

public class DeleteLastNode {

    public static Node delete(Node head, int lastNum) {
        Node pre = head, rear = head;
        while (lastNum > 0) {
            if (pre != null) {
                pre = pre.getNext();
            } else {
                return null;
            }
            lastNum--;
        }

        while (pre != null && pre.getNext() != null) {
            pre = pre.getNext();
            rear = rear.getNext();
        }
        Node tmp = null;
        if (rear == head){
            head = head.getNext();
            rear.setNext(null);
        }else{
            tmp = rear.getNext();
            rear.setNext(rear.getNext().getNext());
            tmp.setNext(null);
        }
        return head;
    }

    public static void main(String[] args) {
        Node head = LinkListFactory.getLinkList(1, 2, 3, 4, 5, 6);
        LinkListFactory.show(head);

         head = delete(head,7);


        LinkListFactory.show(head);

    }
}
