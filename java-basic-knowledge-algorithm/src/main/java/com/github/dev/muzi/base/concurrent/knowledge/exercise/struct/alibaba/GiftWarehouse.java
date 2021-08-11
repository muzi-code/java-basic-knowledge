package com.github.dev.muzi.base.concurrent.knowledge.exercise.struct.alibaba;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class GiftWarehouse {

    private volatile LinkedList<Gift> giftQueue = new LinkedList<>();

    private volatile Map<String, LinkedList<Gift>> takeInfo = new ConcurrentHashMap<>();

    public synchronized void pushGiftList(LinkedList<Gift> gifts) {
        giftQueue.addAll(gifts);
    }

    public Gift takeGift(String name) {
        LinkedList<Gift> oldTakeList = null;
        if (takeInfo.containsKey(name)) {
            oldTakeList = takeInfo.get(name);
        } else {
            oldTakeList = new LinkedList<>();
            takeInfo.put(name, oldTakeList);
        }
        if (oldTakeList.size() >= 3){
            System.out.println("每个用户不能超过三个。");
            return null;
        }

        Gift takeGift = null;
        if (giftQueue.size() > 0) {
            synchronized (giftQueue) {
                if (giftQueue.size() > 0) {
                    takeGift = giftQueue.pollFirst();
                }
            }
        }

        if (takeGift != null){
            oldTakeList.add(takeGift);
        }
        return takeGift;
    }

}
