package com.github.dev.muzi.base.concurrent.knowledge.struct.alibaba;

import java.util.LinkedList;
import java.util.TimerTask;


public class AddGiftTask extends TimerTask {

    private GiftWarehouse giftWarehouse;

    public AddGiftTask(GiftWarehouse giftWarehouse) {
        this.giftWarehouse = giftWarehouse;
    }

    public void run() {
        // 生产1000个礼品,存入仓库
        giftWarehouse.pushGiftList(new LinkedList<>());
    }
}
