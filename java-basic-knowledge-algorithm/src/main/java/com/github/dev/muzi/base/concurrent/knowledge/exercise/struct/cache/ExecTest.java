package com.github.dev.muzi.base.concurrent.knowledge.exercise.struct.cache;

public class ExecTest {

    public static void main(String[] args) {
        RBTree<Integer> rb = new RBTree<>();

        for (int i = 0; i < 100; i++) {
            rb.add("" + i, i);
        }

    }
}
