package com.github.dev.muzi.base.concurrent.knowledge.core.exercise.ticket;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

public class TicketSeller4 {
    static Queue<String> ticker = new ConcurrentLinkedQueue<>();

    static {
        for (int i = 0; i < 10000; i++) {
            ticker.add("票编号:" + i);
        }
    }


    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                while (true) {
                    String s = ticker.poll();
                    if (s == null) break;
                    System.out.println("销售了--：" + s);
                }
            }).start();
        }
    }
}
