package com.github.dev.muzi.base.concurrent.knowledge.core.exercise.ticket;

import java.util.LinkedList;
import java.util.List;
import java.util.Vector;

public class TicketSeller3 {
    static List<String> ticker = new LinkedList<>();

    static {
        for (int i = 0; i < 10000 ; i++) {
            ticker.add("票编号:" + i);
        }
    }


    public static void main(String[] args) {
        for (int i = 0; i < 10 ; i++) {
            new Thread(()->{
                synchronized (ticker){
                    while(ticker.size() > 0){
                        System.out.println("销售了--："+ticker.remove(0));
                    }
                }
            }).start();
        }
    }
}
