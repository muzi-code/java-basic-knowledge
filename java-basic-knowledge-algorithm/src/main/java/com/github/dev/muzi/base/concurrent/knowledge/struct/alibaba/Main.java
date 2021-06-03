package com.github.dev.muzi.base.concurrent.knowledge.struct.alibaba;


public class Main {

    public static void main(String[] args) {
        GiftWarehouse giftWarehouse = new GiftWarehouse();
        AddGiftTask giftTask = new AddGiftTask(giftWarehouse);
        TimerManager manager = new TimerManager(giftTask);

        // 创建多个用户线程

        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                giftWarehouse.takeGift(Thread.currentThread().getName());
            }
        }, "小明");
    }
}
