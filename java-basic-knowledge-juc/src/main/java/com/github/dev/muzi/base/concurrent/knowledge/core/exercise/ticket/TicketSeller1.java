package com.github.dev.muzi.base.concurrent.knowledge.core.exercise.ticket;

import java.util.ArrayList;
import java.util.List;

public class TicketSeller1 {
    static List<String> ticker = new ArrayList<>();

    static {
        for (int i = 0; i < 10000 ; i++) {
            ticker.add("票编号:" + i);
        }
    }


    public static void main(String[] args) {
        for (int i = 0; i < 10 ; i++) {
            new Thread(()->{
                while(ticker.size() > 0){
                    System.out.println("销售了--："+ticker.remove(0));
                }
            }).start();
        }
    }
}
