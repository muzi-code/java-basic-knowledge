package com.github.dev.muzi.base.concurrent.knowledge.core.exercise.jdkcontainer;

import java.util.concurrent.LinkedTransferQueue;

public class T07_TransferQueue {

    public static void main(String[] args) {
        // transferqueue 不用 transfer 和正常queue一样。 用了transfer如果没有消费者就会阻塞住。
        LinkedTransferQueue<String> transferQueue = new LinkedTransferQueue<>();

        // 需要先启动消费者，然后才能transfer。如果没有消费者然后去transfer的话就会阻塞。
        new Thread(() -> {
            try {
                System.out.println(transferQueue.take());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }, "consumer").start();

        try {
            transferQueue.transfer("hahah");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
