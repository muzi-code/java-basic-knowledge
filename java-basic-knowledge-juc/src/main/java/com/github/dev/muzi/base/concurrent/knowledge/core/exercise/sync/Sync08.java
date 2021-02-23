package com.github.dev.muzi.base.concurrent.knowledge.core.exercise.sync;

import com.github.dev.muzi.base.concurrent.knowledge.common.ThreadUtils;


/**
 * create by muzi  2019-06-27
 * 细粒度的锁要比粗粒度的锁效率高
 */
public class Sync08 {

    int count = 0;

    //粗粒度的锁
    synchronized void method01(){
        ThreadUtils.seconds(2);
        count = count + 1;
        ThreadUtils.seconds(2);
    }

    //细粒度的锁
    void method02(){
        ThreadUtils.seconds(2);
        synchronized (this){
            count = count + 1;
        }
        ThreadUtils.seconds(2);
    }


}
