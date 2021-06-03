package com.github.dev.muzi.base.concurrent.knowledge.aqs;

import java.util.concurrent.locks.AbstractQueuedSynchronizer;
import java.util.concurrent.locks.Condition;

/**
 * @author lifuyi8
 * @since 2021/5/17 2:25 下午
 */
public class EnjoySync extends AbstractQueuedSynchronizer {


    /**
     * 主要实现获取锁
     */
    @Override
    protected boolean tryAcquire(int arg) {
        if (getState() == 0) {
            boolean suc = compareAndSetState(0, 1);
            if (suc) {
                setExclusiveOwnerThread(Thread.currentThread());
                return true;
            }
        }
        return false;
    }

    /**
     * 释放锁
     */
    @Override
    protected boolean tryRelease(int arg) {
        setExclusiveOwnerThread(null);
        setState(0);
        return true;
    }

    /**
     * 当前这个锁是否被人持有
     */
    @Override
    protected boolean isHeldExclusively() {

        return !(getState() == 0);
    }


    public Condition newCondition(){
        return new ConditionObject();
    }
}
