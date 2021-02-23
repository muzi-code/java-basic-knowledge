package com.github.dev.muzi.base.concurrent.knowledge.struct.link;


import lombok.Data;

@Data
public class Node {

    private int data;

    private Node next;

    public Node(int data) {
        this.data = data;
    }
}
